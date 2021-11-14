package daggerok.rabbitmqbroker.infrastructure.stomp

import daggerok.rabbitmqbroker.messages.Messages
import mu.KLogging
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class StompMessagingResource(private val messages: Messages) {

    @MessageMapping("/messages") // JavaScript: stompClient.send('/stomp-application/messages', {}, json);
    @SendTo("/topic/messages") // JavaScript: stompClient.subscribe('/topic/messages', f);
    fun relay(command: SendMessageCommand): MessageDocument = run {
        val messageEntity = command.toEntity()
        if (messageEntity.body.contains("error")) throw RuntimeException("Error on $messageEntity save")
        val savedMessage = messages.save(messageEntity)
        logger.info { "saved: $savedMessage" }
        savedMessage.toDocument()
    }

    @MessageExceptionHandler
    @SendTo("/topic/errors") // JavaScript: stompClient.subscribe('/topic/errors', f);
    fun handleError(e: Throwable) =
        (e.message ?: "Unexpected error").let {
            logger.warn(e) { it }
            ErrorDocument(it)
        }

    private companion object : KLogging()
}
