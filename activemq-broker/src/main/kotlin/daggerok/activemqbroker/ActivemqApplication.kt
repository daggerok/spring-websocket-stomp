package daggerok.activemqbroker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ActivemqApplication

fun main(args: Array<String>) {
    runApplication<ActivemqApplication>(*args)
}
