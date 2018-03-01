public class Data {
    private static boolean play = false,menu = true,DEBUG=true;

    public static boolean DEBUG(){return DEBUG;}
    public static boolean isPlay(){return play;}
    public static boolean isMenu(){return menu;}

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
