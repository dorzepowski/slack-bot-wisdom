package damiano.wisdom

interface WordsOfWisdom {

	void add(WordOfWisdom wisdom)

	WordOfWisdom get(String id)

	long count()

	WordOfWisdom search(String searchPhrase)

	List<WordOfWisdom> toList()
}