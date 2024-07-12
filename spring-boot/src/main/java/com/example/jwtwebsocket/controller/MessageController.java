package com.example.jwtwebsocket.controller;

import com.example.jwtwebsocket.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

@Controller
public class MessageController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private WebSocketClient webSocketClient;

    private final String FASTAPI_WS_URL = "ws://localhost:8000/ws";

    @PostConstruct
    public void sendMessageOnStartup() {
        String message = "Hello from Spring Boot";
        String jwtToken = jwtService.createToken(message);
        System.out.println("");
        System.out.println("Generated JWT Token: " + jwtToken);
        System.out.println("");

        try {
            WebSocketSession session = webSocketClient.doHandshake(
                    new TextWebSocketHandler() {
                        @Override
                        public void handleTextMessage(WebSocketSession session, TextMessage message) {
                            System.out.println("Received message: " + message.getPayload());
                        }
                    },
                    new WebSocketHttpHeaders(),
                    URI.create(FASTAPI_WS_URL)).get();

            session.sendMessage(new TextMessage(jwtToken));
            System.out.println("Message sent to FastAPI server");

            // Keep the connection open for a while
            Thread.sleep(30000); // Sleep for 30 seconds

            session.close();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            System.err.println("Failed to establish WebSocket connection: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Failed to send message: " + e.getMessage());
        }
    }
}