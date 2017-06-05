package damiano.wisdom

import java.awt.*
import java.util.concurrent.ConcurrentHashMap

import damiano.printer.Image
import damiano.printer.Media
import damiano.printer.Printer
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class WordsOfWisdomFacade {

	private final Media media

	private final WordsOfWisdom wordsOfWisdom

	private final Map<String, String> DB = new ConcurrentHashMap()

	WordsOfWisdomFacade(
			WordsOfWisdom wordsOfWisdom,
			Media media
	) {
		this.wordsOfWisdom = wordsOfWisdom
		this.media = media
	}

	String wisdomIdFor(String text) {
		log.info("Wisdom to process: $text")
		WordOfWisdom wisdom = new WordOfWisdom(text)
		wordsOfWisdom.add(wisdom)

		DB.put(wisdom.id, text)

		return wisdom.id
	}

	Image wisdomImageBytesForId(String id) {
		WordOfWisdom wordOfWisdom = wordsOfWisdom.get(id)

		log.info "Read original image"
		Printer printer = media.newPrinter()

		wordOfWisdom.wisdom.eachLine { text ->
			log.info "print wisdom line"

			printer.println {
				text.ofSize(30).withColor(Color.GRAY)
			}
		}

		printer.println {
			log.info "print author"

			"~ Damiano Cohello".ofSize(30).withColor(Color.GRAY).italic()
		}


		Image image = printer.toImage()
		return image
	}

}
