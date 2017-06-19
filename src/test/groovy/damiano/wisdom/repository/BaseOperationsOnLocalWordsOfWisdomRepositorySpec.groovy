package damiano.wisdom.repository

import damiano.wisdom.NotFound
import damiano.wisdom.WordOfWisdom
import damiano.wisdom.WordsOfWisdom
import damiano.wisdom.WordsOfWisdomTestFactory
import spock.lang.Shared
import spock.lang.Specification

class BaseOperationsOnLocalWordsOfWisdomRepositorySpec extends Specification {

	@Shared
	static WordsOfWisdomTestFactory factory = new WordsOfWisdomTestFactory()

	WordsOfWisdom wordsOfWisdom = factory.localWordsOfWisdom()

	private WordOfWisdom wisdom = new WordOfWisdom("Wise text")


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
