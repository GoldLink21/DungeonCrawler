import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    int x,y,dir;

    private final int DIR_UP=0,DIR_RIGHT=1,DIR_DOWN=2,DIR_LEFT=3;

    boolean touchedLava=false;

    ImageLoader curImg;

    private Map map;
    private final int SPEED=2;
    private final String file = "resources/gfx/player.png";

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

    private ImageLoader getCurImg(){
        switch(Data.getLastDir()){
            case DIR_UP: return new ImageLoader(x,y,width,height,16*4,0,file);
            case DIR_DOWN: return new ImageLoader(x,y,width,height,16*2,0,file);
            case DIR_LEFT: return new ImageLoader(x,y,width,height,0,0,file);
            case DIR_RIGHT: return new ImageLoader(x,y,width,height,16*6,0,file);
            default: return null;
        }
    }

    @Override
    public void paint(Graphics g){

        getCurImg().paint(g);

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
        if(curX>Data.getNumTiles()||curY>Data.getNumTiles()){
            return 0;
        }
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
        int buffer = 5;
        if(Data.isUp()&&y>0){
            y-=SPEED;
            Data.setLastDir(DIR_UP);
            new SoundLoader("resources/sfx/pingpong.wav");
            //SoundLoader.play("resources/sfx/pingpong.wav");
            if(checkCollisions()) y+=SPEED;
        }if(Data.isDown()&&y+height-buffer<BoardWidth){
            y+=SPEED;
            Data.setLastDir(Data.DIR_DOWN);
            if(checkCollisions()) y-=SPEED;
        }if(Data.isRight()&&x+width-buffer<BoardWidth){
            x+=SPEED;
            Data.setLastDir(DIR_RIGHT);
            if(checkCollisions()) x-=SPEED;
        }if(Data.isLeft()&&x>0){
            x-=SPEED;
            Data.setLastDir(Data.DIR_LEFT);
            if(checkCollisions()) x+=SPEED;
        }
    }

    @Override
    public void setPosition(int x,int y){
        int tSize = Data.getTileSize();
        this.x=x*tSize+tSize/4;
        this.y=y*tSize+tSize/4;
    }
}
