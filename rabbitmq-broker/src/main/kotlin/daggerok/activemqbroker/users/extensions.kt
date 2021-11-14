package daggerok.activemqbroker.users

import daggerok.activemqbroker.messages.GetAllUsersDocument
import daggerok.activemqbroker.messages.SaveUserDocument
import daggerok.activemqbroker.messages.UserDTO

fun User.toDocument() =
    SaveUserDocument(user = toDTO())

fun User.toDTO(): UserDTO =
    UserDTO(id, name, createdAt, updatedAt)

fun Iterable<User>.toDocument() =
    GetAllUsersDocument(
        map { it.toDTO() }
    )

fun User?.toNullOrDTO(): UserDTO? =
    if (this == null) null
    else UserDTO(id, name, createdAt, updatedAt)

fun Iterable<User>?.toDTOs(): Iterable<UserDTO> =
    this?.map { it.toDTO() } ?: setOf()
