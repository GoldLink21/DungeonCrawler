import java.awt.Graphics;
import java.util.ArrayList;

public class Map {
    Tile[][]map;

    private final int numTiles = Data.getNumTiles();

    private int floor=-1;

    private static ArrayList<Trap>traps=new ArrayList<>();

    boolean trapsAdded=false;

    public void setTrapsAdded(boolean bool){trapsAdded=bool;}

    public Map(){
        map=new Tile[numTiles][numTiles];
        loadNextFloor();
    }

    public void randomBoard(){
        for (int i = 0; i < numTiles; i++)
            for (int j = 0; j < numTiles; j++)
                setTile(i,j,(int)(Math.random()*Data.TILE_TYPES));
    }

    public Tile getTile(int x, int y){
        if(x>=0&&x<=Data.getNumTiles())
            if(y>=0&&y<=Data.getNumTiles())
                return map[x][y];
        return new Tile(x,y,MapData.WALL);
    }

    public void loadFloor(int floor){
        this.floor=floor;
        if(!trapsAdded)addTraps();

        if(MapData.getFloor(floor)!=null){
            int[][]curFloor=MapData.getFloor(floor);
            for(int i=0;i<Data.getNumTiles();i++)
                for(int j=0;j<Data.getNumTiles();j++)
                    setTile(i,j,curFloor[i][j]);
            if(Data.DEBUG())System.out.println("Loaded Floor "+floor);
        }else{
            loadFloor(0);
            Data.toggleEnd();
        }
    }

    public void clearTraps(){
        while(traps.size()>0){
            traps.get(0).stop();
            traps.remove(0);
        }
    }

    private void addTrap(int[]x,int[]y,int delay){traps.add(new Trap(x,y,delay,this));}

    public void loadNextFloor(){
        trapsAdded=false;
        clearTraps();
        loadFloor(floor+1);
    }

    private void addTraps(){
        clearTraps();
        switch(floor){
            case 0://Cannot have traps on first floor!!
                break;
            case 1:
                int[] x1 = {4,4,4}, y1 = {0,1,2};
                addTrap(x1, y1, 1);
                addTrap(y1,x1,2);
                break;
            case 2:
                int[]x={1};int[]y={2};
                addTrap(x,y,1);
                break;
        }
        trapsAdded=true;
    }

    public void setTile(int x,int y,int val){map[x][y]=new Tile(x,y,val);}

    public void paint(Graphics g){
        for (int i = 0; i < numTiles; i++)
            for (int j = 0; j < numTiles; j++)
                map[i][j].paint(g);
    }
}
