package damiano.slack.image

import damiano.slack.wisdom.Wisdom

interface WiseManImage {

	WisdomImage with(Wisdom wisdom)
}
