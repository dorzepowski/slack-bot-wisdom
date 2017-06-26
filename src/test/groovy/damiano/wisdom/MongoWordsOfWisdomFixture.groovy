package damiano.wisdom

import org.mockito.Mockito

class MongoWordsOfWisdomFixture {

	private final MongoDbStorage storage = Mockito.mock(MongoDbStorage)

	private
	final WordsOfWisdom repository = new WordsOfWisdomTestFactory().mongoWordsOfWisdom(storage)

	WordsOfWisdom repository() {
		repository
	}

}
