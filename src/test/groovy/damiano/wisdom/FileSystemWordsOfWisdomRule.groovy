package damiano.wisdom

import org.junit.rules.TemporaryFolder
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class FileSystemWordsOfWisdomRule implements TestRule {

	private File dbFile

	private WordsOfWisdomTestFactory factory = new WordsOfWisdomTestFactory()

	private TemporaryFolder temporaryFolder = new TemporaryFolder()

	WordsOfWisdom create() {
		return factory.fileSystemWordsOfWisdom(dbFile)
	}

	@Override
	Statement apply(Statement base, Description description) {
		temporaryFolder.apply(prepareFactory(base, description), description)
	}

	private prepareFactory(Statement base, Description description) {
		return {
			createDbFile()
			base.evaluate()
		} as Statement
	}

	private createDbFile() {
		dbFile = temporaryFolder.newFile("db.json")
	}

}
