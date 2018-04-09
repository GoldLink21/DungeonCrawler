import java.awt.*;

public class Map {
    Tile[][]map;

    private final int numTiles = Data.getNumTiles();

    private int floor=-1;

    public Map(){
        map = new Tile[numTiles][numTiles];
        loadFloor(0);
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
        return null;
    }

    public void loadFloor(int floor){
        this.floor=floor;
        int[][] curFloor = MapData.getFloor(floor);
        for(int i=0;i<Data.getNumTiles();i++){
            for(int j=0;j<Data.getNumTiles();j++){
                setTile(i,j,curFloor[i][j]);
            }
        }
    }

    public void loadNextFloor(){
        loadFloor(floor+1);
    }

    public void setTile(int x,int y,int val){map[x][y]=new Tile(x,y,val);}

    public void paint(Graphics g){
        for (int i = 0; i < numTiles; i++)
            for (int j = 0; j < numTiles; j++)
                map[i][j].paint(g);
    }
}
