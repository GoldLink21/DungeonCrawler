import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    private int x,y,value;

    //WALL=0,PATH=1,LAVA=2,END=3,START=4,TRAP=5,LOCK=6;

    //Predefined colors for in case of error
    private static Color[]colors={(Color.DARK_GRAY),(Color.LIGHT_GRAY),(Color.RED).darker().darker(),(Color.WHITE),
            (Color.CYAN),(Color.ORANGE),(Color.YELLOW).brighter()};

    private static String[] tileNames={"wall","path","lava","end","start","trap","lock"};

    //Gets all the images for the tiles based on the names in the above array
    private static BufferedImage[]getImages(){
        BufferedImage[]temp=new BufferedImage[tileNames.length];
        for(int i=0;i<tileNames.length;i++) {
            //try {
                temp[i] = ImageLoader.getImg(tileNames[i] + ".png");
            /*}catch(NullPointerException e){
                temp[i]=null;
            }*/
        }
        return temp;
    }

    //Holds all the images
    private static BufferedImage[]images=getImages();

    //Just for short notation
    private final int tSize=Data.getTileSize();

    Tile(int x,int y,int value){
        this.x=tSize*x;
        this.y=tSize*y;
        this.value = value;
    }

    public int getValue(){return value;}

    public void paint(Graphics g){
        try{
            //If there is no picture for it, draws a square of the predefined colors above
            g.drawImage(images[value].getScaledInstance(tSize,tSize,Image.SCALE_SMOOTH),x,y,null);
        }catch(NullPointerException e){
            g.setColor(colors[value]);
            g.fill3DRect(x,y,tSize,tSize,true);
        }
    }
}
