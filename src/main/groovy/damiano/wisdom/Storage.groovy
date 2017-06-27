package damiano.wisdom

interface Storage {

	Quote save(Quote entity)

	Iterable<Quote> findAll()
}