package damiano.wisdom

import groovy.transform.PackageScope

@PackageScope
class FileSystemStorage implements Storage {

	private final File file

	FileSystemStorage(File file) {
		this.file = file
	}

	@Override
	Quote save(Quote quote) {
		file.withWriterAppend { writer -> writer.println(quote.text) }
		return quote
	}

	@Override
	Iterable<Quote> findAll() {
		file.readLines().collect { Quote.fromString(it) }
	}
}
