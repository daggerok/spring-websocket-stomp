package daggerok.rabbitmqbroker.messages

import daggerok.rabbitmqbroker.users.GetAllMessagesDocument
import daggerok.rabbitmqbroker.users.MessageDTO
import daggerok.rabbitmqbroker.users.SaveMessageDocument
import daggerok.rabbitmqbroker.users.toDTOs
import daggerok.rabbitmqbroker.users.toNullOrDTO

fun Message.toDTO() =
    MessageDTO(id, body, sender.toNullOrDTO(), recipients.toDTOs(), createdAt, updatedAt)

fun Message.toDocument() =
    SaveMessageDocument(toDTO())

fun Iterable<Message>.toDocument() =
    GetAllMessagesDocument(
        messages = map { it.toDTO() }
    )
