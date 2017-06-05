package damiano.wisdom

import java.util.concurrent.ConcurrentHashMap

import groovy.transform.PackageScope
import org.springframework.stereotype.Service

@Service
@PackageScope
class LocalWordsOfWisdomRepository implements WordsOfWisdom {

	private final Map<String, WordOfWisdom> db = new ConcurrentHashMap()


	@Override
	void add(WordOfWisdom wisdom) {
		if (!wisdom) {
			throw new NullPointerException("Wisdom cannot be null")
		}
		db.put(wisdom.key(), wisdom)
	}

	@Override
	WordOfWisdom get(String id) {
		if (id) {
			return db.get(id) ?: throwNotFound()
		} else {
			throwNotFound()
		}
	}

	@Override
	long count() {
		return db.size()
	}

	private static def throwNotFound() {
		throw new NotFound()
	}
}
