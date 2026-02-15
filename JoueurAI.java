import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JoueurAI extends Joueur{
    public JoueurAI(int id){
        super(id, "AI Player " + id );
    }

    // Planifier
    public int minimax(Game position, int depth, boolean maximizing, int alpha, int beta){
        int myId = getId();
        int oppId = 3 - myId;
        if (depth == 0) {
            return evaluatePosition(position, myId, oppId);
        }
        if (position.estFin()){
            return 0;
        }

        int nbColonne = position.getNbColonnes();

        if (maximizing){
            int best = Integer.MIN_VALUE;
            for (int col = 0; col < nbColonne; col++){
                int ligne = position.getLigneVideByColonne(col);
                if (ligne == -1) continue;

                // play move for maximizer
                position.setCoup(ligne, col, myId);

                int score;
                if (position.estGagnant(ligne, col, myId) >= 4) {
                    score = 100000 + depth; // immediate win is best
                } else {
                    score = minimax(position, depth - 1, false, alpha, beta);
                }

                // undo move
                position.setCoup(ligne, col, 0);

                best = Math.max(best, score);
                alpha = Math.max(alpha, best);
                if (alpha >= beta) break; // prune
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int col = 0; col < nbColonne; col++){
                int ligne = position.getLigneVideByColonne(col);
                if (ligne == -1) continue;

                // play opponent move
                position.setCoup(ligne, col, oppId);

                int score;
                if (position.estGagnant(ligne, col, oppId) >= 4){
                    score = -100000 - depth; // opponent immediate win is worst
                } else {
                    score = minimax(position, depth - 1, true, alpha, beta);
                }

                // undo
                position.setCoup(ligne, col, 0);

                best = Math.min(best, score);
                beta = Math.min(beta, best);
                if (beta <= alpha) break; // prune
            }
            return best;
        }
    }

    // Decider
    public int getBestMove(Game position, int searchDepth){
        int bestScore = Integer.MIN_VALUE;
        int myId = getId();
        List<Integer> bestMoves = new ArrayList<>();

        int nbColonne = position.getNbColonnes();
        for (int col = 0; col < nbColonne; col++){
            int ligne = position.getLigneVideByColonne(col);
            if (ligne == -1) continue;

            // play candidate move
            position.setCoup(ligne, col, myId);

            int score;
            if (position.estGagnant(ligne, col, myId) >= 4) {
                // immediate winning move
                position.setCoup(ligne, col, 0);
                return col;
            } else {
                score = minimax(position, searchDepth - 1, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }

            // undo
            position.setCoup(ligne, col, 0);

            if (score > bestScore){
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(col);
            } else if (score == bestScore) {
                bestMoves.add(col);
            }
        }

        System.out.println("best score: " + bestScore);
        position.setMoveCount(position.getMoveCount() + 1);
        int randomIndex = new Random().nextInt(bestMoves.size());
        return bestMoves.get(randomIndex);
    }

    public int evaluatePosition(Game position, int myId, int oppId){
        // make the columns weighted to focus more on central column
        int[] colWeight = {1, 2, 4, 5, 4, 2, 1};
        int[] lineWeight = {1, 2, 4, 8, 16, 32};
        int myScore = 0; 
        int oppScore = 0;

        int nbLigne = position.getNbLigne();
        int nbColonne = position.getNbColonnes();
        int[][] grille = position.getGrille();

        for (int i = 0; i < nbLigne; i++){
            for (int j = 0; j < nbColonne; j++){
                if (grille[i][j] == myId) myScore += colWeight[j] + lineWeight[i];
                else if (grille[i][j] == oppId) oppScore += (colWeight[j] + lineWeight[i]) * 2;
            }
        }
        
        return myScore - oppScore + threatDetection(position, myId, oppId);
    }

    public int threatDetection(Game position, int myId, int oppId){
        int score = 0;
        int nbLigne = position.getNbLigne();
        int nbColonne = position.getNbColonnes();
        int[][] grille = position.getGrille();

        for (int i = 0; i < nbLigne; i++){
            for (int j = 0; j < nbColonne; j++){
                if (j <= nbColonne - 4) {
                    score += evalWindow(grille[i][j], grille[i][j+1], grille[i][j+2], grille[i][j+3], myId, oppId);
                }
            
                if (i <= nbLigne - 4){ 
                    score += evalWindow(grille[i][j], grille[i+1][j], grille[i+2][j], grille[i+3][j], myId, oppId);
                }

                if (i <= nbLigne - 4 && j <= nbColonne - 4) {
                    score += evalWindow(grille[i][j], grille[i+1][j+1], grille[i+2][j+2], grille[i+3][j+3], myId, oppId);
                }

                if (i >= 3 && j <= nbColonne - 4) {
                    score += evalWindow(grille[i][j], grille[i-1][j+1], grille[i-2][j+2], grille[i-3][j+3], myId, oppId);
                }
            }
        }
        return score;
    }

    public int evalWindow(int a, int b, int c, int d, int myId, int oppId){
        int myColCount = 0;
        int oppColCount = 0;
        int emptyColCount = 0;
        
        int[] cols = {a, b, c, d};
        for (int col : cols){
            if (col == myId) myColCount++;
            else if (col == oppId) oppColCount++;
            else emptyColCount++;
        }
        if (myColCount > 0 && oppColCount > 0){
            return 0;
        }

        if (myColCount == 3 && emptyColCount == 1) return 100;
        if (myColCount == 2 && emptyColCount == 2) return 10;

        if (oppColCount == 3 && emptyColCount == 1) return -200;
        if (oppColCount == 2 && emptyColCount == 2) return -20;

        return 0;
    }

    // Executer est dans le super class (super.setCoup(ligne, colonne, id))
}