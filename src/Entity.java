import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity implements Move {

    protected int x,y,width,height,dir;
    protected Color color;
    protected Map map;

    protected Entity(Color color,int x,int y,int height, int width,Map map){
        this.color=color;
        this.width=width;
        this.height=height;
        this.map=map;
        this.dir=Data.DIR_DOWN;
        setPosition(x,y);
    }

    protected int[] getCornerTypes(){
        Point[]points={new Point(x,y),new Point(x+width,y),new Point(x,y+height),new Point(x+width,y+height)};
        int[] temp=new int[4];
        for(int i=0;i<points.length;i++)
            temp[i]=getCurTileType(points[i]);
        return temp;
    }

    protected int getCurTileType(Point p){
        int curX = (int)(p.getX())/Data.getTileSize();
        int curY = (int)(p.getY())/Data.getTileSize();
        if(curX>Data.getNumTiles()||curY>Data.getNumTiles())
            return 0;
        return map.getTile(curX,curY).getValue();
    }

    @Override
    public void paint(Graphics g){}

    @Override
    public void setPosition(int x,int y){
        int tSize = Data.getTileSize();
        this.x=x*tSize+tSize/4;
        this.y=y*tSize+tSize/4;
    }

    @Override
    public void move(){}

    public Rectangle getBounds(){return new Rectangle(x,y,width,height);}
}
