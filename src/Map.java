import java.awt.*;

public class Map {
    Tile[][]map;

    private final int WALL = 0,PATH=1,LAVA=2,GOAL=3;

    private final int numTiles = Data.getNumTiles();

    private int floor=0;

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

    private void fillBoard(int value){
        for (int i = 0; i < numTiles; i++) {
            for (int j = 0; j < numTiles; j++) {
                setTile(i,j,value);
            }
        }
    }

    public void floorOne(){
        floor=1;
        fillBoard(WALL);
        for(int i=1;i<numTiles-1;i+=2) {
            for (int j = 1; j < numTiles - 1; j++) {
                setTile(i,j,PATH);
            }
        }
        setTile(0,1,PATH);
        setTile(0,0,PATH);

        setTile(4,1,PATH);
        setTile(8,1,PATH);
        setTile(12,1,PATH);
        setTile(16,1,PATH);

        setTile(2,18,PATH);
        setTile(6,18,PATH);
        setTile(10,18,PATH);
        setTile(14,18,PATH);
        setTile(18,18,GOAL);

    }

    public void paint(Graphics g){
        int tSize = Data.getTileSize();
        for (int i = 0; i < numTiles; i++) {
            for (int j = 0; j < numTiles; j++) {
                int x = i * tSize;
                int y = j * tSize;
                map[i][j].setColor(g);
                //g.fill3DRect(x,y,tSize,tSize,true);
            }
        }
    }
}
