public class MapData {

    private final static int NUM_FLOORS = 1,NUM_TILES=Data.getNumTiles();

    private static int[][][]floors= new int[NUM_FLOORS][NUM_TILES][NUM_TILES];

    public static int[][] getFloor(int floor){
        return floors[floor];
    }

    public static void setupFloors(){
        floors[0]=floorOne();
    }

    private static int[][] floorOne(){
        int[][]temp=new int[NUM_TILES][NUM_TILES];
        for(int i=1;i<NUM_TILES-1;i++){

        }
        return temp;
    }

}
