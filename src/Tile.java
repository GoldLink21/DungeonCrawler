import java.awt.*;

public class Tile {
    int x,y,value;

    private final int SIZE = 25;
    public Tile(int x,int y,int value){
        setPosition(x,y);
        this.value = value;
    }

    public int getValue(){return value;}

    public void setPosition(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void setValue(int value){
        this.value = value;
    }

    public void paint(Graphics g){
        switch(value){
            case 0:g.setColor(Color.DARK_GRAY);break;
            case 1:g.setColor(Color.LIGHT_GRAY);break;
            case 2:g.setColor(Color.RED);break;
            default:g.setColor(Color.GREEN);
        }
        g.fillRect(x*SIZE,y*SIZE,SIZE,SIZE);
    }
}
