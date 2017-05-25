package damiano.slack.wisdom

import groovy.transform.PackageScope

@PackageScope
class WordsOfWisdomFacadeTestFixture {


	private WordsOfWisdomFacade facadeInstance

	WordsOfWisdomFacade create() {
		this.facadeInstance = new WordsOfWisdomFacade()
		return facadeInstance
	}
}
