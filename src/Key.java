import java.awt.*;

public class Key extends Entity {

    Key(int x,int y,Map map){super(Color.ORANGE,x,y,10,10,map);}

    @Override
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x,y,width,height);
    }

    @Override
    public void move(){}
}
