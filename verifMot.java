import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class verifMot {

    public static void verifierMot(String lettresDisponibles, char lettreCentrale, HashSet<String> dictionnaire) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        HashSet<String> motsJoues = new HashSet<>();  // Pour stocker les mots déjà joués

        while (true) { // boucle infinie jusqu'à ce que l'utilisateur décide de quitter
            System.out.println("Lettres disponibles: " + lettresDisponibles);
            System.out.println("Veuillez saisir un mot de 4 lettres ou plus ou tapez 'QUITTER' pour sortir:");
            System.out.println("Tapez 'AFFICHER' pour afficher les mots que vous avez déjà joué");


            String saisieUtilisateur = scanner.nextLine().toUpperCase();

            if ("QUITTER".equals(saisieUtilisateur)) {
                System.out.println("Merci d'avoir joué !");
                break; // sortir de la boucle
            }

            if ("AFFICHER".equals(saisieUtilisateur)) {
                System.out.println("Mots déjà joués: " + motsJoues);
                continue;
            }

            if (motsJoues.contains(saisieUtilisateur)) {
                System.out.println("Ce mot a déjà été joué!");
                continue;
            }

            for (char c : saisieUtilisateur.toCharArray()) {
                if (!lettresDisponibles.contains(String.valueOf(c))) {
                    System.out.println("Le mot contient des lettres non disponibles. Veuillez utiliser uniquement les lettres tirées.");
                    continue; // retourne au début de la boucle
                }
            }

            int longueur = saisieUtilisateur.length();

            if (longueur < 4) {
                System.out.println("Le mot doit contenir 4 lettres ou plus.");
                continue; // retourne au début de la boucle
            }

            if (!saisieUtilisateur.contains(String.valueOf(lettreCentrale))) {
                System.out.println("Vous devez utiliser la lettre centrale : " + lettreCentrale);
                continue; // retourne au début de la boucle
            }

            if (dictionnaire.contains(saisieUtilisateur)) {
                motsJoues.add(saisieUtilisateur);  // Ajouter le mot à la liste des mots déjà joués
                System.out.println("Le mot " + saisieUtilisateur + " existe dans le dictionnaire.");
                if (longueur == 4) {
                    score += 1;  // 4 lettres valent 1 point
                } else {
                    score += longueur;  // Pour les mots de 5 lettres ou plus, chaque lettre vaut un point.
                }

                if (saisieUtilisateur.contains(lettresDisponibles)) { // Vérifier si c'est un pangramme
                    score += 7;  // Bonus de 7 points pour un pangramme
                }

                System.out.println("Votre score actuel est : " + score);
            } else {
                System.out.println("Le mot " + saisieUtilisateur + " n'existe pas dans le dictionnaire.");
            }
        }
        }



    public static HashSet<String> chargerMots(String fichier) {
        HashSet<String> hashSetMot = new HashSet<>();
        try (BufferedReader lecteur = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {
                hashSetMot.add(ligne.toUpperCase().trim());
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
        return hashSetMot;
    }


}
