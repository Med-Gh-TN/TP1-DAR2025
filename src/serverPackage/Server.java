package serverPackage;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        int port = 1234;

        try {
            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(ipAddress, port));

            System.out.println("Le serveur est démarré et écoute sur " + ipAddress + ":" + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Un client s'est connecté depuis : " + clientSocket.getRemoteSocketAddress());
                handleClient(clientSocket);
            }

        } catch (IOException e) {
            System.err.println("Une erreur est survenue sur le serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream())
        ) {

            int operationChoice = dis.readInt();
            double operand1 = dis.readDouble();
            double operand2 = dis.readDouble();

            System.out.println("Opération reçue: " + operationChoice + ", Opérande 1: " + operand1 + ", Opérande 2: " + operand2);

            double result = 0;

            switch (operationChoice) {
                case 1:
                    result = operand1 + operand2;
                    break;
                case 2:
                    result = operand1 - operand2;
                    break;
                case 3:
                    result = operand1 * operand2;
                    break;
                case 4:
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        result = Double.NaN;
                    }
                    break;
                default:
                    result = Double.NaN;
                    break;
            }

            dos.writeDouble(result);
            System.out.println("Résultat envoyé au client : " + result);

        } catch (IOException e) {
            System.err.println("Erreur de communication avec le client : " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Connexion avec le client terminée.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}