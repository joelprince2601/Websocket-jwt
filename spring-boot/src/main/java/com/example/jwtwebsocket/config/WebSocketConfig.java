package com.example.jwtwebsocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.client.WebSocketClient;

@Configuration
public class WebSocketConfig {

    @Bean
    public WebSocketClient webSocketClient() {
        return new StandardWebSocketClient();
    }
}
