import javax.swing.*;
import java.awt.*;

public class Floor {
    int[][]floorTiles;
    Entity[][]floorEntities;
    private final int WIDTH = 20,HEIGHT = 20,TILE_SIZE=25;

    Player player;

    public Floor(Player player){
        floorTiles = new int[WIDTH][HEIGHT];
        floorEntities = new Entity[WIDTH][HEIGHT];
        floorOne();
        this.player = player;
    }

    public void floorOne(){
        for(int row = 0;row<WIDTH;row++) {
            for(int col = 0; col < HEIGHT; col++) {
               floorTiles[row][col]=0;
                if(row==col||row==19-col)
                    floorTiles[row][col]=1;
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

    public void paintTiles(Graphics g){
        for(int row = 0;row<WIDTH;row++){
            for(int col = 0;col<HEIGHT;col++){
                int temp = floorTiles[row][col];
                //0=wall,1=air
                if(temp==0)
                    g.setColor(Color.DARK_GRAY);
                else if(temp==1)
                    g.setColor(Color.LIGHT_GRAY);

                g.fillRect(row*TILE_SIZE,col*TILE_SIZE,TILE_SIZE*TILE_SIZE,TILE_SIZE*TILE_SIZE);
            }
        }
    }

    public void paintEntities(Graphics g){
        for(int row=0;row<WIDTH;row++) {
            for(int col=0;col<HEIGHT;col++){
                if(floorEntities[row][col]==null){
                    floorEntities[row][col].paint(g);
                }
            }
        }
    }
}
