import java.awt.*;

public class Player extends Entity{

    private boolean onLava=false,onEnd=false;

    //Name of the file
    private final String file="player.png";

    Player(){super(Color.BLUE,0,0,Data.PLAYER_SIZE,Data.PLAYER_SIZE);}

    private ImageLoader getCurImg(){
        switch(Data.getLastDir()){
            case UP: return getImg(4);
            case DOWN: return getImg(2);
            case LEFT: return getImg(0);
            case RIGHT: return getImg(6);
            default: return null;
        }
    }

    private ImageLoader getAltImg(){
        switch(Data.getLastDir()){
            case UP: return getImg(5);
            case DOWN: return getImg(3);
            case LEFT: return getImg(1);
            case RIGHT: return getImg(7);
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
            g.setColor(color);
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
                if(Map.getTile(i,j).getValue()==MapData.START){
                    setPosition(i,j);
                    onEnd=false;
                    onLava=false;
                    return;
                }
    }

    private boolean checkWallCollisions(){
        int[]temp=getCornerTypes();
        for(int i:temp)
            if(i==MapData.WALL||i==MapData.TRAP||i==MapData.LOCK)return true;
        return false;
    }

    private boolean removeKey=false;

    private void checkTiles(){
        int[]temp=getCornerTypes();
        for(int i=0;i<temp.length;i++){
            if(temp[i]==MapData.LAVA){
                onLava=true;
            }else if(temp[i]==MapData.END){
                onEnd=true;
            }else if(temp[i]==MapData.LOCK){
                if(Data.getKeys()>0){
                    removeKey=true;
                    Point[]corners=getCornerMapPoints();
                    Map.setTile((int)corners[i].getX(),(int)corners[i].getY(),MapData.PATH);
                    //new SoundLoader("unlock.wav");
                }

            }
        }if(onLava){
            resetPosition();
            //new SoundLoader("respawn.wav");
            if(Data.isModeEndless()){
                Data.setEndlessLives(Data.getEndlessLives()-1);
            }
        }else if(onEnd){
            Map.loadNextFloor();
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
            Data.setLastDir(Data.Direction.UP);
            checkTiles();
            if(checkWallCollisions())y+=SPEED;
        }if(Data.isDown()&&y+height-buffer<BoardWidth){
            y+=SPEED;
            Data.setLastDir(Data.Direction.DOWN);
            checkTiles();
            if(checkWallCollisions())y-=SPEED;
        }if(Data.isRight()&&x+width-buffer<BoardWidth){
            x+=SPEED;
            Data.setLastDir(Data.Direction.RIGHT);
            checkTiles();
            if (checkWallCollisions())x-=SPEED;
        }if(Data.isLeft()&&x>0){
            x-=SPEED;
            Data.setLastDir(Data.Direction.LEFT);
            checkTiles();
            if (checkWallCollisions())x+=SPEED;
        }
        checkTiles();
        if(removeKey){
            Data.decreaseKeys();
            removeKey=false;
        }if(Data.getKeys()<0)
            Data.resetKeys();
    }
}