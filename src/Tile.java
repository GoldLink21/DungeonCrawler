import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private int x,y,value;
    //WALL=0,PATH=1,LAVA=2,END=3,START=4
    private static Color[]colors={(Color.DARK_GRAY),(Color.LIGHT_GRAY),(Color.RED).darker().darker(),(Color.WHITE),
            (Color.CYAN)};
    
    private static BufferedImage[]images={ImageLoader.getImg("wall.png"),
            ImageLoader.getImg("clay.png"),ImageLoader.getImg("magma.png"),
            ImageLoader.getImg("start.png"),ImageLoader.getImg("end.png")};

    int tSize=Data.getTileSize();

    public Tile(int x,int y,int value){
        this.x=Data.getTileSize()*x;
        this.y=Data.getTileSize()*y;
        this.value = value;
    }

    public static void printRGB(){
        for(Color c:colors)
            System.out.println(c.toString());
    }

    public int getValue(){return value;}

    public void paint(Graphics g){
        int s=Data.getTileSize();
        if(images[value]!=null) {
            g.drawImage(images[value].getScaledInstance(s, s, Image.SCALE_SMOOTH), x, y, null);
        }else{
            g.setColor(colors[value]);
            g.fill3DRect(x,y,s,s,true);
        }

    }
}
