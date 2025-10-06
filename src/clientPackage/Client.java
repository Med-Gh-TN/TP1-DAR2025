package clientPackage;

import java.io.*;
import java.net.*;
import java.util.Scanner; // Importation pour la lecture au clavier

public class Client {

    public static void main(String[] args) {
        // a) Le Client est demandé de taper un entier x au clavier.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez taper un entier x : ");
        int x = scanner.nextInt();

        try (Socket socket = new Socket("localhost", 1234)) {
            System.out.println("Connecté au serveur.");

            // Outils pour envoyer et recevoir des données de type primitif (comme int)
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            // b) Le client envoie cet entier au serveur.
            dos.writeInt(x);
            System.out.println("Envoyé le nombre " + x + " au serveur.");

            // f) Le client reçoit le résultat.
            int resultat = dis.readInt();

            System.out.println("Réponse du serveur : " + x + " * 5 = " + resultat);

        } catch (IOException e) {
            System.err.println("Erreur de connexion ou de communication avec le serveur : " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close(); // Bonne pratique de fermer le scanner
        }
    }
}