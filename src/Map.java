import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Map {
    private static Tile[][]map;

    private final int nTiles = Data.getNumTiles();

    private static int floor=-1;

    private static ArrayList<Trap>traps=new ArrayList<>();

    private static boolean trapsAdded=false;

    Map(){map=new Tile[nTiles][nTiles];}

    public static ArrayList<Trap> getTraps(){return traps;}

    public Tile getTile(int x, int y){
        if(x>=0&&x<=Data.getNumTiles())
            if(y>=0&&y<=Data.getNumTiles())
                return map[x][y];
        return new Tile(x,y,MapData.WALL);
    }

    private void loadFloor(int flr){
        floor=flr;

        clearTraps();
        if(MapData.getFloor(flr)!=null){
            int[][]curFloor=MapData.getFloor(flr);
            for(int i=0;i<Data.getNumTiles();i++)
                for(int j=0;j<Data.getNumTiles();j++)
                    setTile(i,j,curFloor[i][j]);
            addTrapsAndKeys(flr);
            if(Data.DEBUG())System.out.println("Loaded Floor "+flr);
        }else{
            floor=-1;
            Data.endGame();
        }

    }

    private void loadRandomFloor(){
        Data.setEndlessLevels(Data.getEndlessLevels()+1);
        int[][]curFloor=MapData.getRandomFloor();
        for(int i=0;i<Data.getNumTiles();i++)
            for(int j=0;j<Data.getNumTiles();j++)
                setTile(i,j,curFloor[i][j]);
        if(!trapsAdded)addTrapsAndKeys(MapData.getEndlessFloor());
        if(Data.DEBUG())System.out.println("Randomly Loaded Floor "+MapData.getEndlessFloor());
        floor=-1;
    }

    public static void clearTraps(){
        while(traps.size()>0){
            traps.get(0).stop();
            traps.remove(0);
        }
        trapsAdded=false;
    }

    private void addLavaTrap(int[]x,int[]y,int delay){traps.add(new LavaTrap(x,y,delay,true,this));}
    private void addDartTrap(int x,int y,int delay,int dir){traps.add(new DartTrap(x,y,delay,dir,this));}

    public void loadNextFloor(){
        trapsAdded=false;
        Data.resetKeys();
        clearTraps();
        Board.removeDarts();
        if(Data.isModeClassic())
            loadFloor(floor+1);
        else if(Data.isModeEndless())
            loadRandomFloor();
    }

    private void addTrapsAndKeys(int var){
        clearTraps();
        switch(var){
            case 0:floorZeroTraps();break;
            case 1:floorOneTraps();break;
            case 2:floorTwoTraps();break;
            case 3:floorThreeTraps();break;
            case 4:floorFourTraps();break;
            case 5:floorFiveTraps();break;
            case 6:floorSixTraps();break;
        }
        trapsAdded=true;
    }

    private void floorZeroTraps(){
        int[]x=fillArr(6,5,false);int[]y=fillArr(2,5,true);
        addLavaTrap(x,y,10);
        addDartTrap(1,5,2,Data.DIR_DOWN);
    }

    private void floorOneTraps(){
        int[]x=fillArr(4,3);int[]y=fillArr(0,3,true);
        addLavaTrap(x, y, 4);
        x=fillArr(0,9,true);y=fillArr(4,9);
        addLavaTrap(x,y,7);
    }

    private void floorTwoTraps(){
        addKey(4,7);
    }

    private void floorThreeTraps(){
        int length=8;
        int[]x;
        int[][]y={{2,1,1,1,1,1,1,1,1},{3,2,1,1,1,1,1,1},{4,3,2,1,1,1,1,1},
                {5,4,3,2,1,1,1,1},{6,5,4,3,2,1,1,1},{7,6,5,4,3,2,1,1},{8,7,6,5,4,3,2,1}};
        for(int i=1;i<length;i++){
            x=fillArr(i,length);
            addLavaTrap(x,y[i-1],7);
        }
    }

    private void floorFourTraps(){
        addDartTrap(3,1,3,Data.DIR_RIGHT);
        addDartTrap(5,2,2,Data.DIR_LEFT);
        addDartTrap(3,3,3,Data.DIR_RIGHT);
        addDartTrap(5,4,2,Data.DIR_LEFT);
        addDartTrap(3,5,3,Data.DIR_RIGHT);
        addDartTrap(5,6,2,Data.DIR_LEFT);
        addDartTrap(3,7,3,Data.DIR_RIGHT);
    }

    private void floorFiveTraps(){
        addKey(7,1);
        addKey(1,7);
    }

    private void floorSixTraps(){
        addKey(1,4);
        addKey(4,1);
        addKey(4,7);
    }

    private int[] fillArr(int val,int length){
        int[]arr=new int[length];
        for(int i=0;i<length;i++)
            arr[i]=val;
        return arr;
    }private int[] fillArr(int val,int length,boolean up){
        int[]arr=new int[length];
        if(up)
            for(int i=0;i<length;i++)
                arr[i]=val++;
        else
            for(int i=0;i<length;i++)
                arr[i]=val--;
        return arr;
    }

    public static void setTile(int x,int y,int val){map[x][y]=new Tile(x,y,val);}

    public static void setFloor(int val){floor=val;}

    public void paint(Graphics g){
        for(int i=0;i<nTiles;i++)
            for(int j=0;j<nTiles;j++)
                map[i][j].paint(g);
    }

    private void addKey(int x,int y){
        try {
            Board.addEntity(new Key(x, y, this));
        }catch(ConcurrentModificationException e){}
    }
}
