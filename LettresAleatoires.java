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


    private static boolean peutFormerMot(String lettres, String mot) {
        StringBuilder lettresTemporaires = new StringBuilder(lettres);
        for (char c : mot.toCharArray()) {
            int index = lettresTemporaires.indexOf(String.valueOf(c));
            if (index == -1) {
                return false;
            }
            lettresTemporaires.deleteCharAt(index);
        }
        return true;
    }
}
