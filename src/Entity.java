import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity implements Move {

    //They're "package-private" when they don't have public, private or protected before it
    int x,y,width,height,dir;
    Color color;
    Map map;
    boolean remove=false;

    Entity(Color color,int x,int y,int height, int width,Map map){
        this.color=color;
        this.width=width;
        this.height=height;
        this.map=map;
        this.dir=Data.DIR_DOWN;
        setPosition(x,y);
    }

    int[] getCornerTypes(){
        Point[]points={new Point(x,y),new Point(x+width,y),new Point(x,y+height),new Point(x+width,y+height)};
        int[] temp=new int[4];
        for(int i=0;i<points.length;i++)
            temp[i]=getCurTileType(points[i]);
        return temp;
    }

    private int getCurTileType(Point p){
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

    boolean isRemove(){return remove;}

    private Rectangle getBounds(){return new Rectangle(x,y,width,height);}

    boolean collidesWith(Entity e){return getBounds().intersects(e.getBounds());}
}
