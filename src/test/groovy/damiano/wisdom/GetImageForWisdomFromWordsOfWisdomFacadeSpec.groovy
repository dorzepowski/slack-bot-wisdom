package damiano.wisdom

import damiano.printer.BackgroundImage
import damiano.printer.Image
import damiano.printer.Printable
import damiano.printer.Printer
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class GetImageForWisdomFromWordsOfWisdomFacadeSpec extends Specification {

	@Shared
	static String singleLineWisdom = "some wise text"

	@Shared
	static String multiLineWisdom = """some wise text 
									and another line""".stripIndent()

	@Shared
	static String multiLineWisdomWithUnneededNewLines = """
														some wise text 
														and another line
														""".stripIndent()

	WordsOfWisdomFacade wordsOfWisdomFacade = WordsOfWisdomFacade()

	private String text = "some wise text"


	def "provide id for wisdom text"() {
		when:
			String id = wordsOfWisdomFacade.wisdomIdFor(text)
		then:
			id
	}

	def "get image for given text"() {
		when:
			String id = wordsOfWisdomFacade.wisdomIdFor(text)
			byte[] image = wordsOfWisdomFacade.wisdomImageBytesForId(id).toByteArray()
		then:
			image
			image.length
	}

	@Unroll
	def "get image with #wisdomLinesNumber text line and author line for given #textType text"(String textType, String text, int wisdomLinesNumber) {
		when:
			String id = wordsOfWisdomFacade.wisdomIdFor(text)
			byte[] image = wordsOfWisdomFacade.wisdomImageBytesForId(id).toByteArray()
		then:
			image
			image.length == wisdomLinesNumber + 1
		where:
			textType                         | text                                | wisdomLinesNumber
			"single line"                    | singleLineWisdom                    | 1
			"multi line"                     | multiLineWisdom                     | 2
			"multiline with blank new lines" | multiLineWisdomWithUnneededNewLines | 2
	}


	@Unroll
	def "raise not found error when provide image id that #idType"(String idType, String id) {
		when:
			wordsOfWisdomFacade.wisdomImageBytesForId(id)
		then:
			thrown(NotFound)
		where:
			idType       | id
			"not exists" | "not existing id"
			"is null"    | null
			"is empty"   | ""
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
