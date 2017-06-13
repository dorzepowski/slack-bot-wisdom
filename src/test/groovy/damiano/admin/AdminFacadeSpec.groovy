package damiano.admin

import damiano.wisdom.WordOfWisdom
import damiano.wisdom.WordsOfWisdom
import spock.lang.Specification

class AdminFacadeSpec extends Specification {


	@SuppressWarnings("GroovyAssignabilityCheck")
	def "batch store words of wisdom #words "(List<String> words) {
		given:
			WordsOfWisdom repository = Mock()
			AdminFacade adminFacade = new AdminFacade(repository)
		when:
			adminFacade.store(words)
		then:
			words.size() * repository.add(_)
		where:
			words                                          | _
			["wise word"]                                  | _
			["wise word", "other wise word"]               | _
			["wise word", "other wise word", "wiser word"] | _
	}

	def "load all of wisdom"() {
		given:
			WordsOfWisdom repository = Stub()
			def expectedWordsOfWisdom = [new WordOfWisdom("wise word"), new WordOfWisdom("other wise word")]
			repository.toList() >> expectedWordsOfWisdom
			AdminFacade adminFacade = new AdminFacade(repository)
		when:
			List<String> allWordsOfWisdom = adminFacade.listAllWisdomTexts()
		then:
			allWordsOfWisdom.size() == 2
			allWordsOfWisdom.containsAll(expectedWordsOfWisdom.sentence)


	}


}