package clientPackage;

import java.io.*;
import java.net.*;


public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {

            System.out.println("je suis connecté");

            OutputStream os = socket.getOutputStream();
            os.write(10);
            System.out.println("Envoyé le nombre 10 au serveur.");

            InputStream is = socket.getInputStream();
            int nb = is.read(); // Lecture de la réponse (un seul octet)

            System.out.println("la multiple de 10 est " + nb);



        } catch (IOException e) {
            System.err.println("Erreur de connexion ou de communication avec le serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}