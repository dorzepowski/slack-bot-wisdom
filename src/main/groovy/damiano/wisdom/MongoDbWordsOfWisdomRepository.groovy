package damiano.wisdom

import groovy.transform.PackageScope

@PackageScope
class MongoDbWordsOfWisdomRepository implements WordsOfWisdom {

	@Delegate
	private final WordsOfWisdom original

	private final Storage storage

	MongoDbWordsOfWisdomRepository(WordsOfWisdom original, Storage storage) {
		this.original = original
		this.storage = storage
		loadSentences().each(original.&add)
	}

	@Override
	void add(WordOfWisdom wisdom) {
		original.add(wisdom)
		storage.save(wisdom)
	}

	private Iterable<WordOfWisdom> loadSentences() {
		storage.findAll()
	}


}
