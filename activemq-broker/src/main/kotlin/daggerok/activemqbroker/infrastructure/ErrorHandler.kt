package daggerok.simpleinmemorybroker

import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler
    fun handle(e: Throwable) =
        (e.message ?: "Unexpected error").let {
            logger.warn(e) { it }
            ResponseEntity.badRequest().body(
                mapOf("error" to it)
            )
        }

    private companion object : KLogging()
}
