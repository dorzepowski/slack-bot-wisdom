package damiano.slack.image

import damiano.slack.wisdom.Wisdom
import groovy.transform.PackageScope
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

import static damiano.slack.image.Author.DAMIANO_COHELLO

@TypeChecked
@PackageScope
class DamianoCohelloImage implements WiseManImage {

	private final ImageFile imageFile

	DamianoCohelloImage(ImageFile imageFile) {
		this.imageFile = imageFile
	}

	@Override
	WisdomImage with(Wisdom wisdom) {
		return new WisdomImage(imageFile.image(), wisdom, DAMIANO_COHELLO)
	}
}
