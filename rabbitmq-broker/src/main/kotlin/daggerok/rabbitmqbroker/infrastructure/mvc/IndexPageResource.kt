package daggerok.rabbitmqbroker.infrastructure.mvc

import daggerok.rabbitmqbroker.messages.Messages
import mu.KLogging
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexPageResource(private val messages: Messages) {

    @GetMapping("/")
    fun index() = "index"

    private companion object : KLogging()
}
