package daggerok.simpleinmemorybroker.users

import daggerok.simpleinmemorybroker.messages.UserDTO
import java.time.Instant

// save message

data class SaveMessageCommand(
    val body: String,
    val senderId: Long? = null,
    val recipientsIds: Iterable<Long> = setOf(),
)

data class SaveMessageDocument(
    val message: MessageDTO,
)

// get all messages

class GetAllMessagesQuery

data class GetAllMessagesDocument(
    val messages: Iterable<MessageDTO> = setOf(),
)

// DTOs

data class MessageDTO(
    val id: Long? = null,
    val body: String,
    val sender: UserDTO? = null,
    val recipients: Iterable<UserDTO> = setOf(),
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null,
)
