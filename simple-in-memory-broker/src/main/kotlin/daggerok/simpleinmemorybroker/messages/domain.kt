package daggerok.simpleinmemorybroker.messages

import daggerok.simpleinmemorybroker.users.User
import java.time.Instant
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.ConstraintMode.CONSTRAINT
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.AUTO
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.Lob
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.repository.JpaRepository

@Entity
@Table(name = "messages")
data class Message(

    @Id
    @GeneratedValue(strategy = AUTO)
    val id: Long? = null,

    @Lob
    val body: String = "",

    @ManyToOne(
        fetch = EAGER,
        optional = true,
        cascade = [PERSIST, MERGE], // [PERSIST, MERGE, REFRESH, DETACH], // [ALL],
    )
    @JoinColumn(
        unique = false,
        nullable = true,
        updatable = true,
        foreignKey = ForeignKey(
            name = "messages_sender_user_id_fk",
            value = CONSTRAINT,
        ),
    )
    val sender: User? = null,

    @ManyToMany(
        fetch = EAGER,
        cascade = [PERSIST, MERGE],
    )
    @JoinTable(
        name = "users_messages",
        uniqueConstraints = [
            UniqueConstraint(
                columnNames = ["message_id", "user_id"],
                name = "users_messages_unique_constraint",
            ),
        ],
        joinColumns = [
            JoinColumn(
                name = "message_id",
                foreignKey = ForeignKey(
                    name = "users_messages_message_id_fk",
                    value = CONSTRAINT,
                ),
            ),
        ],
        inverseJoinColumns = [
            JoinColumn(
                name = "user_id",
                foreignKey = ForeignKey(
                    name = "users_messages_recipient_user_id_fk",
                    value = CONSTRAINT,
                ),
            ),
        ],
    )
    val recipients: Collection<User> = setOf(),

    @CreationTimestamp // @CreatedDate
    val createdAt: Instant? = null, // Instant.now(),

    @UpdateTimestamp // @LastModifiedDate
    val updatedAt: Instant? = null, // Instant.now(),
)

interface Messages : JpaRepository<Message, Long>
