import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    private int x,y,value;

    //WALL=0,PATH=1,LAVA=2,END=3,START=4,TRAP=5;
    private static Color[]colors={(Color.DARK_GRAY),(Color.LIGHT_GRAY),(Color.RED).darker().darker(),(Color.WHITE),
            (Color.CYAN),(Color.ORANGE)};

    private int[] numVariants={1,1,1,1,1,1};

    private static String[] tileNames={"wall","path","lava","end","start","trap"};


    private static BufferedImage[]getImages(){
        BufferedImage[]temp=new BufferedImage[tileNames.length];
        for(int i=0;i<tileNames.length;i++)
            temp[i]=ImageLoader.getImg(tileNames[i]+".png");
        return temp;
    }

    private static BufferedImage[]images=getImages();

    private final int tSize=Data.getTileSize();

    public Tile(int x,int y,int value){
        this.x=tSize*x;
        this.y=tSize*y;
        this.value = value;
    }

    public int getValue(){return value;}

    public void paint(Graphics g){
        try{
            g.drawImage(images[value].getScaledInstance(tSize,tSize,Image.SCALE_SMOOTH),x,y,null);
        }catch(NullPointerException e){
            g.setColor(colors[value]);
            g.fill3DRect(x,y,tSize,tSize,true);
        }
    }
}
