package damiano.printer

interface Printer {

	void println(Closure<Printable> printableSupplier)

	Image toImage()
}