package damiano.wisdom

import org.springframework.core.io.ClassPathResource
import spock.lang.Specification

class GetImageForWisdomFromWordsOfWisdomFacadeTest extends Specification {

	def "provide id for wisdom text"() {
		given:
			String text = "some wise text"
			WordsOfWisdomFacade wordsOfWisdomFacade = new WordsOfWisdomFacade(new ClassPathResource("DC.png"))
		when:
			String id = wordsOfWisdomFacade.wisdomIdFor(text)
		then:
			id
	}

	def "stream image for received id"() {
		given:
			String text = "some wise text"
			WordsOfWisdomFacade wordsOfWisdomFacade = new WordsOfWisdomFacade(new ClassPathResource("DC.png"))
		when:
			String id = wordsOfWisdomFacade.wisdomIdFor(text)
			byte[] image = wordsOfWisdomFacade.wisdomImageBytesForId(id)
		then:
			image
			image.length
	}


}
