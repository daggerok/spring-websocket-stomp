package daggerok.simpleinmemorybroker.infrastructure.rest

import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

data class ErrorDocument(
    val error: String,
)

@RestControllerAdvice
class RestApiErrorHandler {

    @ExceptionHandler
    fun handleError(e: Throwable) =
        (e.message ?: "Unexpected error").let {
            logger.warn(e) { it }
            ResponseEntity.badRequest().body(ErrorDocument(it))
        }

    private companion object : KLogging()
}
