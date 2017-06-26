package damiano.wisdom

interface Storage {

	WordOfWisdom save(WordOfWisdom entity)

	Iterable<WordOfWisdom> findAll()
}