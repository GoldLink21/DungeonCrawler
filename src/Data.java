public class Data {
    private static boolean play = false,menu = true,DEBUG=true;

    public static final int TILE_SIZE = 25;

    public static boolean DEBUG(){return DEBUG;}
    public static boolean isPlay(){return play;}
    public static boolean isMenu(){return menu;}

    public static int getTileSize(){return TILE_SIZE;}

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
