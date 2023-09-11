import java.util.HashSet;
import java.util.Random;

public class main {
    public static void main(String[] args) {
        System.out.println("Début du programme.");
        HashSet<String> dictionnaire = verifMot.chargerMots("mot_abeille.csv");
        String tirageLettres = LettresAleatoires.obtenirLettresValides(7, dictionnaire);
        char lettreCentrale = tirageLettres.charAt(new Random().nextInt(tirageLettres.length()));
        System.out.println("Lettres générées : " + tirageLettres);
        System.out.println("Lettre centrale : " + lettreCentrale);
        verifMot.verifierMot(tirageLettres, lettreCentrale, dictionnaire);    }
}
