package daggerok.rabbitmqbroker.messages

import java.time.Instant

// save or update user

data class SaveOrUpdateUserCommand(
    val id: Long? = null,
    val name: String,
)

data class SaveUserDocument(
    val user: UserDTO,
)

// get all users

class GetAllUsersQuery

data class GetAllUsersDocument(
    val users: Iterable<UserDTO> = listOf()
)

// DTOs

data class UserDTO(
    val id: Long? = null,
    val name: String,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null,
)
