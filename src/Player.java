import java.awt.*;

public class Player extends Entity{
    int x,y;
    private final int SIZE = 21;
    public Player(int x,int y){
        super(x,y);

    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(x+2,y+2,SIZE,SIZE);
    }
}
