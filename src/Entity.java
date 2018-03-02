import java.awt.*;

public class Entity {

    int x,y;

    //25*25 grid of tiles
    final int SIZE = 21,GAP = 4;

    public Entity(int x,int y){setPosition(x,y);}

    public void setPosition(int x,int y){
        this.x = x*(SIZE+GAP)+GAP/2;
        this.y = y*(SIZE+GAP)+GAP/2;
    }

    public Rectangle getBounds(){return new Rectangle(x+GAP/2,y+GAP/2,SIZE,SIZE);}

    public void paint(Graphics g){g.fillOval(x+GAP/2,y+GAP/2,SIZE,SIZE);}


}
