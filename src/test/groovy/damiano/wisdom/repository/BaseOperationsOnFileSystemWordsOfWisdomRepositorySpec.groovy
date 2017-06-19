package damiano.wisdom.repository

import damiano.wisdom.FileSystemWordsOfWisdomRule
import damiano.wisdom.NotFound
import damiano.wisdom.WordOfWisdom
import damiano.wisdom.WordsOfWisdom
import org.junit.Rule
import spock.lang.Specification

class BaseOperationsOnFileSystemWordsOfWisdomRepositorySpec extends Specification {

	@Rule
	FileSystemWordsOfWisdomRule wordsOfWisdomRule = new FileSystemWordsOfWisdomRule()

	WordsOfWisdom wordsOfWisdom

	WordOfWisdom wisdom = new WordOfWisdom("Wise text")

	void setup() {
		wordsOfWisdom = wordsOfWisdomRule.create()
	}

	def "add one word of wisdom"() {
		given:
			long beforeInsert = wordsOfWisdom.count()
		when:
			wordsOfWisdom.add(wisdom)
		then:
			wordsOfWisdom.count() == (beforeInsert + 1)
	}

	def "retrieve added word of wisdom"() {
		given:
			wordsOfWisdom.add(wisdom)
		when:
			WordOfWisdom retrievedWisdom = wordsOfWisdom.get(wisdom.id)
		then:
			retrievedWisdom == wisdom
	}

	def "raise an error when someone try to add null wisdom"() {
		when:
			wordsOfWisdom.add(null)
		then:
			thrown(NullPointerException)
	}

	def "raise an error when someone try to get not existing wisdom"() {
		when:
			this.wordsOfWisdom.get("oigjajdlkj")
		then:
			thrown(NotFound)
	}

	def "raise an error when someone try to get wisdom by null id"() {
		when:
			wordsOfWisdom.get(null)
		then:
			thrown(NotFound)
	}

	def "convert empty words of wisdom to empty list of word of wisdom"() {
		when:
			List<WordOfWisdom> list = wordsOfWisdom.toList()
		then:
			list != null
			list.isEmpty()
	}

	def "convert words of wisdom to list of word of wisdom"() {
		given:
			wordsOfWisdom.add(wisdom)
		when:
			List<WordOfWisdom> list = wordsOfWisdom.toList()
		then:
			list.size() == 1
			list.contains(wisdom)
	}

}
