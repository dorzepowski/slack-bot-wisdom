package damiano.wisdom

import groovy.transform.PackageScope

@PackageScope
class FileSystemWordsOfWisdomRepository implements WordsOfWisdom {

	@Delegate
	private final WordsOfWisdom original

	private final File storage

	FileSystemWordsOfWisdomRepository(WordsOfWisdom original, File storage) {
		this.original = original
		this.storage = storage
		loadSentences().each(original.&add)
	}

	@Override
	void add(WordOfWisdom wisdom) {
		original.add(wisdom)
		saveSentences()
	}

	private List<String> saveSentences() {
		storage.withPrintWriter { writer ->
			original.toList().collect({ it.sentence }).each(writer.&println)
		}
	}

	private List<WordOfWisdom> loadSentences() {
		storage.readLines().collect { new WordOfWisdom(it) }
	}
}