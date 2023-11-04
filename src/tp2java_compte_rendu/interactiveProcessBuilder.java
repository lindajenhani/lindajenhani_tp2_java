package tp2java_compte_rendu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class interactiveProcessBuilder {
    public static void main(String[] args) {
        List<String> commands = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez un argument ou un paramètre : ");
        String userArgument = scanner.nextLine();

        // Créez la commande en utilisant l'argument fourni par l'utilisateur
        String command = "ping " + userArgument;
        commands.add(command);

        try {
            ProcessBuilder pb = new ProcessBuilder(commands);
            Process process = pb.start();

            // Récupérez le flux d'entrée (stdout) du processus
            InputStream inputStream = process.getInputStream();

            // Créez un BufferedReader pour lire le flux d'entrée
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Affichez la sortie du processus
            }

            // Assurez-vous de fermer le BufferedReader après utilisation
            reader.close();

            // Attendre la fin du processus
            int exitCode = process.waitFor();
            System.out.println("Le processus s'est terminé avec le code de sortie : " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
