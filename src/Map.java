import java.awt.*;

public class Map {
    Tile[][]map;

    private final int WALL = 0,PATH=1,LAVA=2;

    private final int numTiles = Data.getNumTiles();

    private int floor=0;

    public Map(){
        map = new Tile[numTiles][numTiles];
        fillBoard(WALL);
    }

    public void randomBoard(){
        for (int i = 0; i < numTiles; i++)
            for (int j = 0; j < numTiles; j++)
                map[i][j]=new Tile(i,j,(int)(Math.random()*Data.TILE_TYPES));
    }

    public Tile getTile(int x, int y){
        if(x>=0&&x<=Data.getNumTiles())
            if(y>=0&&y<=Data.getNumTiles())
                return map[x][y];
        return null;
    }

    private void fillBoard(int value){
        for (int i = 0; i < numTiles; i++) {
            for (int j = 0; j < numTiles; j++) {
                map[i][j]=new Tile(i,j,value);
            }
        }
    }

    public void floorOne(){
        floor=1;
        fillBoard(WALL);
        for(int i=1;i<numTiles-1;i+=2) {
            for (int j = 1; j < numTiles - 1; j++) {
                    map[j][i] = new Tile(j, i, PATH);
            }

        }
        map[0][0]=new Tile(0,0,PATH);
        map[0][1]=new Tile(0,1,PATH);

        /*
        map[1][18]=new Tile(1,18,PATH);
        map[5][18]=new Tile(5,18,PATH);
        map[9][18]=new Tile(9,18,PATH);
        map[13][18]=new Tile(13,18,PATH);
        map[17][18]=new Tile(17,18,PATH);
        */
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
