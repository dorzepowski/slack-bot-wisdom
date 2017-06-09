package damiano.wisdom

import javax.imageio.ImageIO

import damiano.printer.BackgroundImage
import damiano.printer.BufferedImagePrinter
import org.springframework.core.io.ClassPathResource
import spock.lang.Specification

class GetImageForWisdomFromWordsOfWisdomFacadeTest extends Specification {

	def "provide id for wisdom text"() {
		given:
			String text = "some wise text"
			WordsOfWisdomFacade wordsOfWisdomFacade = WordsOfWisdomFacade()
		when:
			String id = wordsOfWisdomFacade.wisdomIdFor(text)
		then:
			id
	}

	def "get image for received id"() {
		given:
			String text = "some wise text"
			WordsOfWisdomFacade wordsOfWisdomFacade = WordsOfWisdomFacade()
		when:
			String id = wordsOfWisdomFacade.wisdomIdFor(text)
			byte[] image = wordsOfWisdomFacade.wisdomImageBytesForId(id).toByteArray()
		then:
			image
			image.length
	}

	private WordsOfWisdomFacade WordsOfWisdomFacade() {
		new WordsOfWisdomFacade(new LocalWordsOfWisdomRepository(), (BackgroundImage) {
			new BufferedImagePrinter(ImageIO.read(new ClassPathResource("dc-background.png").inputStream))
		})
	}

}
