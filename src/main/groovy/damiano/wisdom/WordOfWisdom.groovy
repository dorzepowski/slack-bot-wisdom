package damiano.wisdom

import java.awt.*
import java.security.MessageDigest

import damiano.printer.Printer
import groovy.transform.PackageScope
import groovy.util.logging.Slf4j
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Slf4j
@Document(collection = "quotes")
class WordOfWisdom {

	public static final String AUTHOR_NAME = "~ Damiano Cohello"

	private final String wisdom

	private String key

	@PersistenceConstructor
	WordOfWisdom(String wisdom) {
		this.wisdom = wisdom.trim()
	}

	@Id
	String getId() {
		return key()
	}

	@Field("text")
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
