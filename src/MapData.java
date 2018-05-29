public class MapData {

    //Holds all the data for the map tiles

    //To make a new floor, add a method to build the floor, increase
    //nFloors, implement method in floorMethod(), add Traps on Map

    private final static int nFloors = 7,nTiles=Data.getNumTiles();

    public static final int WALL=0,PATH=1,LAVA=2,END=3,START=4,TRAP=5,LOCK=6;

    private static int endlessFloor=0;

    public static int getEndlessFloor(){return endlessFloor;}

    private static int[][][]floors=new int[nFloors][nTiles][nTiles];

    public static int[][] getFloor(int floor){
        if(floor<nFloors)
            return floors[floor];
        return null;
    }

    public static int[][]getRandomFloor(){
        int old = endlessFloor;
        do {
            endlessFloor = (int) (Math.random() * nFloors);
        }while(endlessFloor==old);
        return floors[endlessFloor];
    }

    private static int[][] floorMethod(int num){
        switch(num){
            case 0:return floorZero();
            case 1:return floorOne();
            case 2:return floorTwo();
            case 3:return floorThree();
            case 4:return floorFour();
            case 5:return floorFive();
            case 6:return floorSix();
            default:return null;
        }
    }

    public static void setupFloors(){
        for(int i=0;i<nFloors;i++){
            floors[i]=floorMethod(i);
        }
    }

    private static int[][] floorZero(){
        int[][]temp=new int[nTiles][nTiles];
        temp[1][7]=START;
        temp[7][1]=END;
        for(int i=1;i<4;i++)
            temp[i][6]=PATH;
        for(int i=4;i<6;i++)
            temp[3][i]=PATH;
        for(int i=3;i<6;i++)
            temp[i][4]=PATH;
        for(int i=2;i<5;i++)
            temp[5][i]=PATH;
        for(int i=5;i<8;i++)
            temp[i][2]=PATH;
        temp[1][5]=TRAP;
        return temp;

    }

    private static int[][] floorOne(){
        int[][]temp=new int[nTiles][nTiles];
        setFloorAs(temp,PATH);
        for(int i=0;i<nTiles;i++){
            temp[0][i]=WALL;
            temp[nTiles-1][i]=WALL;
            temp[i][0]=WALL;
            temp[i][nTiles-1]=WALL;
        }for(int i=1;i<nTiles-2;i++){
            temp[2][i]=WALL;
            temp[6][i]=WALL;
        }for(int i=2;i<nTiles-1;i++){
            temp[4][i]=WALL;
        }
        temp[1][1]=START;
        temp[nTiles-2][1]=END;
        temp[4][nTiles-2]=LAVA;
        return temp;
    }

    private static int[][] floorTwo(){
        int[][] temp = new int[nTiles][nTiles];
        for(int i=2;i<nTiles-2;i++)
            temp[i][1]=PATH;
        temp[4][1]=LAVA;
        for(int i=3;i<6;i++)
            temp[i][2]=PATH;
        temp[1][1]=START;
        temp[nTiles-2][1]=END;
        for(int i=2;i<8;i++)
            temp[4][i]=PATH;
        temp[6][1]=LOCK;
        return temp;
    }

    private static int[][] floorThree(){
        int[][]temp=new int[nTiles][nTiles];
        temp[0][1]=START;
        temp[8][1]=END;
        for(int i=1;i<8;i++){
            temp[i][1]=PATH;
        }
        return temp;
    }

    private static int[][]floorFour(){
        int[][]temp=new int[nTiles][nTiles];
        temp[4][0]=START;
        temp[4][8]=END;
        for(int i=1;i<8;i++){
            temp[4][i]=PATH;
        }
        return temp;
    }

    private static int[][]floorFive(){
        int[][]temp=new int[nTiles][nTiles];
        setFloorAs(temp,PATH);
        border(temp,LAVA);
        temp[6][7]=LOCK;
        temp[7][6]=LOCK;
        temp[1][1]=START;
        temp[7][7]=END;
        return temp;
    }

    private static int[][]floorSix(){
        int[][]temp=new int[nTiles][nTiles];
        for(int i=1;i<8;i++){
            temp[i][4]=PATH;
            temp[4][i]=PATH;
        }for(int i=5;i<8;i++)
            temp[i][4]=LOCK;
        temp[4][4]=START;
        temp[8][4]=END;
        return temp;
    }

    private static void border(int[][]temp,int type){
        for(int i=0;i<nTiles;i++){
            temp[0][i]=type;
            temp[8][i]=type;
            temp[i][0]=type;
            temp[i][8]=type;
        }
    }

    private static void setFloorAs(int[][]temp,int type){
        for(int i=0;i<nTiles;i++){
            for(int j=0;j<nTiles;j++){
                temp[i][j]=type;
            }
        }
    }
}
