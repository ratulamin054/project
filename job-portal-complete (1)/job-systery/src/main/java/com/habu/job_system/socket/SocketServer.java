package com.habu.job_system.socket;

import java.io.*;
import java.net.*;

public class SocketServer {

    public static void start() {
        int port = 9090;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("✅ Socket server started on port " + port);
            while (true) {
                try {
                    Socket client = server.accept();
                    new Thread(() -> handle(client)).start();
                } catch (IOException ignored) {}
            }
        } catch (IOException e) {
            System.out.println("⚠️  Socket server could not start on port " + port + ": " + e.getMessage());
        }
    }

    private static void handle(Socket client) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            String line;
            while ((line = in.readLine()) != null) {
                out.println("Echo: " + line);
            }
        } catch (IOException ignored) {}
    }
}
