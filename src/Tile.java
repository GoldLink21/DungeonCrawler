import java.awt.*;

public class Tile {
    int x,y,value;

    private final int size = Data.getTileSize();

    Color[]colors={Color.DARK_GRAY,Color.LIGHT_GRAY,Color.RED,Color.WHITE,Color.GRAY,Color.CYAN};

    public Tile(int x,int y,int value){
        this.x=Data.getTileSize()*x;
        this.y=Data.getTileSize()*y;
        this.value = value;
    }

    public int getValue(){return value;}

    public void setValue(int value){this.value = value;}

    public void paint(Graphics g){
        g.setColor(colors[value]);
        g.fill3DRect(x,y,size,size,true);
    }
}
