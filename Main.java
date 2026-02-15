import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int choice = 0;
        Joueur j1 = new JoueurAI(1);
        Joueur j2 = new JoueurAI(2);
        while (choice > 3 || choice < 1){
            System.out.println("1. AI vs AI");
            System.out.println("2. Human vs AI");
            System.out.println("3. Human vs Human");
            System.out.print("Choose the game Mode: ");

            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1){
                j1 = new JoueurAI(1);
                j2 = new JoueurAI(2);

            } else if (choice == 2) {

                System.out.print("Player name: ");
                String name1 = scanner.nextLine();
                j1 = new Joueur(1, name1);
                j2 = new JoueurAI(2);

            } else if (choice == 3){

                System.out.print("Player1 name: ");
                String name1 = scanner.nextLine();
                System.out.print("Player2 name: ");
                String name2 = scanner.nextLine();
                j1 = new Joueur(1, name1);
                j2 = new Joueur(2, name2);

            } else {
                System.out.println("Invalid choice");
            } 
        }
        Joueur currentPlayer = j1;
        Joueur otherPlayer = j2;
        Joueur x;

        Game puissance = new Game(j1.getId(), j2.getId());
        boolean fin = false;
        puissance.seeGrille();
        while (!fin) {

            System.out.println("Joueur : " + currentPlayer.getNom());
            int numColonne;
            if (currentPlayer instanceof JoueurAI ){
                int depth;
                if (puissance.getMoveCount() <= 15){
                    depth = 9;
                } else if (puissance.getMoveCount() <= 25){
                    depth = 10;
                }else {
                    depth = 14;
                }

                System.out.println(depth);
                numColonne = ((JoueurAI) currentPlayer).getBestMove(puissance, depth);
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
