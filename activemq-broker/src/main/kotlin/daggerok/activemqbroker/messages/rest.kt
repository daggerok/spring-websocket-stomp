package daggerok.activemqbroker.messages

import daggerok.simpleinmemorybroker.GetAllMessagesQuery
import daggerok.simpleinmemorybroker.SaveMessageCommand
import daggerok.activemqbroker.users.Users
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Transactional(readOnly = true)
class Resource(private val users: Users, private val messages: Messages) {

    @PostMapping("/api/messages")
    @Transactional(readOnly = false)
    fun saveMessage(@RequestBody command: SaveMessageCommand) = let {
        val sender =
            if (command.senderId == null) null
            else users.findById(command.senderId).orElse(null)
        val recipients = command.recipientsIds
            .mapNotNull { users.findById(it).orElse(null) }
        val message = Message(body = command.body, sender = sender, recipients = recipients)
        messages.save(message).toDocument()
    }

    @GetMapping("/api/messages")
    fun getAllMessages(query: GetAllMessagesQuery) =
        messages.findAll().toDocument()
}
