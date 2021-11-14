package daggerok.activemqbroker.infrastructure.stomp

import daggerok.activemqbroker.messages.Messages
import mu.KLogging
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class StompMessagingResource(private val messages: Messages) {

    @GetMapping("/")
    fun index() = "index"

    @MessageMapping("/messages") // stompClient.send('/stomp-application/messages', {}, json);
    @SendTo("/topic/messages") // stompClient.subscribe('/topic/messages', f);
    fun relay(command: SendMessageCommand): MessageDocument = run {
        val messageEntity = command.toEntity()
        val savedMessage = messages.save(messageEntity)
        logger.info { "saved: $savedMessage" }
        savedMessage.toDocument()
    }

    private companion object : KLogging()
}
