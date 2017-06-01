package damiano.slack.integration

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import damiano.wisdom.WordsOfWisdomFacade
import groovy.transform.TypeChecked
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import static org.springframework.web.bind.annotation.RequestMethod.POST

@TypeChecked
@RestController
class SlackIntegrationEndpoint {

	final TokenValidator tokenValidator

	final WordsOfWisdomFacade wordsOfWisdomFacade

	SlackIntegrationEndpoint(TokenValidator tokenValidator, WordsOfWisdomFacade wordsOfWisdomFacade) {
		this.tokenValidator = tokenValidator
		this.wordsOfWisdomFacade = wordsOfWisdomFacade
	}

	@RequestMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = POST)
	@ResponseBody
	SlackResponse demo(
			@RequestParam("token") String token,
			@RequestParam("text") String text, HttpServletRequest request) {
		tokenValidator.validate(token)

		String imageId = wordsOfWisdomFacade.wisdomIdFor(text)

		return new SlackResponse(request, imageId)
	}

	@GetMapping(path = "/wisdom/{imageId}", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	Resource wisdomImage(
			@PathVariable("imageId") String imageId, HttpServletRequest request, HttpServletResponse response) {
		return new ByteArrayResource(wordsOfWisdomFacade.wisdomImageBytesForId(imageId))
	}
}
