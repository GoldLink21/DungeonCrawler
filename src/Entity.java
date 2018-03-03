import java.awt.*;

public class Entity implements Move {

    double x,y;

    //25*25 grid of tiles
    final int SIZE = 21,GAP = 4;

    public Entity(int x,int y){setPosition(x,y);}

    @Override
    public void paint(Graphics g) {
        g.fillOval((int)(x+GAP/2),(int)(y+GAP/2),SIZE,SIZE);
    }

    @Override
    public void setPosition(double x, double y){
        this.x = x*(SIZE+GAP)+GAP/2;
        this.y = y*(SIZE+GAP)+GAP/2;
    }

    @Override
    public void move() {

    }

    @Override
    public Rectangle getBounds(){return new Rectangle((int)(x),(int)(y),SIZE,SIZE);}




}
