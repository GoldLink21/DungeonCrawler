import java.awt.*;

public class Key extends Entity {

    //Literally just an entity with a different picture

    Key(int x,int y,Map map){super(Color.ORANGE,x,y,10,7,map);}

    @Override
    public void paint(Graphics g){
        try{
            g.drawImage(ImageLoader.getImg("key.png"),x,y,null);
        }catch(NullPointerException e){
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }
}
