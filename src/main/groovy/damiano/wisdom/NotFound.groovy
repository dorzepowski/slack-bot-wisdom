package damiano.wisdom

import org.springframework.web.bind.annotation.ResponseStatus

import static org.springframework.http.HttpStatus.NOT_FOUND

@ResponseStatus(value = NOT_FOUND)
class NotFound extends RuntimeException {
}
