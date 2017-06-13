package damiano.wisdom

import damiano.printer.BackgroundImage
import damiano.printer.Image
import damiano.printer.Printer
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class WordsOfWisdomFacade {

	private final BackgroundImage media

	private final WordsOfWisdom wordsOfWisdom

	WordsOfWisdomFacade(
			WordsOfWisdom wordsOfWisdom,
			BackgroundImage media
	) {
		this.wordsOfWisdom = wordsOfWisdom
		this.media = media
	}

	String wisdomIdFor(String text) {
		log.info("Wisdom to process: $text")
		WordOfWisdom wisdom = new WordOfWisdom(text)
		wordsOfWisdom.add(wisdom)
		return wisdom.id
	}

	Image wisdomImageBytesForId(String id) {
		WordOfWisdom wisdom = wordsOfWisdom.get(id)

		log.info "Read original image"
		Printer printer = media.newPrinter()

		wisdom.printWith(printer)

		Image image = printer.toImage()
		return image
	}

}
