package damiano.integration

import damiano.printer.Image
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import spock.lang.Shared
import spock.lang.Specification

import static org.mockito.BDDMockito.given
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.http.HttpMethod.GET

@ContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT)
class SlackIntegrationEndpointSpec extends Specification {

	@LocalServerPort
	int port

	@Autowired
	TestRestTemplate restTemplate

	@MockBean
	Image image

	@Shared
	static Resource imgResource = new ClassPathResource("dc-background.png")

	static private String token = "12345"


	void setup() {
		given(image.toByteArray()).willReturn(imgResource.inputStream.bytes)
	}

	def "Respond for command go to chanel"() {
		when:
			def message = send(slackCommand())
		then:
			message.response_type == "in_channel"
	}

	def "Respond with attachment"() {
		when:
			def message = send(slackCommand())
		then:
			message.attachments != null
			message.attachments.size() == 1

	}

	def "Respond with image url"() {
		when:
			def message = send(slackCommand())
			def attachment = message.attachments.first()
		then:
			attachment.image_url
			attachment.image_url.startsWith("http://localhost:$port/wisdom/")
	}


	def "Provide image for received id"() {
		given:
			def message = send(slackCommand())
			String imageUrl = message.attachments.first().image_url
		when:
			byte[] image = loadImage(imageUrl)
		then:
			image
			image.size() != 0

	}

	def "Respond with bad request for wrong token"() {
		given:
			token = "WRONG"
		when:
			def error = send(slackCommand())
		then:
			error
			error.status == HttpStatus.BAD_REQUEST.value()
	}


	private static MultiValueMap<String, String> slackCommand() {
		MultiValueMap<String, String> command = new LinkedMultiValueMap<String, String>()
		command.add("token", token)
		command.add("text", "very wisdom text")
		return command
	}

	private def send(MultiValueMap<String, String> command) {
		ResponseEntity<String> response = restTemplate.postForEntity("/", command, String.class)
		return new JsonSlurper().parseText(response.body)
	}

	private byte[] loadImage(String imageUrl) {
		//noinspection GroovyAssignabilityCheck
		ResponseEntity<byte[]> entity = restTemplate.exchange(imageUrl, GET, imageRequestHeaders(), byte[])
		return entity.getBody()
	}

	private static HttpEntity<String> imageRequestHeaders() {
		HttpHeaders headers = new HttpHeaders()
		headers.setAccept([MediaType.IMAGE_PNG])
		return new HttpEntity<String>(headers)
	}


}
