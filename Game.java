public class Game{
    private int[][] grille;
    private int nbLigne = 6;
    private int nbColonnes = 7;
    private int idJ1;
    private int idJ2;


    public Game(int idJ1,int idJ2){
        grille = new int[nbLigne][nbColonnes];
        this.idJ1 = idJ1;
        this.idJ2 = idJ2;
    }

    public int estGagnant(int ligne, int colonne, int id) {
        int counter = 1;
        int i = 1;

        // vertical
        
        while (ligne + i < nbLigne && grille[ligne + i][colonne] == id) {
            counter++;
            i++;
        }

        if (counter >= 4) return counter;
        counter = 1;

        // horizontal
        int j = 1;
        while (colonne + j < nbColonnes && grille[ligne][colonne + j] == id) {
            counter++;
            j++;
        }

        j = 1;
        while (colonne - j >= 0 && grille[ligne][colonne - j] == id) {
            counter++;
            j++;
        }

        if (counter >= 4) return counter;
        counter = 1;

        // diagonal 1
        i = 1;
        j = 1;
        while (ligne + i < nbLigne && colonne + j < nbColonnes
                && grille[ligne + i][colonne + j] == id) {
            counter++;
            i++;
            j++;
        }

        i = 1;
        j = 1;
        while (ligne - i >= 0 && colonne - j >= 0
                && grille[ligne - i][colonne - j] == id) {
            counter++;
            i++;
            j++;
        }

        if (counter >= 4) return counter;
        counter = 1;

        // diagonal 2
        i = 1;
        j = 1;
        while (ligne - i >= 0 && colonne + j < nbColonnes
                && grille[ligne - i][colonne + j] == id) {
            counter++;
            i++;
            j++;
        }

        i = 1;
        j = 1;
        while (ligne + i < nbLigne && colonne - j >= 0
                && grille[ligne + i][colonne - j] == id) {
            counter++;
            i++;
            j++;
        }

        if (counter >= 4) return counter;

        return counter;
    }


    public boolean estFin(){
        int i = 0;
        while(i < nbColonnes){
            if (grille[0][i] == 0){
                return false;
            }
            i++;
        }
        return true;
    }

    public boolean setCoup(int colonne, int idJoueur){
        int ligne = getLigneVideByColonne(colonne);
        if (ligne == -1) return false;
        grille[ligne][colonne] = idJoueur;
        return true;
    }


    public int getLigneVideByColonne(int colonne) {
        for (int i = nbLigne-1; i >= 0; i--){
            if (grille[i][colonne] == 0){
                return i;
            }
        }
        return -1;
    }

    public void setCoup(int ligne, int colonne, int id) {
        grille[ligne][colonne] = id;
    }

    public void seeGrille(){
        for (int i = 0; i < nbLigne; i++){
            for (int j = 0; j < nbColonnes; j++){
                System.out.print(grille[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public int getNbLigne() {
        return nbLigne;
    }
}