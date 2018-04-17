import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private int x,y,value;
    //WALL=0,PATH=1,LAVA=2,END=3,START=4;
    private static Color[]colors={(Color.DARK_GRAY),(Color.LIGHT_GRAY),(Color.RED).darker().darker(),(Color.WHITE),
            (Color.CYAN)};

    private static ImageLoader[]images;

    int tSize=Data.getTileSize();
    private BufferedImage lava;

    public Tile(int x,int y,int value){
        this.x=Data.getTileSize()*x;
        this.y=Data.getTileSize()*y;
        this.value = value;
        lava = ImageLoader.getImg("LavaGif.gif");
    }

    public static void printRGB(){
        for(Color c:colors)
            System.out.println(c.toString());
    }

    public int getValue(){return value;}

    public void paint(Graphics g){
        int s=Data.getTileSize();
        g.setColor(colors[value]);
        g.fill3DRect(x,y,s,s,true);
        if(value==2){
            g.drawImage(lava.getScaledInstance(tSize,tSize, Image.SCALE_SMOOTH),x,y,null);
        }
    }
}
