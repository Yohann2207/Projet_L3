package Association;

import Controleur.Controleur;

public class Association {
    public static void main(String[] args) {
    	 Controleur controleur = new Controleur();
         //controleur.demarrer(); // Console
         new Vue.Authentification_IHM(controleur); // IHM
    }
}