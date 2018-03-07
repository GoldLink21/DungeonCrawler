import java.awt.*;
import java.util.SimpleTimeZone;

public class Player extends Entity{
    int x,y;
    Game game;

    private final int SIZE = 21,SPEED=4,GAP=4;
    public Player(int x,int y,Game game){
        super(Color.BLUE,x,y,21,21);
        this.game = game;
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(x+GAP/2,y+GAP/2,SIZE,SIZE);
    }

    public void move(){
        if(game.isUp()){
            y+=SPEED;
        }if(game.isDown()){
                y-=SPEED;
        }if(game.isRight()){
            x+=SPEED;
        }if(game.isLeft()){
            y-=SPEED;
        }

    }
}
