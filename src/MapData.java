public class MapData {

    private final static int NUM_FLOORS = 1,NUM_TILES=Data.getNumTiles();

    private static final int WALL = 0,PATH=1,LAVA=2,GOAL=3,SECRET=4,START=5;

    private static int[][][]floors= new int[NUM_FLOORS][NUM_TILES][NUM_TILES];

    public static int[][] getFloor(int floor){
        return floors[floor];
    }

    public static void setupFloors(){
        floors[0]=floorOne();
    }

    private static int[][] floorOne(){
        int[][]temp=new int[NUM_TILES][NUM_TILES];
        setFloorAs(temp,PATH);
        for(int i=0;i<NUM_TILES;i++){
            temp[0][i]=WALL;
            temp[NUM_TILES-1][i]=WALL;
            temp[i][0]=WALL;
            temp[i][NUM_TILES-1]=WALL;
        }
        temp[1][1]=START;
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
