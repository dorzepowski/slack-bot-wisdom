package damiano.slack.wisdom

import spock.lang.Ignore
import spock.lang.Specification

class RequestingAnyReadySentenceFromWordsOfWisdomFacade extends Specification {

	private static final String STORED_WISDOM_IMAGE_NAME = "1.png"

	private static final String SECOND_STORED_WISDOM_IMAGE_NAME = "2.png"

	private static final String NEW_WISDOM_IMAGE_NAME = "3.png"

	WordsOfWisdomFacade facade

	WordsOfWisdomFacadeTestFixture factory


	void setup() {
		factory = new WordsOfWisdomFacadeTestFixture()
		facade = factory.create()
	}


	def "return one of stored wisdom when receive empty wisdom text"() {
		given:
			def wisdom = ''
		when:
			String imageName = facade.imageNameFor(wisdom)
		then:
			imageName in [STORED_WISDOM_IMAGE_NAME, SECOND_STORED_WISDOM_IMAGE_NAME]
	}

	def "return one of stored wisdom when receive null wisdom text"() {
		given:
			def wisdom = null
		when:
			String imageName = facade.imageNameFor(wisdom)
		then:
			imageName in [STORED_WISDOM_IMAGE_NAME, SECOND_STORED_WISDOM_IMAGE_NAME]
	}

	def "return new wisdom image name when receive custom wisdom text"() {
		given:
			def wisdom = "very wise words"
		when:
			String imageName = facade.imageNameFor(wisdom)
		then:
			imageName == NEW_WISDOM_IMAGE_NAME
	}


	@Ignore("Not sure about assertions")
	def "search for ready words of wisdom if received empty wisdom"() {
		given:
			def wisdom = ''
		when:
			String imageName = facade.imageNameFor(wisdom)
		then:
			WordsOfWisdom
	}

	//create image for new wisdom ? maybe during downloading the picture????
	//create image for stored wisdom ? maybe during downloading the picture????
	//find wisdom in store when receive empty wisdom
	//add new wisdom to store when receive custom wisdom

}
