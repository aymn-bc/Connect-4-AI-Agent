public class Main {
    public static void main(String[] args) {

        Joueur j1 = new JoueurAI(1);
        Joueur j2 = new JoueurAI(2);
        Joueur currentPlayer = j1;
        Joueur otherPlayer = j2;
        Joueur x;

        Game puissance = new Game(j1.getId(), j2.getId());
        boolean fin = false;

        while (!fin) {

            System.out.println("Joueur : " + currentPlayer.getNom());
            int numColonne;
            if (currentPlayer instanceof JoueurAI ){
                numColonne = ((JoueurAI) currentPlayer).getBestMove(puissance, 11);
            }else {
                numColonne = currentPlayer.choisirCoup();
            }
            int numLigne = puissance.getLigneVideByColonne(numColonne);
            
            if (numLigne == -1){
                System.out.println("this column is full");
                continue;
            }

            if (numColonne >= puissance.getNbColonnes() || numLigne >= puissance.getNbLigne() ){
                System.out.println("overflow");
                continue;
            }

            puissance.setCoup(numLigne, numColonne, currentPlayer.getId());
            System.out.println(puissance);

            if (puissance.estGagnant(numLigne, numColonne, currentPlayer.getId()) >= 4) {

                System.out.println("\nGagnant : " + currentPlayer.getNom());
                currentPlayer.incrementerScore();
                otherPlayer.decrementerScore();
                fin = true;

            } else if (puissance.estFin()) {

                System.out.println(
                    "\nLa partie est nulle entre le joueur : "
                    + currentPlayer.getNom()
                    + " et le joueur : "
                    + otherPlayer.getNom()
                );
                fin = true;
            }
            puissance.seeGrille();
            x = currentPlayer;
            currentPlayer = otherPlayer;
            otherPlayer = x;
        }
    }
}
