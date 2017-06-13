package damiano.integration

import damiano.printer.Image
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@ContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT)
class AdminEndpointSpec extends Specification {

	@Autowired
	TestRestTemplate restTemplate

	@MockBean
	Image image


	def "expect api statuses"() {
		when:
			ResponseEntity<Void> entity = restTemplate.postForEntity("/admin/wisdom", ["wise text", "word of wisdom"], Void)
		then:
			entity.statusCode == HttpStatus.CREATED
		when:
			ResponseEntity<String> response = restTemplate.getForEntity("/admin/wisdom", String)
		then:
			response.statusCode == HttpStatus.OK
	}

	def "list all stored words of wisdom"() {
		given:
			def wordsToFeed = ["wise text", "word of wisdom"]
			restTemplate.postForEntity("/admin/wisdom", wordsToFeed, Void)
		when:
			ResponseEntity<String> response = restTemplate.getForEntity("/admin/wisdom", String)
			def wordsOfWisdom = new JsonSlurper().parseText(response.body)
		then:
			wordsOfWisdom.size() == 2
			wordsOfWisdom.containsAll(wordsToFeed)

	}

}