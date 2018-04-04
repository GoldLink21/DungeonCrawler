import java.awt.*;
import java.awt.Graphics2D;

public class Player extends Entity{
    int x,y;

    private final int SIZE = 21,SPEED=4,GAP=4;
    public Player(int x,int y){
        super(Color.BLUE,x,y,21,21);
        rot=0;
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(Math.toRadians(rot));
        g.setColor(Color.BLUE);
        g.fillOval(x+GAP/2,y+GAP/2,SIZE,SIZE);
        g.setColor(Color.BLACK);
        g.drawOval(x+GAP/2,y+GAP/2,SIZE,SIZE);
        g2d.rotate(Math.toRadians(-rot));
    }

    @Override
    public void move(){
        if(Data.isUp()){
            y+=SPEED;
        }if(Data.isDown()){
                y-=SPEED;
        }if(Data.isRight()){
            x+=SPEED;
        }if(Data.isLeft()){
            y-=SPEED;
        }

    }
}
