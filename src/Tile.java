import java.awt.*;

public class Tile {
    int x,y,value;

    private final int size = Data.getTileSize();

    private final int WALL = 0,PATH=1,LAVA=2,GOAL=3,SECRET=4,START=5;

    Color[]colors={Color.DARK_GRAY,Color.LIGHT_GRAY,Color.RED,Color.WHITE,Color.GRAY,Color.CYAN};

    public Tile(int x,int y,int value){
        this.x=Data.getTileSize()*x;
        this.y=Data.getTileSize()*y;
        this.value = value;
    }

    public int getValue(){return value;}

    public void setValue(int value){this.value = value;}

    public void setColor(Graphics g){
        g.setColor(colors[value]);
        g.fill3DRect(x,y,size,size,true);
    }

    private void paintTileOld(Color c, Graphics g){
        g.setColor(c);
        g.fill3DRect(x,y,size,size,true);
    }

    public void setColorOld(Graphics g){
        switch(this.value) {
            case WALL: 
                paintTileOld(Color.GRAY,g);
                break;
            case PATH: 
                paintTileOld(Color.LIGHT_GRAY,g);
                break;
            case LAVA:
                paintTileOld(Color.RED,g);
                break;
            case GOAL:
                paintTileOld(Color.WHITE,g);
                break;
            case SECRET:
                paintTileOld(Color.GRAY,g);
                break;
            default: 
                g.setColor(Color.GREEN);
                g.fill3DRect(x,y,size,size,true);
        }
    }
}
