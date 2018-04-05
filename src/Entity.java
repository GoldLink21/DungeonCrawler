import java.awt.*;

public class Entity implements Move {

    int x,y,width,height;
    Color color;

    //25*25 grid of tiles
    //final int SIZE = 21,GAP = 4;

    public Entity(Color color,int x,int y,int height, int width){
        this.color=color;
        this.width=width;
        this.height=height;
        setPosition(x,y);
    }

    public void waitMilli(long x){
        long curTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis()+x;
        while(curTime<endTime){curTime=System.currentTimeMillis();}
    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void setPosition(int x, int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public void move() {

    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }



}
