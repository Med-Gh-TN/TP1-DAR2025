package serverPackage;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Le serveur attend une connexion client sur le port 1234...");

            // Le serveur attend indéfiniment une connexion client
            Socket clientSocket = serverSocket.accept();
            System.out.println("Un client s'est connecté !");

            // Outils pour recevoir et envoyer des données de type primitif (comme int)
            InputStream inputStream = clientSocket.getInputStream();
            DataInputStream dis = new DataInputStream(inputStream);
            OutputStream outputStream = clientSocket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(outputStream);

            // c) Le serveur, qui était en attente de ce nombre, le reçoit.
            int numberFromClient = dis.readInt();
            System.out.println("Nombre reçu du client : " + numberFromClient);

            // d) Le serveur calcule par exemple le produit x*5.
            int result = numberFromClient * 5;
            System.out.println("Résultat du calcul : " + result);

            // e) Le serveur envoie le résultat au client.
            dos.writeInt(result);
            System.out.println("Résultat envoyé au client.");

            // Fermeture de la socket client une fois la transaction terminée
            clientSocket.close();
            System.out.println("Connexion avec le client terminée.");

        } catch (IOException e) {
            System.err.println("Une erreur est survenue sur le serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}