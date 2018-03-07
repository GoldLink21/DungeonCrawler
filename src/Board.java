import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    private final int BOARD_WIDTH= 500,BOARD_HEIGHT= 500,NUM_TILES = 20;

    //With it like this, the array index values are the x,y coords
    Tile[][]map;


    Timer timer;
    Game game;

    ArrayList<Entity> entities = new ArrayList<>();

    public Board(Game game){
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        this.game = game;
        this.map = new Tile[NUM_TILES][NUM_TILES];

        //fillBoard((int)(Math.random()*3));
        floorOne();

    }

    public void setupEntities(){

    }

    private void fillBoard(int value){
        for (int i = 0; i < NUM_TILES; i++) {
            for (int j = 0; j < NUM_TILES; j++) {
                map[i][j]=new Tile(value);
            }
        }
    }

    private void floorOne(){
        fillBoard(0);
        for(int i=1;i<NUM_TILES-1;i++) {
            map[i][NUM_TILES-2]=new Tile(1);
            map[i][1] = new Tile(1);
        }for(int i=1;i<NUM_TILES-1;i++) {
            map[1][i] = new Tile(1);
            map[NUM_TILES - 2][i] = new Tile(1);
        }
    }

    public void startGame(){
        timer = new Timer(1000/60,this);
        timer.start();

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int TILE_SIZE = getWidth()/NUM_TILES;

        if(Data.isPlay()) {
            for (int i = 0; i < NUM_TILES; i++) {
                for (int j = 0; j < NUM_TILES; j++) {
                    int x = i * TILE_SIZE;
                    int y = j * TILE_SIZE;
                    g.setColor(map[i][j].getColor());
                    g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        Font debugFont = new Font("TimesRoman",Font.PLAIN,15);
        Font titleFont = new Font("TimesRoman",Font.BOLD,30);

        if(Data.isMenu()){
            g.setFont(titleFont);
            printSimpleString("Dungeon Crawler",getWidth(),0,(int)(getHeight()*1.0/3),g);
            if(Data.DEBUG()) {
                g.setFont(debugFont);
                g.drawString("Up: " + Boolean.toString(game.isUp()), 10, 20);
                g.drawString("Down: " + Boolean.toString(game.isDown()), 10, 40);
                g.drawString("Left: " + Boolean.toString(game.isLeft()), 10, 60);
                g.drawString("Right: " + Boolean.toString(game.isRight()), 10, 80);
                g.drawString("Space: " + Boolean.toString(game.isSpace()), 10, 100);

            }

        }else if(Data.isPlay()){

        }
    }

    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d){
        //returns the LENGTH of the STRING parameter to the variable stringLen
        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        //determines the center of the WIDTH parameter and subtracts the center of the length
        //to determine the X value to start the string
        int start = width/2 - stringLen/2;
        //prints s at the desired X position with adjustment and the desired y.
        g2d.drawString(s, start + XPos, YPos);
    }

}
