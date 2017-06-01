package damiano.integration

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.http.HttpMethod.GET

@ContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT)
class SlackIntegrationEndpointSpec extends Specification {

	@LocalServerPort
	private int port

	@Autowired
	private TestRestTemplate restTemplate


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


	private static MultiValueMap<String, String> slackCommand() {
		MultiValueMap<String, String> command = new LinkedMultiValueMap<String, String>()
		command.add("token", "12345")
		command.add("text", "very wisdom text")
		return command
	}

	private def send(MultiValueMap<String, String> command) {
		ResponseEntity<String> response = restTemplate.postForEntity("/", command, String.class)
		return new JsonSlurper().parseText(response.body)
	}

	private byte[] loadImage(String imageUrl) {
		ResponseEntity<byte[]> entity = restTemplate.exchange(imageUrl, GET, imageRequestHeaders(), byte[])
		return entity.getBody()
	}

	private static HttpEntity<String> imageRequestHeaders() {
		HttpHeaders headers = new HttpHeaders()
		headers.setAccept([MediaType.IMAGE_PNG])
		return new HttpEntity<String>(headers)
	}

}
