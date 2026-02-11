public class Position {
    private int posX;
    private int posY;

    public Position(int x, int y){
        posX = x;
        posY = y;
    }

    public void modifierPosition(int x1,int y1){
        posX = x1;
        posY = y1;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }
}
