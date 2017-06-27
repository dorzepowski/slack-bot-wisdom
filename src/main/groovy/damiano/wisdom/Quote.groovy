package damiano.wisdom

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import groovy.transform.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "quotes")
@PackageScope
@Canonical
@CompileStatic
@ToString(includeNames = true, includeFields = true, includePackage = false)
class Quote {

	@Id
	String id

	@Field("text")
	String text

	@PersistenceConstructor
	Quote(String id, String text) {
		this.id = id
		this.text = text
	}

	private Quote(String text) {
		this.text = text
	}

	@PackageScope
	WordOfWisdom toWisdom() {
		return new WordOfWisdom(text)
	}

	@PackageScope
	static Quote fromWisdom(WordOfWisdom wisdom) {
		return new Quote(wisdom.id, wisdom.sentence)
	}

	static Quote fromString(String text) {
		return new Quote(text)
	}
}
