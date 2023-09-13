import java.util.HashSet;
import java.util.Random;


public class main {
    public static void main(String[] args) {
        HashSet<String> dictionnaire = verifMot.chargerMots("mot_abeille.csv");
        String tirageLettres = LettresAleatoires.obtenirLettresValides(7, dictionnaire);
        char lettreCentrale = tirageLettres.charAt(new Random().nextInt(tirageLettres.length()));
        System.out.println("Lettres générées : " + tirageLettres);
        System.out.println("Lettre centrale : " + lettreCentrale);

        HashSet<String> possibles = verifMot.motsPossibles(tirageLettres, lettreCentrale, dictionnaire);
        System.out.println("Nombre total de mots possibles: " + possibles.size());

        verifMot.verifierMot(tirageLettres, lettreCentrale, dictionnaire, possibles);
    }
}
