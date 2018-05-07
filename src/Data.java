public class Data {
    private static boolean play = false,menu = true,end=false;

    private static boolean up=false,down=false,left=false,right=false;

    private static final int TILE_SIZE = 35,NUM_TILES = 9;
    public static final int TILE_TYPES=5,PLAYER_SIZE = 18;

    public static final int DIR_UP=0,DIR_RIGHT=1,DIR_DOWN=2,DIR_LEFT=3;

    public static final int MODE_CLASSIC=0,MODE_ENDLESS=1;

    private static int lastDir=DIR_DOWN,mode=MODE_CLASSIC,endlessLives=3,endlessLevels=0;

    public static int getNumTiles(){return NUM_TILES;}
    public static int getTileSize(){return TILE_SIZE;}
    public static int getLastDir(){return lastDir;}
    public static int getMode(){return mode;}
    public static int getEndlessLives(){return endlessLives;}
    public static int getEndlessLevels(){return endlessLevels;}

    public static boolean DEBUG(){return true;}

    public static boolean isPlay(){return play;}
    public static boolean isMenu(){return menu;}
    public static boolean isEnd(){return end;}

    public static boolean isUp(){return up;}
    public static boolean isDown(){return down;}
    public static boolean isLeft(){return left;}
    public static boolean isRight(){return right;}

    public static boolean isModeEndless(){return mode==MODE_ENDLESS;}
    public static boolean isModeClassic(){return mode==MODE_CLASSIC;}

    public static void setUp(boolean bool){up=bool;}
    public static void setDown(boolean bool){down=bool;}
    public static void setLeft(boolean bool){left=bool;}
    public static void setRight(boolean bool){right=bool;}

    public static void setLastDir(int dir){lastDir = dir;}
    public static void setMode(int type){mode = type;}
    public static void setEndlessLives(int num){endlessLives=num;}
    public static void setEndlessLevels(int num){endlessLevels=num;}

    public static void setPlay(boolean bool){play=bool;}
    public static void setEnd(boolean end){Data.end = end;}
    public static void setMenu(boolean menu){Data.menu = menu;}

    public static void startGame(){
        end=false;
        menu=false;
        play=true;
    }

    public static void endGame(){
        play=false;
        end=true;
        menu=false;
    }

    public static void toMenu(){
        end=false;
        play=false;
        menu=true;
    }

    public static void togglePlay(){
        //This is a cool way to toggle booleans
        play^=true;
        menu^=true;
    }

    public static void newEndless(){
        endlessLives=3;
        endlessLevels=0;
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
