import java.awt.*;

public class Enemy extends Entity {
    public Enemy(int x,int y){
        super(x,y);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.ORANGE);
        super.paint(g);
    }
}
