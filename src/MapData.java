public class MapData {

    private final static int NUM_FLOORS = 4,NUM_TILES=Data.getNumTiles();

    public static final int WALL=0,PATH=1,LAVA=2,END=3,START=4;

    public static int endlessFloor;

    public static void setEndlessFloor(int num){endlessFloor=num;}
    public static int getEndlessFloor(){return endlessFloor;}

    private static int[][][]floors= new int[NUM_FLOORS][NUM_TILES][NUM_TILES];

    public static int[][] getFloor(int floor){
        if(floor<NUM_FLOORS)
            return floors[floor];
        return null;
    }

    public static int[][]getRandomFloor(){
        endlessFloor=(int)(Math.random()*NUM_FLOORS);
        switch(endlessFloor){
            case 0:return floorZero();
            case 1:return floorOne();
            case 2:return floorTwo();
            case 3:return floorThree();

            default:return floorZero();
        }
    }

    public static void setupFloors(){
        floors[0]=floorZero();
        floors[1]=floorOne();
        floors[2]=floorTwo();
        floors[3]=floorThree();


        if(Data.DEBUG()){
            floors[1]=floorZero();
        }
    }

    private static int[][] floorZero(){
        int[][]temp=new int[NUM_TILES][NUM_TILES];
        temp[1][3]=START;
        temp[1][2]=END;
        return temp;

    }

    private static int[][] floorOne(){
        int[][]temp=new int[NUM_TILES][NUM_TILES];
        setFloorAs(temp,PATH);
        for(int i=0;i<NUM_TILES;i++){
            temp[0][i]=WALL;
            temp[NUM_TILES-1][i]=WALL;
            temp[i][0]=WALL;
            temp[i][NUM_TILES-1]=WALL;
        }for(int i=1;i<NUM_TILES-2;i++){
            temp[2][i]=WALL;
            temp[6][i]=WALL;
        }for(int i=2;i<NUM_TILES-1;i++){
            temp[4][i]=WALL;
        }
        temp[1][1]=START;
        temp[NUM_TILES-2][1]=END;
        temp[NUM_TILES/2][NUM_TILES-2]=LAVA;
        return temp;
    }

    private static int[][] floorTwo(){
        int[][] temp = new int[NUM_TILES][NUM_TILES];
        for(int i=2;i<NUM_TILES-2;i++)
            temp[i][1]=PATH;
        temp[4][1]=LAVA;
        for(int i=3;i<6;i++)
            temp[i][2]=PATH;
        temp[1][1]=START;
        temp[NUM_TILES-2][1]=END;
        return temp;
    }

    private static int[][] floorThree(){
        int[][]temp=new int[NUM_TILES][NUM_TILES];
        temp[0][1]=START;
        temp[8][1]=END;
        for(int i=1;i<8;i++){
            temp[i][1]=PATH;
        }
        return temp;
    }

    private static void border(int[][]temp,int type){
        for(int i=0;i<NUM_TILES;i++){
            temp[0][i]=type;
            temp[8][i]=type;
            temp[i][0]=type;
            temp[i][8]=type;
        }
    }

    private static void setFloorAs(int[][]temp,int type){
        for(int i=0;i<NUM_TILES;i++){
            for(int j=0;j<NUM_TILES;j++){
                temp[i][j]=type;
            }
        }
    }
}
