import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class LettresAleatoires {

    public static String obtenirLettresValides(int n, HashSet<String> dictionnaire) {
        String lettresAleatoires;
        do {
            lettresAleatoires = obtenirLettresAleatoires(n);
        } while (!estEnsembleValide(lettresAleatoires, dictionnaire));
        return lettresAleatoires;
    }

    public static String obtenirLettresAleatoires(int n) {
        if (n > 26) {
            throw new IllegalArgumentException("Le nombre de lettres demandé est trop grand");
        }

        ArrayList<Character> alphabet = new ArrayList<>();
        for (char lettre = 'A'; lettre <= 'Z'; lettre++) {
            alphabet.add(lettre);
        }

        Collections.shuffle(alphabet);

        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < n; i++) {
            resultat.append(alphabet.get(i));
        }

        return resultat.toString();
    }

    public static boolean estEnsembleValide(String lettres, HashSet<String> dictionnaire) {
        for (String mot : dictionnaire) {
            if (mot.length() >= lettres.length() && peutFormerMot(lettres, mot)) {
                return true;
            }
        }
        return false;
    }


    public static boolean peutFormerMot(String lettres, String mot) {
        int[] compteLettres = new int[26]; // Un tableau pour compter les occurrences de chaque lettre dans les lettres tirées
        for (char c : lettres.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {  // Vérifie si la lettre est une majuscule
                compteLettres[c - 'A']++;
            } else if (c >= 'a' && c <= 'z') {  // vérifie si la lettre est une minuscule
                compteLettres[c - 'a']++;
            } // sinon ignore la lettre
        }

        // Vérifie si chaque lettre du mot peut être formée avec les lettres tirées
        for (char c : mot.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                if (--compteLettres[c - 'A'] < 0) {
                    return false;
                }
            } else if (c >= 'a' && c <= 'z') {
                if (--compteLettres[c - 'a'] < 0) {
                    return false;
                }
            } else {
                // caractères spéciaux
                return false;
            }
        }

        return true;
    }

}
