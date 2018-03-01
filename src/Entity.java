import java.awt.*;

public class Entity {

    int x,y;

    //25*25 grid of tiles
    final int SIZE = 22,GAP = 3;

    public Entity(int x,int y){
        setPosition(x,y);
    }

    public void setPosition(int x,int y){
        this.x = GAP+(x*(SIZE+GAP));
        this.y = GAP+(y*(SIZE+GAP));
    }

    public void paint(Graphics g){
        g.fillRect(x+GAP,y+GAP,SIZE,SIZE);
    }
}
