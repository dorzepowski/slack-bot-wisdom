package damiano.wisdom.repository

import damiano.wisdom.FileSystemWordsOfWisdomRule
import damiano.wisdom.WordOfWisdom
import damiano.wisdom.WordsOfWisdom
import org.junit.Rule
import spock.lang.Specification

class PersistWithLocalFileSystemPersistedWordsOfWisdomRepository extends Specification {

	@Rule
	FileSystemWordsOfWisdomRule wordsOfWisdomRule = new FileSystemWordsOfWisdomRule()


	def "New repository should contain the same number of words of wisdom as old one"() {
		given:
			WordsOfWisdom wordsOfWisdom = wordsOfWisdomRule.create()
			wordsOfWisdom.add(new WordOfWisdom("Text"))
			int expectedCount = wordsOfWisdom.count()
		when:
			WordsOfWisdom newlyCreatedWordsOfWisdom = wordsOfWisdomRule.create()
		then:
			newlyCreatedWordsOfWisdom.count() == expectedCount
	}

	def "New repository should contain the same wisdom sentences as old one"() {
		given:
			def expectedSentences = ["Text", "Wise"]
			WordsOfWisdom wordsOfWisdom = wordsOfWisdomRule.create()
			expectedSentences.collect({ new WordOfWisdom(it) }).each(wordsOfWisdom.&add)
		when:
			WordsOfWisdom newlyCreatedWordsOfWisdom = wordsOfWisdomRule.create()
			List<String> sentences = newlyCreatedWordsOfWisdom.toList().collect { it.sentence }
		then:
			sentences.sort() == expectedSentences.sort()
	}

}