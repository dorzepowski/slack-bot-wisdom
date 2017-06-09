package damiano.wisdom.printing

import java.awt.*
import java.util.List

import damiano.printer.Decoration
import damiano.printer.Media
import damiano.printer.Printable
import damiano.printer.PrintableStringApi
import damiano.printer.Printer
import groovy.transform.PackageScope

@PackageScope
class MockPrinter implements Printer {

	private List<CapturingMedia> lines = []

	@Override
	void println(Closure<Printable> printableSupplier) {
		Printable printable = use(PrintableStringApi, printableSupplier)
		def line = new CapturingMedia()
		printable.printOn(line)
		lines << line
	}

	@Override
	damiano.printer.Image toImage() {
		return null
	}

	PrintAssertion onLine(int lineNumber) {
		return new PrintAssertion(lineNumber)
	}


	class PrintAssertion {

		final int lineIndex

		PrintAssertion(int lineNumber) {
			this.lineIndex = lineNumber - 1
		}

		void hasPrinted(String expectedText) {
			assert lines.size() > lineIndex
			assert lines.get(lineIndex).text == expectedText
		}
	}

	private class CapturingMedia implements DoNothingMedia {

		String text

		@Override
		Media.Text text(String text) {
			this.text = text
			return new DummyText()
		}
	}

	private class DummyText implements Media.Text {

		@Override
		Media.Text withColor(Color color) {
			return this
		}

		@Override
		Media.Text withSize(int size) {
			return this
		}

		@Override
		Media.Text withDecoration(Decoration decoration) {
			return this
		}

		@Override
		void print() {

		}
	}
}
