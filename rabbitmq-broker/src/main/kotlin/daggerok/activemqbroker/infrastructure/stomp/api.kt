package daggerok.activemqbroker.infrastructure.stomp

import daggerok.activemqbroker.users.User

data class SendMessageCommand(
    val message: String = "",
)

data class MessageDocument(
    val id: Long? = null,
    val message: String = "",
    val sender: User? = null,
    val recipients: Collection<User> = setOf(),
    val createdAt: String = "",
    val updatedAt: String = "",
)
