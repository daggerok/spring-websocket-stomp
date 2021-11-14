package daggerok.simpleinmemorybroker.messages

import daggerok.simpleinmemorybroker.GetAllMessagesDocument
import daggerok.simpleinmemorybroker.MessageDTO
import daggerok.simpleinmemorybroker.SaveMessageDocument
import daggerok.simpleinmemorybroker.users.toDTOs
import daggerok.simpleinmemorybroker.users.toNullOrDTO

fun Message.toDTO() =
    MessageDTO(id, body, sender.toNullOrDTO(), recipients.toDTOs(), createdAt, updatedAt)

fun Message.toDocument() =
    SaveMessageDocument(toDTO())

fun Iterable<Message>.toDocument() =
    GetAllMessagesDocument(
        messages = map { it.toDTO() }
    )