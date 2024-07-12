# WebSocket JWT Authentication Example

This repository contains a demonstration of WebSocket communication secured with JWT (JSON Web Token) authentication. It includes both server-side and client-side implementations to illustrate how to establish secure WebSocket connections using JWT tokens.

## Features

- **Server-side Implementation**: Uses Spring Boot to create a WebSocket server that handles JWT token authentication for incoming connections.
- **Client-side Implementation**: Provides a simple WebSocket client example using JavaScript to connect securely to the server.
- **JWT Token Generation**: Includes utilities to generate JWT tokens with configurable expiration times.
- **Secure Communication**: Ensures that all WebSocket connections are authenticated using JWT tokens, providing a secure channel for data exchange.

## Requirements

- Java Development Kit (JDK)
- Maven (for building and running the Spring Boot application)
- Node.js and npm (for running the client-side WebSocket example)

## Getting Started

### Running the Server

1. Clone this repository.
2. Navigate to the `server` directory.
3. Build the project using Maven: `mvn clean install`.
4. Run the Spring Boot application: `java -jar target/websocket-jwt-server.jar`.

### Running the Client

1. Navigate to the `client` directory.
2. Install dependencies: `npm install`.
3. Start the WebSocket client: `npm start`.

### Usage

- Access the WebSocket server endpoint from the client at `ws://localhost:8080/socket`.
- Authenticate using a JWT token in the `Authorization` header (e.g., `Bearer <token>`).
- Send and receive WebSocket messages securely.

### Configuration

- Customize JWT token expiration and secret key in the `application.properties` file.
- Adjust WebSocket endpoint URL and other client configurations in the client-side scripts.


### License

This project is licensed under the MIT License - see the LICENSE file for details.
