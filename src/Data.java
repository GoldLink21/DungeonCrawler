public class Data {
    private static boolean play = false,menu = true,end=false,DEBUG=true;

    private static boolean up=false,down=false,left=false,right=false,space=false;

    private static final int TILE_SIZE = 35,NUM_TILES = 9;
    public static final int TILE_TYPES=5,PLAYER_SIZE = 15;

    public static final int DIR_UP=0,DIR_RIGHT=1,DIR_DOWN=2,DIR_LEFT=3;

    private static int lastDir=DIR_DOWN;

    public static int getNumTiles(){return NUM_TILES;}
    public static int getTileSize(){return TILE_SIZE;}
    public static int getLastDir(){return lastDir;}

    public static boolean DEBUG(){return DEBUG;}
    public static boolean isPlay(){return play;}
    public static boolean isMenu(){return menu;}
    public static boolean isEnd(){return end;}

    public static boolean isUp(){return up;}
    public static boolean isDown(){return down;}
    public static boolean isLeft(){return left;}
    public static boolean isRight(){return right;}
    public static boolean isSpace(){return space;}

    public static void setUp(boolean bool){up=bool;}
    public static void setDown(boolean bool){down=bool;}
    public static void setLeft(boolean bool){left=bool;}
    public static void setRight(boolean bool){right=bool;}
    public static void setSpace(boolean bool){space=bool;}

    public static void setLastDir(int dir){lastDir = dir;}

    public static void togglePlay(){
        //This is a cool way to toggle booleans
        play^=true;
        menu^=true;
    }


    public static void toggleEnd(){
        if(end){
            end =false;
            menu = true;
        }else{
            play=false;
            end=true;
        }
    }
}
