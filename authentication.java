package com.aquila.auth;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class AuthServer {

    private static UserStore store = new UserStore();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/health", exchange ->
                sendResponse(exchange, 200, "{\"status\":\"Auth Service Running\"}")
        );

        server.createContext("/register", exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                store.addUser("demo", "password");
                sendResponse(exchange, 201, "{\"message\":\"User registered\"}");
            }
        });

        server.createContext("/login", exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                boolean valid = store.validate("demo", "password");
                if (valid) {
                    sendResponse(exchange, 200, "{\"message\":\"Login successful\"}");
                } else {
                    sendResponse(exchange, 401, "{\"message\":\"Invalid credentials\"}");
                }
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Auth Service started on port 8080");
    }

    private static void sendResponse(HttpExchange exchange, int status, String body) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    }
}
