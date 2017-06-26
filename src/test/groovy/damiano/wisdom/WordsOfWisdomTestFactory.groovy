package damiano.wisdom

import static org.mockito.Mockito.mock

class WordsOfWisdomTestFactory {

	WordsOfWisdom localWordsOfWisdom() {
		new LocalWordsOfWisdomRepository()
	}

	WordsOfWisdom fileSystemWordsOfWisdom(File file) {
		def storage = new FileSystemStorage(file)
		persistedWordsOfWisdom(storage)
	}


	WordsOfWisdom persistedWordsOfWisdom(Storage storage) {
		new PersistedWordsOfWisdomRepository(localWordsOfWisdom(), storage)
	}

	WordsOfWisdom persistedWordsOfWisdom() {
		def storage = mock(Storage)
		persistedWordsOfWisdom(storage)
	}
}
