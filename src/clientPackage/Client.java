package clientPackage;

import java.io.*;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Veuillez entrer l'adresse IP du serveur : ");
            String serverIp = scanner.nextLine();
            int serverPort = 1234;

            Socket socket = new Socket(serverIp, serverPort);
            System.out.println("Connecté au serveur sur " + serverIp + ":" + serverPort);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            System.out.println("--- Calculatrice Réseau ---");
            System.out.println("1. Addition (+)");
            System.out.println("2. Soustraction (-)");
            System.out.println("3. Multiplication (*)");
            System.out.println("4. Division (/)");
            System.out.print("Choisissez une opération (1-4) : ");
            int choice = scanner.nextInt();

            System.out.print("Entrez le premier nombre : ");
            double num1 = scanner.nextDouble();
            System.out.print("Entrez le deuxième nombre : ");
            double num2 = scanner.nextDouble();

            dos.writeInt(choice);
            dos.writeDouble(num1);
            dos.writeDouble(num2);
            System.out.println("Données envoyées au serveur pour calcul.");

            double result = dis.readDouble();

            if (Double.isNaN(result)) {
                System.out.println("Erreur de calcul côté serveur (ex: division par zéro ou opération invalide).");
            } else {
                System.out.println("Résultat reçu du serveur : " + result);
            }

            socket.close();

        } catch (UnknownHostException e) {
            System.err.println("Hôte inconnu : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur de connexion ou de communication avec le serveur : " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Erreur de saisie. Veuillez entrer des nombres valides.");
        } finally {
            scanner.close();
        }
    }
}