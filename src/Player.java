import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    int x,y,dir;

    private final int DIR_UP=0,DIR_RIGHT=1,DIR_DOWN=2,DIR_LEFT=3;

    ImageLoader curImg;

    private Map map;
    private final int SPEED=2;
    private final String file = "resources/player.png";

    public Player(Map map){
        super(Color.BLUE,0,0,16,16);
        this.map = map;
        dir=DIR_UP;
        curImg = new ImageLoader(x,y,width,height,x,y,file);
    }

    private Polygon makePlayerShape(){
        int rad=width/2;
        int[]xPoints={x+rad,x+width,x+rad,x};
        int[]yPoints={y,y+rad,y+height,y+rad};
        return new Polygon(xPoints,yPoints,xPoints.length);
    }

    private void getCurImg(){

    }

    @Override
    public void paint(Graphics g){
        getCurImg();
        curImg.paint(g);
        /*
        Polygon pShape = makePlayerShape();
        g.setColor(Color.BLUE);
        g.fillPolygon(pShape);
        //g.fillOval(x,y,SIZE,SIZE);
        g.setColor(Color.BLACK);
        g.drawPolygon(pShape);
        //g.drawOval(x,y,SIZE,SIZE);
        g.setColor(Color.ORANGE);
        g.drawRect((int)pShape.getBounds().getX(),(int)pShape.getBounds().getY(),width,height);
        */
    }

    private int getCurTileType(Point p){
        int curX = (int)(p.getX())/Data.getTileSize();
        int curY = (int)(p.getY())/Data.getTileSize();
        return map.getTile(curX,curY).getValue();
    }

    private boolean checkCollisions(){
        Point[]points={new Point(x,y),new Point(x+width,y),new Point(x,y+height),new Point(x+width,y+height)};
        for(int i=0;i<points.length;i++)
            if(getCurTileType(points[i])==0) return true;
        return false;
    }

    @Override
    public void move(){
        int BoardWidth = Data.getNumTiles()*Data.getTileSize();
        if(Data.isUp()&&y>0){
            y-=SPEED;
            if(checkCollisions()) y+=SPEED;
        }if(Data.isDown()&&y+height-1<BoardWidth){
            y+=SPEED;
            if(checkCollisions()) y-=SPEED;
        }if(Data.isRight()&&x+width-1<BoardWidth){
            x+=SPEED;
            if(checkCollisions()) x-=SPEED;
        }if(Data.isLeft()&&x>0){
            x-=SPEED;
            if(checkCollisions()) x+=SPEED;
        }
    }

    @Override
    public void setPosition(int x,int y){
        int tSize = Data.getTileSize();
        this.x=x*tSize;
        this.y=y*tSize;
    }
}
