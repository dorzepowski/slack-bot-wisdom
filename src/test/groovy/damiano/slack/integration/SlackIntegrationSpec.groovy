package damiano.slack.integration

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import spock.lang.Ignore
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@ContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT)
class SlackIntegrationEndpointSpec extends Specification {

	@LocalServerPort
	private int port

	@Autowired
	private TestRestTemplate restTemplate

	@Ignore
	def "when someone use slash command and provide text, then return location of image"() {
		given:
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>()
			map.add("token", "12345")
			map.add("text", "")

		when:
			ResponseEntity<String> response = restTemplate.postForEntity("/", map, String.class)
			def json = new JsonSlurper().parseText(response.body)
			println json
			println response.headers
			println port
		then:
			json.text == ""
			json.response_type == "in_channel"
			json.attachments
			json.attachments.image_url == "http://localhost:$port/wisdom/{hdkghjsghjhs}"
	}

	def "Show me content :) "() {
		given:
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>()
			map.add("token", "12345")
			map.add("text", "")

		when:
			ResponseEntity<String> response = restTemplate.postForEntity("/", map, String.class)
			def json = new JsonSlurper().parseText(response.body)
			println json
			println response.headers
			println port
		then:
			json
	}

	def "Respond for command go to chanel"() {
		given:
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>()
			map.add("token", "12345")
			map.add("text", "")

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
			map.add("text", "")

		when:
			ResponseEntity<String> response = restTemplate.postForEntity("/", map, String.class)
			def message = new JsonSlurper().parseText(response.body)
		then:
			message.attachments != null
	}

	def "Respond with image url"() {
		given:
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>()
			map.add("token", "12345")
			map.add("text", "")

		when:
			ResponseEntity<String> response = restTemplate.postForEntity("/", map, String.class)
			def message = new JsonSlurper().parseText(response.body)
			def attachments = message.attachments
		then:
			attachments.image_url
			attachments.image_url ==~ /http:\/\/localhost:$port\/wisdom\/\w+/
	}


}
