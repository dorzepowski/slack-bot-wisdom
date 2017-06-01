package damiano.integration

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@ContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT)
class SlackIntegrationEndpointSpec extends Specification {

	@LocalServerPort
	private int port

	@Autowired
	private TestRestTemplate restTemplate

	def "Respond for command go to chanel"() {
		given:
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>()
			map.add("token", "12345")
			map.add("text", "very wisdom text")

		when:
			ResponseEntity<String> response = restTemplate.postForEntity("/", map, String.class)
			def message = new JsonSlurper().parseText(response.body)
		then:
			message.response_type == "in_channel"
	}

	def "Respond with attachment"() {
		given:
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>()
			map.add("token", "12345")
			map.add("text", "very wisdom text")

		when:
			ResponseEntity<String> response = restTemplate.postForEntity("/", map, String.class)
			def message = new JsonSlurper().parseText(response.body)
		then:
			message.attachments != null
			message.attachments.size() == 1

	}

	def "Respond with image url"() {
		given:
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>()
			map.add("token", "12345")
			map.add("text", "very wisdom text")

		when:
			ResponseEntity<String> response = restTemplate.postForEntity("/", map, String.class)
			def message = new JsonSlurper().parseText(response.body)
			def attachment = message.attachments.first()
		then:
			attachment.image_url
			attachment.image_url.startsWith("http://localhost:$port/wisdom/")
	}


	def "Provide image for received id"() {
		given:
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>()
			map.add("token", "12345")
			map.add("text", "very wisdom text")
			ResponseEntity<String> response = restTemplate.postForEntity("/", map, String.class)
			def message = new JsonSlurper().parseText(response.body)
			String imageUrl = message.attachments.first().image_url
		when:
			HttpHeaders headers = new HttpHeaders()
			headers.setAccept([MediaType.IMAGE_PNG])
			HttpEntity<String> request = new HttpEntity<String>(headers)

			ResponseEntity<byte[]> entity = restTemplate.exchange(imageUrl, HttpMethod.GET, request, byte[])
			byte[] image = entity.getBody()

		then:
			image
			image.size() != 0

	}


}
