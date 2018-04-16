import java.awt.Graphics;
import java.awt.Color;

public class Player extends Entity{

    private final int IMG_SIZE=16;

    boolean onLava=false,onEnd=false;

    private final int SPEED=2;
    private final String file = "player.png";

    public Player(Map map){super(Color.BLUE,0,0,Data.PLAYER_SIZE,Data.PLAYER_SIZE,map);}

    private ImageLoader getCurImg(){
        switch(Data.getLastDir()){
            case Data.DIR_UP: return getImg(4);
            case Data.DIR_DOWN: return getImg(2);
            case Data.DIR_LEFT: return getImg(0);
            case Data.DIR_RIGHT: return getImg(6);
            default: return null;
        }
    }

    private ImageLoader getImg(int num){return new ImageLoader(x,y,IMG_SIZE,IMG_SIZE,16*num,0,width,height,file);}

    @Override
    public void paint(Graphics g){getCurImg().paint(g);}

    public void resetPosition(){
        for(int i=0;i<Data.getNumTiles();i++)
            for(int j=0;j<Data.getNumTiles();j++)
                if(map.getTile(i,j).getValue()==MapData.START){
                    setPosition(i,j);
                    onEnd=false;
                    onLava=false;
                    return;
                }
    }

    private boolean checkWallCollisions(){
        int[] temp=getCornerTypes();
        for(int i:temp)
            if(i==MapData.WALL)return true;
        return false;
    }

    private void checkTiles(){
        int[]temp=getCornerTypes();
        for(int i:temp){
            if(i==MapData.LAVA){
                onLava=true;
            }else if(i==MapData.END){
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
        int BoardWidth = Data.getNumTiles() * Data.getTileSize();
        int buffer = 1;
        if (Data.isUp() && y > 0) {
            y-=SPEED;
            Data.setLastDir(Data.DIR_UP);
            if(checkWallCollisions())y+=SPEED;
            checkTiles();
        }if (Data.isDown()&&y+height-buffer<BoardWidth){
            y+=SPEED;
            Data.setLastDir(Data.DIR_DOWN);
            if (checkWallCollisions())y-=SPEED;
            checkTiles();
        }if(Data.isRight()&&x+width-buffer<BoardWidth){
            x+=SPEED;
            Data.setLastDir(Data.DIR_RIGHT);
            if (checkWallCollisions())x-=SPEED;
            checkTiles();
        }if(Data.isLeft()&&x>0){
            x-=SPEED;
            Data.setLastDir(Data.DIR_LEFT);
            if (checkWallCollisions())x+=SPEED;
            checkTiles();
        }
        checkTiles();
    }
}