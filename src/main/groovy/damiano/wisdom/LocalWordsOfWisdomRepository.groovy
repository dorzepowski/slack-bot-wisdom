package damiano.wisdom

import java.util.concurrent.ConcurrentHashMap

import groovy.transform.PackageScope

@PackageScope
class LocalWordsOfWisdomRepository implements WordsOfWisdom {

	private final Map<String, WordOfWisdom> db = new ConcurrentHashMap()

	private final Map<String, List<WordOfWisdom>> index = new ConcurrentHashMap().withDefault { [] }


	@Override
	void add(WordOfWisdom wisdom) {
		if (!wisdom) {
			throw new NullPointerException("Wisdom cannot be null")
		}
		save(wisdom)
		addToIndex(wisdom)
	}

	void addToIndex(WordOfWisdom wordOfWisdom) {
		wordOfWisdom.sentence.tokenize().each {
			index.get(it.toLowerCase()).add(wordOfWisdom)
		}
	}

	private WordOfWisdom save(WordOfWisdom wisdom) {
		db.put(wisdom.key(), wisdom)
	}

	@Override
	WordOfWisdom get(String id) {
		if (id) {
			return db.get(id) ?: throwNotFound()
		} else {
			return throwNotFound()
		}
	}

	@Override
	long count() {
		return db.size()
	}

	@Override
	WordOfWisdom search(String searchPhrase) {
		if (searchPhrase) {
			return searchPhrase.tokenize().collectMany {
				index.get(it.toLowerCase())
			}.<WordOfWisdom> find()
		} else {
			return null
		}
	}

	@Override
	List<WordOfWisdom> toList() {
		return db.values().toList()
	}

	private static def throwNotFound() {
		throw new NotFound()
	}
}
