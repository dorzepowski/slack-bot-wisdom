package damiano.slack.integration

import groovy.transform.TypeChecked
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import static org.springframework.web.bind.annotation.RequestMethod.POST

@TypeChecked
@RestController
class SlackIntegrationEndpoint {

	final TokenValidator tokenValidator


	SlackIntegrationEndpoint(TokenValidator tokenValidator) {
		this.tokenValidator = tokenValidator
	}

	@RequestMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = POST)
	@ResponseBody
	SlackResponse demo(@RequestParam("token") String token, @RequestParam("text") String text) {
		tokenValidator.validate(token)




		return new SlackResponse(text)
	}

	@RequestMapping(path = "/test", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = POST)
	@ResponseBody
	String test(@RequestParam("token") String token, @RequestParam("text") String text) {

		return "{ 'token':'$token', 'text':'$text' }"
	}

}
