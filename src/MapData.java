public class MapData {

    private final static int NUM_FLOORS = 2,NUM_TILES=Data.getNumTiles();

    public static final int WALL = 0,PATH=1,LAVA=2,GOAL=3,SECRET=4,START=5;

    private static int[][][]floors= new int[NUM_FLOORS][NUM_TILES][NUM_TILES];

    public static int getNumFloors(){return NUM_FLOORS;}

    public static int[][] getFloor(int floor){
        if(floor<NUM_FLOORS)
            return floors[floor];
        return null;
    }

    public static void setupFloors(){
        floors[0]=floorOne();
        floors[1]=floorTwo();
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
        temp[2][1]=LAVA;
        return temp;
    }

    private static int[][] floorTwo(){
        int[][] temp = new int[NUM_TILES][NUM_TILES];
        temp[1][1]=START;
        temp[1][2]=GOAL;
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
