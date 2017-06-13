package damiano.admin

import damiano.wisdom.WordOfWisdom
import damiano.wisdom.WordsOfWisdom
import groovy.transform.PackageScope

class AdminFacade {

	private final WordsOfWisdom wordsOfWisdom

	@PackageScope
	AdminFacade(WordsOfWisdom wordsOfWisdom) {
		this.wordsOfWisdom = wordsOfWisdom
	}

	void store(List<String> wisdomFeed) {
		wisdomFeed.each { save(it) }
	}

	private void save(String wisdom) {
		WordOfWisdom wordOfWisdom = new WordOfWisdom(wisdom)
		wordsOfWisdom.add(wordOfWisdom)
	}

	List<String> listAllWisdomTexts() {
		return wordsOfWisdom.toList().collect { it.sentence }
	}
}
