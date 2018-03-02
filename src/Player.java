import java.awt.*;

public class Player extends Entity{
    int x,y;
    Game game;

    private final int SIZE = 21,SPEED=4;
    public Player(int x,int y,Game game){
        super(x,y);
        this.game = game;
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(x+GAP/2,y+GAP/2,SIZE,SIZE);
    }

    public void move(){
        if(game.isUpPressed()){
            y+=SPEED;
        }if(game.isDownPressed()){
                y-=SPEED;
        }if(game.isRightPressed()){
            x+=SPEED;
        }if(game.isLeftPressed()){
            y-=SPEED;
        }

    }
}
