package daggerok.simpleinmemorybroker.infrastructure.stomp

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
)
