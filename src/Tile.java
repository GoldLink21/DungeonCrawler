import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;

public class Tile {
    private int x,y,value;
    //WALL=0,PATH=1,LAVA=2,END=3,START=4;
    private static Color[]colors={(Color.DARK_GRAY),(Color.LIGHT_GRAY),(Color.RED).darker().darker(),(Color.WHITE),
            (Color.CYAN)};

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
        g.setColor(colors[value]);
        g.fill3DRect(x,y,s,s,true);
    }
}
