import java.awt.*;

public class Map {
    Tile[][]map;

    private final int WALL = 0,PATH=1,LAVA=2;

    private final int NUM_TILES = Data.getNumTiles();
    public Map(){
        map = new Tile[NUM_TILES][NUM_TILES];
        fillBoard(WALL);
    }

    public void randomBoard(){
        for (int i = 0; i < NUM_TILES; i++) {
            for (int j = 0; j < NUM_TILES; j++) {
                map[i][j]=new Tile((int)(Math.random()*3));
            }
        }
    }

    private void fillBoard(int value){
        for (int i = 0; i < NUM_TILES; i++) {
            for (int j = 0; j < NUM_TILES; j++) {
                map[i][j]=new Tile(value);
            }
        }
    }

    public void floorOne(){
        fillBoard(WALL);
        for(int i=1;i<NUM_TILES-1;i++) {
            map[i][NUM_TILES-2]=new Tile(PATH);
            map[i][1] = new Tile(PATH);
        }for(int i=1;i<NUM_TILES-1;i++) {
            map[1][i] = new Tile(PATH);
            map[NUM_TILES - 2][i] = new Tile(PATH);
        }
    }

    public void paint(Graphics g){
        for (int i = 0; i < NUM_TILES; i++) {
            for (int j = 0; j < NUM_TILES; j++) {
                int x = i * Data.getTileSize();
                int y = j * Data.getTileSize();
                map[i][j].setColor(g);
                g.fillRect(x, y, Data.getTileSize(), Data.getTileSize());
            }
        }
    }
}
