import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity implements Move {

    //They're "package-private" when they don't have public, private or protected before it
    int x,y,width,height,dir;
    Color color;
    boolean remove=false;

    Entity(Color color,int x,int y,int height, int width){
        this.color=color;
        this.width=width;
        this.height=height;
        this.dir=Data.DIR_DOWN;
        setPosition(x,y);
    }

    Point[]getCornerMapPoints(){
        //Returns the x and y of the tile that each corner of the Entity is on
        Point[]points={new Point(x,y),new Point(x+width,y),new Point(x,y+height),new Point(x+width,y+height)};
        for(Point p:points){
            p.setLocation(((int)p.getX()/Data.getTileSize()),(int)(p.getY()/Data.getTileSize()));
        }
        return points;
    }

    int[] getCornerTypes(){
        //Gets the type of tile at each corner point of the Entity
        Point[]points={new Point(x,y),new Point(x+width,y),new Point(x,y+height),new Point(x+width,y+height)};
        int[] temp=new int[4];
        for(int i=0;i<points.length;i++)
            temp[i]=getCurTileType(points[i]);
        return temp;
    }

    private int getCurTileType(Point p){
        //Helper method to make getting the type of the tile at a specific point
        int curX = (int)(p.getX())/Data.getTileSize();
        int curY = (int)(p.getY())/Data.getTileSize();
        if(curX>Data.getNumTiles()||curY>Data.getNumTiles())
            return 0;
        return Map.getTile(curX,curY).getValue();
    }

    boolean isRemove(){return remove;}

    private Rectangle getBounds(){return new Rectangle(x,y,width,height);}

    boolean collidesWith(Entity e){return getBounds().intersects(e.getBounds());}

    @Override
    public void paint(Graphics g){}

    @Override
    public void move(){}

    @Override
    public void setPosition(int x,int y){
        int tSize = Data.getTileSize();
        this.x=x*tSize+tSize/2-width/2;
        this.y=y*tSize+tSize/2-height/2;
    }
}
