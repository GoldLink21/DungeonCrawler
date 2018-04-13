import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    int x,y,dir;

    private final int IMG_SIZE=16;

    boolean onLava=false,onEnd=false;

    ImageLoader curImg;

    private Map map;
    private final int SPEED=2;
    private final String file = "player.png";

    public Player(Map map){
        super(Color.BLUE,0,0,Data.PLAYER_SIZE,Data.PLAYER_SIZE);
        this.map = map;
        dir=Data.DIR_UP;
        curImg = getImg(2);
    }

    private ImageLoader getCurImg(){
        switch(Data.getLastDir()){
            case Data.DIR_UP: return getImg(4);
            case Data.DIR_DOWN: return getImg(2);
            case Data.DIR_LEFT: return getImg(0);
            case Data.DIR_RIGHT: return getImg(6);
            default: return null;
        }
    }

    private ImageLoader getImg(int num){
        return new ImageLoader(x,y,IMG_SIZE,IMG_SIZE,16*num,0,width,height,file);
    }

    @Override
    public void paint(Graphics g){
        getCurImg().paint(g);
    }

    public void resetPosition(){
        for(int i=0;i<Data.getNumTiles();i++){
            for(int j=0;j<Data.getNumTiles();j++){
                if(map.getTile(i,j).getValue()==MapData.START){
                    setPosition(i,j);
                    onEnd=false;
                    onLava=false;
                    return;
                }
            }
        }
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
            if(getCurTileType(points[i])==MapData.WALL) return true;
        return false;
    }

    private void checkTiles(){
        Point[]points={new Point(x,y),new Point(x+width,y),new Point(x,y+height),new Point(x+width,y+height)};
        for(int i=0;i<points.length;i++){
            int type = getCurTileType(points[i]);
            if(type==MapData.LAVA){
                onLava=true;
            }else if(type==MapData.GOAL){
                onEnd=true;
            }
        }if(onLava){
            resetPosition();
        }else if(onEnd){
            map.loadNextFloor();
            resetPosition();
        }
    }

    @Override
    public void move(){
        int BoardWidth = Data.getNumTiles()*Data.getTileSize();
        int buffer = 5;
        if(Data.isUp()&&y>0){
            y-=SPEED;
            Data.setLastDir(Data.DIR_UP);
            if(checkCollisions()) y+=SPEED;
            checkTiles();
        }if(Data.isDown()&&y+height-buffer<BoardWidth){
            y+=SPEED;
            Data.setLastDir(Data.DIR_DOWN);
            if(checkCollisions()) y-=SPEED;
            checkTiles();
        }if(Data.isRight()&&x+width-buffer<BoardWidth){
            x+=SPEED;
            Data.setLastDir(Data.DIR_RIGHT);
            if(checkCollisions()) x-=SPEED;
            checkTiles();
        }if(Data.isLeft()&&x>0){
            x-=SPEED;
            Data.setLastDir(Data.DIR_LEFT);
            if(checkCollisions()) x+=SPEED;
            checkTiles();
        }
        checkTiles();
    }

    @Override
    public void setPosition(int x,int y){
        int tSize = Data.getTileSize();
        this.x=x*tSize+tSize/4;
        this.y=y*tSize+tSize/4;
    }
}
