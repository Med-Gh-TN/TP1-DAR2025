package serverPackage;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server is waiting for a client connection on port 1234...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("A client has connected!");

            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            int numberFromClient = inputStream.read();
            System.out.println("Received number from client: " + numberFromClient);

            int result = numberFromClient * 2;
            System.out.println("Calculation result: " + result);

            outputStream.write(result);
            System.out.println("Sent result back to the client.");

        } catch (IOException e) {
            System.err.println("An error occurred in the server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}