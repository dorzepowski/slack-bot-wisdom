package damiano.wisdom

class WordsOfWisdomTestFactory {

	WordsOfWisdom localWordsOfWisdom() {
		new LocalWordsOfWisdomRepository()
	}

	WordsOfWisdom fileSystemWordsOfWisdom(File file) {
		new FileSystemWordsOfWisdomRepository(localWordsOfWisdom(), file)
	}

}
