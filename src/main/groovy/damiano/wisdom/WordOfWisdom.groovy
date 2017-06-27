package damiano.wisdom

import java.awt.*
import java.security.MessageDigest

import damiano.printer.Printer
import groovy.transform.PackageScope
import groovy.util.logging.Slf4j

@Slf4j
class WordOfWisdom {

	public static final String AUTHOR_NAME = "~ Damiano Coelho"

	private final String wisdom

	private String key

	WordOfWisdom(String wisdom) {
		this.wisdom = wisdom.trim()
	}

	String getId() {
		return key()
	}

	String getSentence() {
		return wisdom
	}

	void printWith(Printer printer) {
		printWisdom(printer)

		printAuthor(printer)

	}

	@PackageScope
	String key() {
		if (!key) {
			key = generateKey()
		}
		return key
	}

	private String generateKey() {
		MessageDigest.getInstance('SHA-256')
				.digest(wisdom.bytes)
				.encodeHex()
				.toString()
	}

	private Object printWisdom(Printer printer) {
		wisdom.eachLine { text ->
			printWisdomLine(text, printer)
		}
	}

	private void printWisdomLine(text, printer) {
		log.info "print wisdom line"

		printer.println {
			text.ofSize(30).withColor(Color.GRAY)
		}
	}

	private printAuthor(Printer printer) {
		log.info "print author"

		printer.println {
			AUTHOR_NAME.ofSize(30).withColor(Color.GRAY).italic()
		}
	}
}
