package damiano.slack.image

import java.awt.*
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

import damiano.slack.image.Image
import groovy.transform.PackageScope

@PackageScope
class ImageCopy implements Image {

	private final BufferedImage image
	private int nextTextXposition = 50
	private int nextTextYposition = 100

	ImageCopy(BufferedImage image) {
		this.image = image
	}

	@Override
	ImageText text(String text) {
		return new Text(text)
	}

	@Override
	void writeTo(File file) {
		ImageIO.write(image,"png",file)
	}

	private Image addText(Text text){
		def g = image.createGraphics()
		g.renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON)
		g.translate(nextTextXposition, nextTextYposition)
		text.draw(g)
		nextTextYposition += g.fontMetrics.height
		g.dispose()
		return this
	}

	private class Text implements ImageText {

		private static final Font FONT = new Font(Font.SERIF,Font.PLAIN,30)
		private static final Color COLOR = Color.GRAY

		private final boolean italic

		private final String value

		private Text(String value) {
			this.value = value
			this.italic = false
		}

		private Text(Text text, boolean italic) {
			this.value = text.value
			this.italic = italic
		}


		@Override
		ImageText italic() {
			return new Text(this, true)
		}

		@Override
		Image add() {
			return addText(this)
		}

		private void draw(Graphics2D g){
			g.color = COLOR
			g.font = italic ? FONT.deriveFont(Font.ITALIC) : FONT
			g.drawString(value,0,0)
		}
	}
}
