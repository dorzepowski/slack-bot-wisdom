package damiano.integration

import groovy.transform.PackageScope
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@PackageScope
@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidToken extends RuntimeException {


}
