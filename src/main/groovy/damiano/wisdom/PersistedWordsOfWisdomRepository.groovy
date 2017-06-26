package damiano.wisdom

import groovy.transform.PackageScope

@PackageScope
class PersistedWordsOfWisdomRepository implements WordsOfWisdom {

	@Delegate
	private final WordsOfWisdom original

	private final Storage storage

	PersistedWordsOfWisdomRepository(WordsOfWisdom original, Storage storage) {
		this.original = original
		this.storage = storage
		storage.findAll().each(original.&add)
	}

	@Override
	void add(WordOfWisdom wisdom) {
		original.add(wisdom)
		storage.save(wisdom)
	}

}
