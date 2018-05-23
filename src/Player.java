import java.awt.Graphics;
import java.awt.Color;

public class Player extends Entity{

    private boolean onLava=false,onEnd=false,onLock=false;

    private final String file="player.png";

    Player(Map map){super(Color.BLUE,0,0,Data.PLAYER_SIZE,Data.PLAYER_SIZE,map);}

    private ImageLoader getCurImg(){
        switch(Data.getLastDir()){
            case Data.DIR_UP: return getImg(4);
            case Data.DIR_DOWN: return getImg(2);
            case Data.DIR_LEFT: return getImg(0);
            case Data.DIR_RIGHT: return getImg(6);
            default: return null;
        }
    }

    private ImageLoader getAltImg(){
        switch(Data.getLastDir()){
            case Data.DIR_UP: return getImg(5);
            case Data.DIR_DOWN: return getImg(3);
            case Data.DIR_LEFT: return getImg(1);
            case Data.DIR_RIGHT: return getImg(7);
            default: return null;
        }
    }

    private ImageLoader getImg(int num){return new ImageLoader(x,y,16,16,16*num,0,width,height,file);}

    private int imgCounter=0;

    @Override
    public void paint(Graphics g){
        try{
            animate(g);
        }catch(NullPointerException e){
           g.fillRect(x, y, width, height);
        }
    }

    private boolean isMoving(){return (Data.isLeft()||Data.isRight()||Data.isDown()||Data.isUp());}

    private void animate(Graphics g){
        final int ANIM_DELAY=12;
        ImageLoader cur;
        if(imgCounter<ANIM_DELAY/2) {
            cur = getCurImg();
        }else{
            cur=getAltImg();
        }
        if(isMoving()) {
            imgCounter++;
            if (imgCounter > ANIM_DELAY)
                imgCounter = 0;
        }
        cur.paint(g);
    }

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
        int[]temp=getCornerTypes();
        for(int i:temp)
            if(i==MapData.WALL||i==MapData.TRAP)return true;
        return false;
    }

    private void checkTiles(){
        int[]temp=getCornerTypes();
        for(int i:temp){
            if(i==MapData.LAVA){
                onLava=true;
            }else if(i==MapData.END){
                onEnd=true;
            }else if(i==MapData.LOCK){
                if(Data.getKeys()>0){
                    Data.decreaseKeys();
                    
                }

            }
        }if(onLava){
            resetPosition();
            if(Data.isModeEndless()){
                Data.setEndlessLives(Data.getEndlessLives()-1);
            }
        }else if(onEnd){
            map.loadNextFloor();
            resetPosition();
        }
        if(Data.isModeEndless()&&Data.getEndlessLives()<1)
            Data.endGame();

    }

    @Override
    public void move(){
        int SPEED=Data.PLAYER_SPEED;
        int BoardWidth = Data.getNumTiles()*Data.getTileSize();
        int buffer = 1;
        if (Data.isUp()&&y>0){
            y-=SPEED;
            Data.setLastDir(Data.DIR_UP);
            checkTiles();
            if(checkWallCollisions())y+=SPEED;

        }if(Data.isDown()&&y+height-buffer<BoardWidth){
            y+=SPEED;
            Data.setLastDir(Data.DIR_DOWN);
            checkTiles();
            if(checkWallCollisions())y-=SPEED;

        }if(Data.isRight()&&x+width-buffer<BoardWidth){
            x+=SPEED;
            Data.setLastDir(Data.DIR_RIGHT);
            checkTiles();
            if (checkWallCollisions())x-=SPEED;

        }if(Data.isLeft()&&x>0){
            x-=SPEED;
            Data.setLastDir(Data.DIR_LEFT);
            checkTiles();
            if (checkWallCollisions())x+=SPEED;

        }
        checkTiles();
    }
}