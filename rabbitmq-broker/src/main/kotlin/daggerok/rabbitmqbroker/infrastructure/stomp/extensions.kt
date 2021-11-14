package daggerok.rabbitmqbroker.infrastructure.stomp

import daggerok.rabbitmqbroker.messages.Message
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun SendMessageCommand.toEntity(): Message =
    Message(body = message)

fun Message.toDocument(): MessageDocument =
    MessageDocument(id, body, sender, recipients, createdAt.format(), updatedAt.format())

fun Instant?.format(): String =
    this?.atZone(ZoneId.systemDefault())
        ?.toLocalDateTime()
        ?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM"))
        ?: ""
