import java.awt.*;

public class Tile {
    int x,y,value;

    private final int WALL = 0,PATH=1,LAVA=2;

    public Tile(int x,int y,int value){
        this.x=x;
        this.y=y;
        this.value = value;
    }

    public int getValue(){return value;}

    public void setValue(int value){this.value = value;}

    public void setColor(Graphics g){
        int tSize = Data.getTileSize();
        switch(this.value) {
            case WALL: 
                g.setColor(Color.DARK_GRAY);break;
            case PATH: 
                g.setColor(Color.LIGHT_GRAY);break;
            case LAVA: 
                g.setColor(Color.RED);break;
            default: 
                g.setColor(Color.GREEN);
        }
    }
}
