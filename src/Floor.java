import javax.swing.*;
import java.awt.*;

public class Floor {
    Tile[][]floorTiles;
    Entity[][]floorEntities;
    private final int WIDTH = 20,HEIGHT = 20,TILE_SIZE=25;

    Player player;

    public Floor(Player player){
        floorTiles = new Tile[WIDTH][HEIGHT];
        floorEntities = new Entity[WIDTH][HEIGHT];
        floorOne();
        this.player = player;
    }

    public void floorOne(){
        for(int row = 0;row<WIDTH;row++) {
            for(int col = 0; col < HEIGHT; col++) {
               floorTiles[row][col]=new Tile(row,col,0);
                if(row==col||row==19-col)
                    floorTiles[row][col]=new Tile(row,col,1);
            }

        }
        /*
        for(int row=0;row<WIDTH;row++){
            for(int col=0;col<HEIGHT;col++){

            }
        }
        */
        floorEntities[0][0]=player;
    }

    public void paint(Graphics g){

        //Tiles
        for(int row = 0;row<WIDTH;row++){
            for(int col = 0;col<HEIGHT;col++){
                floorTiles[row][col].paint(g);
            }
        }

        //Entities
        for(int row=0;row<WIDTH;row++) {
            for(int col=0;col<HEIGHT;col++){
                if(floorEntities[row][col]!=null){
                    floorEntities[row][col].paint(g);
                }
            }
        }

    }

}
