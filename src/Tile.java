import java.awt.*;

public class Tile {
    int x,y,value;

    private final int size = Data.getTileSize();
    //WALL=0,PATH=1,LAVA=2,GOAL=3,START=4;
    Color[]colors={(Color.DARK_GRAY),(Color.LIGHT_GRAY),(Color.RED).darker().darker(),(Color.WHITE),
            (Color.CYAN)};

    public Tile(int x,int y,int value){
        this.x=Data.getTileSize()*x;
        this.y=Data.getTileSize()*y;
        this.value = value;
    }public Tile(Point p,int value){
        this.x=(int)p.getX();
        this.y=(int)p.getY();
        this.value=value;
    }

    public int getValue(){return value;}

    public void paint(Graphics g){
        g.setColor(colors[value]);
        g.fill3DRect(x,y,size,size,true);
    }
}
