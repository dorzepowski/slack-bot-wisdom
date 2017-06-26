package damiano.wisdom

class WordsOfWisdomTestFactory {

	WordsOfWisdom localWordsOfWisdom() {
		new LocalWordsOfWisdomRepository()
	}

	WordsOfWisdom fileSystemWordsOfWisdom(File file) {
		new FileSystemWordsOfWisdomRepository(localWordsOfWisdom(), file)
	}

	WordsOfWisdom mongoWordsOfWisdom(Storage storage) {
		new MongoDbWordsOfWisdomRepository(localWordsOfWisdom(), storage)
	}
}
