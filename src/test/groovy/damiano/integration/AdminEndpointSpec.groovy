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

	private List<String> wordsToSave = ["wise text", "word of wisdom"]


	def "expect api statuses"() {
		when:
			ResponseEntity<Void> entity = postForSave(wordsToSave)
		then:
			entity.statusCode == HttpStatus.CREATED
		when:
			ResponseEntity<String> response = getAllWisdomPhrases()
		then:
			response.statusCode == HttpStatus.OK
	}

	def "list all stored words of wisdom"() {
		given:
			postForSave(wordsToSave)
		when:
			def wordsOfWisdom = parsed(getAllWisdomPhrases())
		then:
			wordsOfWisdom.size() == 2
			wordsOfWisdom.containsAll(wordsToSave)

	}

	private ResponseEntity<Void> postForSave(List<String> feed) {
		restTemplate.postForEntity("/admin/wisdom", feed, Void)
	}

	private def parsed(ResponseEntity<String> response) {
		new JsonSlurper().parseText(response.body)
	}

	private ResponseEntity<String> getAllWisdomPhrases() {
		restTemplate.getForEntity("/admin/wisdom", String)
	}

}