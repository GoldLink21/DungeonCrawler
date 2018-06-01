import java.awt.*;
import java.awt.image.BufferedImage;

public class Key extends Entity {

    //Literally just an entity with a different picture

    BufferedImage img;

    boolean hasImage;

    Key(int x,int y){
        super(Color.ORANGE,x,y,20,14);
        try{
            img=ImageLoader.getImg("key.png");
            hasImage=true;
        }catch(NullPointerException e){
            hasImage=false;
        }
    }

    @Override
    public void paint(Graphics g){
        if(hasImage){
            g.drawImage(img,x,y,null);
        }else{
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }
}
