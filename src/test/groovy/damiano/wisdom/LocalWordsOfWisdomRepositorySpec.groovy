package damiano.wisdom

import spock.lang.Specification

class LocalWordsOfWisdomRepositorySpec extends Specification {

	def "add one word of wisdom"() {
		given:
			WordOfWisdom wisdomToAdd = new WordOfWisdom("Wise text")
			WordsOfWisdom wordsOfWisdom = new LocalWordsOfWisdomRepository()
			long beforeInsert = wordsOfWisdom.count()
		when:
			wordsOfWisdom.add(wisdomToAdd)
		then:
			wordsOfWisdom.count() == (beforeInsert + 1)
	}

	def "retrieve added word of wisdom"() {
		given:
			WordOfWisdom wisdomToAdd = new WordOfWisdom("Wise text")
			WordsOfWisdom wordsOfWisdom = new LocalWordsOfWisdomRepository()
			wordsOfWisdom.add(wisdomToAdd)
		when:
			WordOfWisdom retrievedWisdom = wordsOfWisdom.get(wisdomToAdd.id)
		then:
			retrievedWisdom == wisdomToAdd
	}

	def "raise an error when someone try to add null wisdom"() {
		given:
			WordsOfWisdom wordsOfWisdom = new LocalWordsOfWisdomRepository()
		when:
			wordsOfWisdom.add(null)
		then:
			thrown(NullPointerException)
	}

	def "raise an error when someone try to get not existing wisdom"() {
		given:
			WordsOfWisdom wordsOfWisdom = new LocalWordsOfWisdomRepository()
		when:
			wordsOfWisdom.get("oigjajdlkj")
		then:
			thrown(NotFound)
	}


	def "raise an error when someone try to get wisdom by null id"() {
		given:
			WordsOfWisdom wordsOfWisdom = new LocalWordsOfWisdomRepository()
		when:
			wordsOfWisdom.get(null)
		then:
			thrown(NotFound)
	}

}
