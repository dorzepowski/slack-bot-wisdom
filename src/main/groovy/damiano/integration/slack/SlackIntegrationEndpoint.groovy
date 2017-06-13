package damiano.integration.slack

import javax.servlet.http.HttpServletRequest

import damiano.integration.slack.token.TokenValidator
import damiano.printer.Image
import damiano.wisdom.WordsOfWisdom
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

	final WordsOfWisdom wordsOfWisdom

	SlackIntegrationEndpoint(TokenValidator tokenValidator, WordsOfWisdomFacade wordsOfWisdomFacade, WordsOfWisdom wordsOfWisdom) {
		this.tokenValidator = tokenValidator
		this.wordsOfWisdomFacade = wordsOfWisdomFacade
		this.wordsOfWisdom = wordsOfWisdom
	}

	@RequestMapping(path = "/wisdom", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = POST)
	@ResponseBody
	SlackResponse find(
			@RequestParam("token") String token,
			@RequestParam("text") String searchText, HttpServletRequest request) {
		tokenValidator.validate(token)

		String imageId = wordsOfWisdom.search(searchText).id

		return new SlackResponse(request, imageId)
	}

	@RequestMapping(path = "/wisdom/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = POST)
	@ResponseBody
	SlackResponse newWisdom(
			@RequestParam("token") String token,
			@RequestParam("text") String wisdomText, HttpServletRequest request) {
		tokenValidator.validate(token)

		String imageId = wordsOfWisdomFacade.wisdomIdFor(wisdomText)

		return new SlackResponse(request, imageId)
	}

	@GetMapping(path = "/wisdom/{imageId}", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	Resource wisdomImage(@PathVariable("imageId") String imageId) {
		Image image = wordsOfWisdomFacade.wisdomImageBytesForId(imageId)
		return new ByteArrayResource(image.toByteArray())
	}
}
