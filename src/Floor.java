import java.awt.*;

public class Floor {
    Tile[][]floorTiles;

    private final int WIDTH = 20,HEIGHT = 20;

    Player player;
    Game game;

    public Floor(Game game,Player player){
        floorTiles = new Tile[WIDTH][HEIGHT];
        this.game = game;
        this.player = player;
        floorOne();
    }

    public void fillFloor(int value){
        for(int row = 0;row<WIDTH;row++)
            for(int col = 0; col < HEIGHT; col++)
                floorTiles[row][col]=new Tile(row,col,value);
    }

    public void floorOne(){
        fillFloor(0);
        for(int row=1;row<WIDTH-1;row++)
            floorTiles[row][1]=new Tile(row,1,1);
        player = new Player(1,1,game);
    }

    public void paint(Graphics g){
        for(int row = 0;row<WIDTH;row++){
            for(int col = 0;col<HEIGHT;col++){
                floorTiles[row][col].paint(g);
            }
        }
    }
}
