package daggerok.rabbitmqbroker.users

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.AUTO
import javax.persistence.Id
import javax.persistence.Table
import org.springframework.data.jpa.repository.JpaRepository

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = AUTO)
    val id: Long? = null,

    val name: String = "",

    @org.hibernate.annotations.CreationTimestamp // @CreatedDate
    val createdAt: Instant? = null, // Instant.now(),

    @org.hibernate.annotations.UpdateTimestamp // @LastModifiedDate
    val updatedAt: Instant? = null, // Instant.now(),
)

interface Users : JpaRepository<User, Long>
