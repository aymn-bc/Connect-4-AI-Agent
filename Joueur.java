import java.util.Scanner;

public class Joueur {
    private String nom;
    private int id;
    private int score;

    public Joueur(int id, String nom){
        this.nom = nom;
        this.id = id;
        score = 400;
    }

    public int choisirCoup(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("choisit une colonne: ");
        int colone = scanner.nextInt();
        return colone;
    }

    public void incrementerScore(){}
    public void decrementerScore(){}

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
