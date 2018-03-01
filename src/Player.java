import java.awt.*;

public class Player extends Entity{
    int x,y;
    public Player(int x,int y){
        super(x,y);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(x,y,25,25);
    }
}
