package damiano.slack.integration

import groovy.json.JsonSlurper
import groovy.transform.PackageScope
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

@PackageScope
class Slack {

	private final TestRestTemplate restTemplate

	private SlackCommand lastCommand

	Slack(TestRestTemplate restTemplate) {
		this.restTemplate = restTemplate
	}


	SlackCommand command(String text) {
		lastCommand = new SlackCommand(text)
	}

	def read() {
		return lastCommand.readResponse()
	}

	class SlackCommand {

		private final String text

		private ResponseEntity<String> response

		private SlackCommand(String text) {
			this.text = text
		}

		Slack send() {
			response = restTemplate.postForEntity("/", data(), String.class)
			return Slack.this
		}

		private data() {
			MultiValueMap<String, String> data = new LinkedMultiValueMap<String, String>()
			data.add("token", "12345")
			data.add("text", text)
			return data
		}

		def readResponse() {
			new JsonSlurper().parseText(response.getBody())

		}

	}
}
