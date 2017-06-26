package damiano.wisdom.repository

import damiano.wisdom.Storage
import damiano.wisdom.WordOfWisdom
import damiano.wisdom.WordsOfWisdom
import damiano.wisdom.WordsOfWisdomTestFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@ActiveProfiles("mongo")
@DataMongoTest
class PersistWithMongoDbWordsOfWisdomRepository extends Specification {

	@Autowired
	Storage storage

	private WordsOfWisdom wordsOfWisdom


	void setup() {
		this.wordsOfWisdom = new WordsOfWisdomTestFactory().mongoWordsOfWisdom(storage)
	}

	def "New repository should contain the same number of words of wisdom as old one"() {
		given:
			wordsOfWisdom.add(new WordOfWisdom("Text"))
			int expectedCount = wordsOfWisdom.count()
		when:
			WordsOfWisdom newlyCreatedWordsOfWisdom = new WordsOfWisdomTestFactory().mongoWordsOfWisdom(storage)
		then:
			newlyCreatedWordsOfWisdom.count() == expectedCount
	}

	def "New repository should contain the same wisdom sentences as old one"() {
		given:
			def expectedSentences = ["Text", "Wise"]
			expectedSentences.collect({ new WordOfWisdom(it) }).each(wordsOfWisdom.&add)
		when:
			WordsOfWisdom newlyCreatedWordsOfWisdom = new WordsOfWisdomTestFactory().mongoWordsOfWisdom(storage)
			List<String> sentences = newlyCreatedWordsOfWisdom.toList().collect { it.sentence }
		then:
			sentences.sort() == expectedSentences.sort()
	}

}