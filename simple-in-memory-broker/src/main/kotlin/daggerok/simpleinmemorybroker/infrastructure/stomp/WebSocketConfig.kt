package daggerok.simpleinmemorybroker.infrastructure.stomp

import mu.KLogging
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig(private val brokerProperties: BrokerProperties) : WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry
            // JavaScript: stompClient.send('/stomp-application/messages', {}, json);
            .setApplicationDestinationPrefixes(brokerProperties.applicationDestinationPrefix)
            // JavaScript: stompClient.subscribe('/topic/messages', f);
            .enableSimpleBroker(brokerProperties.destinationPrefix)
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry
            // JavaScript: new SockJS('/stomp-websocket-endpoint');
            .addEndpoint(brokerProperties.endpoint)
            .withSockJS()
    }

    private companion object : KLogging()
}
