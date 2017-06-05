package damiano.printer

import groovy.transform.PackageScope

@PackageScope
class PrintableStringApi {

	static Printable ofSize(String self, int size) {
		return new TextLine(self, size)
	}
}
