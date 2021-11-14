package daggerok.activemqbroker.infrastructure.stomp

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(BrokerProperties::class)
class BrokerPropertiesConfig

@ConstructorBinding
@ConfigurationProperties("broker")
data class BrokerProperties(
    val applicationDestinationPrefix: String,
    val destinationPrefix: String,
    val endpoint: String,
    val relayHost: String,
    val relayPort: Int,
    val clientLogin: String,
    val clientPasscode: String,
)
