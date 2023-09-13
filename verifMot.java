import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class verifMot {

    public static void verifierMot(String lettresDisponibles, char lettreCentrale, HashSet<String> dictionnaire, HashSet<String> motsPossibles) {        Scanner scanner = new Scanner(System.in);
        int score = 0;
        HashSet<String> motsJoues = new HashSet<>();  // Pour stocker les mots déjà joués
        int motsTrouves = 0;
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

            int pointsGagnes = 0; // Variable pour stocker les points gagnés lors de ce tour
            if (dictionnaire.contains(saisieUtilisateur)) {
                motsJoues.add(saisieUtilisateur);  // Ajouter le mot à la liste des mots déjà joués
                System.out.println("Le mot " + saisieUtilisateur + " existe dans le dictionnaire.");
                if (longueur == 4) {
                    pointsGagnes = 1;
                } else {
                    pointsGagnes = longueur;  // Pour les mots de 5 lettres ou plus, chaque lettre vaut un point.
                }

                if (saisieUtilisateur.contains(lettresDisponibles)) { // Vérifier si c'est un pangramme
                    pointsGagnes += 7;  // Bonus de 7 points pour un pangramme
                    System.out.println("Pangramme !");
                }

                score += pointsGagnes;
                System.out.println("+" + pointsGagnes + " points !");
                System.out.println("Votre score actuel est : " + score);
            } else {
                System.out.println("Le mot " + saisieUtilisateur + " n'existe pas dans le dictionnaire.");
            }

            if (motsPossibles.contains(saisieUtilisateur)) {
                motsTrouves++;
                double pourcentage = ((double) motsTrouves / motsPossibles.size()) * 100;
                String rang = determinerRang(pourcentage);
                System.out.println("Votre rang est : " + rang);
            }

            double pourcentage = ((double) motsTrouves / motsPossibles.size()) * 100;
            int barLength = 50; // Longueur de la barre de progression
            int completed = (int) (pourcentage / 100 * barLength);
            StringBuilder bar = new StringBuilder();
            for (int i = 0; i < barLength; i++) {
                if (i < completed) {
                    bar.append('=');
                } else {
                    bar.append('-');
                }
            }
            System.out.println("Progression: [" + bar + "] ");

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

    public static String determinerRang(double pourcentage) {
        if (pourcentage >= 70) return "Génie";
        if (pourcentage >= 50) return "Incroyable !";
        if (pourcentage >= 40) return "Génial !";
        if (pourcentage >= 25) return "Joli !";
        if (pourcentage >= 15) return "Solide";
        if (pourcentage >= 8) return "Pas mal";
        if (pourcentage >= 5) return "Ça monte !";
        if (pourcentage >= 2) return "Début encourageant";
        return "Débutant";
    }

    public static HashSet<String> motsPossibles(String lettres, char lettreCentrale, HashSet<String> dictionnaire) {
        HashSet<String> possibles = new HashSet<>();
        for (String mot : dictionnaire) {
            if (mot.length() >= 4 && mot.contains(String.valueOf(lettreCentrale)) && LettresAleatoires.peutFormerMot(lettres, mot)) {
                possibles.add(mot);
            }
        }
        return possibles;
    }

}
