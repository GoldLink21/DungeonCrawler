import java.awt.*;

public class Entity implements Move {

    int x,y,width,height,rot;
    Color color;

    //25*25 grid of tiles
    //final int SIZE = 21,GAP = 4;

    public Entity(Color color,int x,int y,int height, int width){
        this.color=color;
        this.width=width;
        this.height=height;
        this.rot = 0;
        setPosition(x,y);
    }

    public void setRotation(int deg){
        this.rot=deg;
    }

    @Override
    public void paint(Graphics g) {
        //g.fillOval((int)(x+GAP/2),(int)(y+GAP/2),SIZE,SIZE);
    }

    @Override
    public void setPosition(int x, int y){
        //this.x = x*(SIZE+GAP)+GAP/2;
        //this.y = y*(SIZE+GAP)+GAP/2;
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
