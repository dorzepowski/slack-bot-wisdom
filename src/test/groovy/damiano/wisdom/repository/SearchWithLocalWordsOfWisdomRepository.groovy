package damiano.wisdom.repository

import damiano.wisdom.WordOfWisdom
import damiano.wisdom.WordsOfWisdom
import damiano.wisdom.WordsOfWisdomTestFactory
import spock.lang.Shared
import spock.lang.Specification


class SearchWithLocalWordsOfWisdomRepository extends Specification {


	public static
	final String SEARCH_PHRASE_FOR_NOT_EXISTING_WISDOM = "SearchPhraseForNotExistingWisdom"

	@Shared
	static WordsOfWisdomTestFactory factory = new WordsOfWisdomTestFactory()

	WordsOfWisdom wordsOfWisdom = factory.localWordsOfWisdom()

	WordOfWisdom wisdom

	WordOfWisdom expectedWisdom

	void setup() {
		wisdom = new WordOfWisdom("something other")
		expectedWisdom = new WordOfWisdom("Wise text")
		wordsOfWisdom.add(wisdom)
		wordsOfWisdom.add(expectedWisdom)
	}


	def "find word of wisdom containing #what"(String what, String searchPhrase) {
		when:
			WordOfWisdom foundWisdom = wordsOfWisdom.search(searchPhrase)
		then:
			foundWisdom.id == expectedWisdom.id
		where:
			what                                     | searchPhrase
			'exactly search phrase'                  | 'Wise text'
			'as first word, one word search phrase'  | 'Wise'
			'ignoring case, one word search phrase'  | 'wIsE'
			'as second word, one word search phrase' | "text"
			'one word of search phrase'              | 'wise sentence'

	}

	def "don't find any existing word of wisdom if #what search text is provided"(String what, String searchPhrase) {
		when:
			WordOfWisdom foundWisdom = wordsOfWisdom.search(searchPhrase)
		then:
			!foundWisdom
		where:
			what            | searchPhrase
			"empty"         | ""
			"null"          | null
			"none matching" | SEARCH_PHRASE_FOR_NOT_EXISTING_WISDOM
	}


	def "find word of wisdom containing both search words from phrase"() {}


}