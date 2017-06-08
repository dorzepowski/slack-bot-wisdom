package damiano.printer

class PrintableStringApi {

	static Printable ofSize(String self, int size) {
		return new TextLine(self, size)
	}
}
