package damiano.slack.integration

import damiano.slack.wisdom.WordsOfWisdomFacade
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

	final WordsOfWisdomFacade wordsOfWisdomFacade;

	SlackIntegrationEndpoint(TokenValidator tokenValidator, WordsOfWisdomFacade wordsOfWisdomFacade) {
		this.tokenValidator = tokenValidator
		this.wordsOfWisdomFacade = wordsOfWisdomFacade
	}

	@RequestMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = POST)
	@ResponseBody
	SlackResponse demo(@RequestParam("token") String token, @RequestParam("text") String wisdom) {
		tokenValidator.validate(token)
		String imageName = wordsOfWisdomFacade.imageNameFor(wisdom)
		return new SlackResponse(imageName)
	}

}
