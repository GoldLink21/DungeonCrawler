public class Data {
    private static boolean play = false,menu = true,DEBUG=true;

    private static boolean up=false,down=false,left=false,right=false,space=false;

    public static final int TILE_SIZE = 25,NUM_TILES = 20;

    public static boolean DEBUG(){return DEBUG;}
    public static boolean isPlay(){return play;}
    public static boolean isMenu(){return menu;}

    public static int getNumTiles(){return NUM_TILES;}
    public static int getTileSize(){return TILE_SIZE;}

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

    public static void togglePlay(){
        if(play){
            play = false;
            menu = true;
        }else{
            play = true;
            menu = false;
        }
    }
}
