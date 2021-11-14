package daggerok.activemqbroker.users

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Transactional(readOnly = true)
class UsersResource(private val users: Users) {

    @PostMapping("/api/users")
    @Transactional(readOnly = false)
    fun saveOrUpdateUser(@RequestBody command: SaveOrUpdateUserCommand) = let {
        val user =
            if (command.id == null) User(name = command.name)
            else users.findById(command.id)
                .orElseThrow { RuntimeException("Cannot update user") }
                .copy(name = command.name)
        users.save(user).toDocument()
    }

    @GetMapping("/api/users")
    fun getAllUsers(query: GetAllUsersQuery) =
        users.findAll().toDocument()
}
