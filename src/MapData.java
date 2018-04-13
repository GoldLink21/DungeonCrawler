public class MapData {

    private final static int NUM_FLOORS = 2,NUM_TILES=Data.getNumTiles();

    public static final int WALL=0,PATH=1,LAVA=2,GOAL=3,START=4;

    private static int[][][]floors= new int[NUM_FLOORS][NUM_TILES][NUM_TILES];

    public static int getNumFloors(){return NUM_FLOORS;}

    public static int[][] getFloor(int floor){
        if(floor<NUM_FLOORS)
            return floors[floor];
        return null;
    }

    public static void setupFloors(){
        floors[0]=floorZero();
        floors[1]=floorOne();
        floors[2]=floorTwo();

    }

    private static int[][] floorZero(){
        int[][]temp=new int[NUM_TILES][NUM_TILES];

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
        temp[NUM_TILES-2][1]=GOAL;
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
        temp[NUM_TILES-2][1]=GOAL;
        return temp;
    }

    private static void setFloorAs(int[][]temp,int type){
        for(int i=0;i<NUM_TILES;i++){
            for(int j=0;j<NUM_TILES;j++){
                temp[i][j]=type;
            }
        }
    }
}
