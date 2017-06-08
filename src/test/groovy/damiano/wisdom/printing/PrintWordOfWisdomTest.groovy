package damiano.wisdom.printing

import damiano.wisdom.WordOfWisdom
import spock.lang.Specification
import spock.lang.Unroll

class PrintWordOfWisdomTest extends Specification {

	static final String expectedFirstLine = "wise words"

	static final String expectedSecondLine = "multiple"

	static final String expectedThirdLine = "lines"

	static final String authorLine = "~ Damiano Cohello"

	static final String singleLineWisdom = expectedFirstLine

	static final String multiLineWisdom = """
									$expectedFirstLine
									$expectedSecondLine
									$expectedThirdLine
									""".stripIndent()

	MockPrinter printer = new MockPrinter()


	@Unroll
	def "print #what of word of wisdom"(String what, String text, int lineNumber, String expectedText) {
		given:
			WordOfWisdom wisdom = new WordOfWisdom(text)
		when:
			wisdom.printWith(printer)
		then:
			printer.onLine(lineNumber).hasPrinted(expectedText)
		where:
			what                                  | text             | lineNumber | expectedText
			"single line text"                    | singleLineWisdom | 1          | expectedFirstLine
			"author line text"                    | singleLineWisdom | 2          | authorLine
			"first line from multiple line text"  | multiLineWisdom  | 1          | expectedFirstLine
			"second line from multiple line text" | multiLineWisdom  | 2          | expectedSecondLine
			"third line from multiple line text"  | multiLineWisdom  | 3          | expectedThirdLine
			"author line from multiple line text" | multiLineWisdom  | 4          | authorLine
	}


}
