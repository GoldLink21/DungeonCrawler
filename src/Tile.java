import java.awt.*;

public class Tile {
    int x,y,value;

    private final int size = Data.getTileSize();

    private final int WALL = 0,PATH=1,LAVA=2,GOAL=3;

    public Tile(int x,int y,int value){
        this.x=Data.getTileSize()*x;
        this.y=Data.getTileSize()*y;
        this.value = value;
    }

    public int getValue(){return value;}

    public void setValue(int value){this.value = value;}

    public void setColor(Graphics g){
        int tSize = Data.getTileSize();
        switch(this.value) {
            case WALL: 
                g.setColor(Color.DARK_GRAY);
                g.fill3DRect(x,y,size,size,true);
                break;
            case PATH: 
                g.setColor(Color.LIGHT_GRAY);
                g.fill3DRect(x,y,size,size,true);
                break;
            case LAVA: 
                g.setColor(Color.RED);
                g.fill3DRect(x,y,size,size,true);
                break;
            case GOAL:
                g.setColor(Color.WHITE);
                g.fill3DRect(x,y,size,size,true);
                break;
            default: 
                g.setColor(Color.GREEN);
                g.fill3DRect(x,y,size,size,true);
        }
    }
}
