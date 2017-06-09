package damiano.wisdom

import damiano.printer.BackgroundImage
import damiano.printer.Image
import damiano.printer.Printable
import damiano.printer.Printer
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
		new WordsOfWisdomFacade(new LocalWordsOfWisdomRepository(), new StubBackgroundImage())
	}

	class StubBackgroundImage implements BackgroundImage {

		@Override
		Printer newPrinter() {
			return new StubPrinter()
		}
	}

	class StubPrinter implements Printer {

		int linesNumber

		@Override
		void println(Closure<Printable> printableSupplier) {
			linesNumber++
		}

		@Override
		Image toImage() {
			return new StubImage()
		}

		class StubImage implements Image {

			@Override
			byte[] toByteArray() {
				return new byte[linesNumber]
			}
		}
	}
}
