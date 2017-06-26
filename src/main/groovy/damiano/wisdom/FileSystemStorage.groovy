package damiano.wisdom

import groovy.transform.PackageScope

@PackageScope
class FileSystemStorage implements Storage {

	private final File file

	FileSystemStorage(File file) {
		this.file = file
	}

	@Override
	WordOfWisdom save(WordOfWisdom entity) {
		file.withWriterAppend { writer -> writer.println(entity.sentence) }
		return entity
	}

	@Override
	Iterable<WordOfWisdom> findAll() {
		file.readLines().collect { new WordOfWisdom(it) }
	}
}
