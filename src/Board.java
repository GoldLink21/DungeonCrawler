import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    private final int BOARD_WIDTH= 500,BOARD_HEIGHT= 500;

    Player player;
    Floor floor1;
    Timer timer;
    Game game;


    public Board(Game game){
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        this.game = game;
        floor1 = new Floor(player);
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
        Font debugFont = new Font("TimesRoman",Font.PLAIN,15);
        Font titleFont = new Font("TimesRoman",Font.BOLD,30);
        super.paintComponent(g);
        if(Data.isMenu()){
            g.setFont(titleFont);
            printSimpleString("Dungeon Crawler",getWidth(),0,(int)(getHeight()*1.0/3),g);
            if(Data.DEBUG()) {
                g.setFont(debugFont);
                g.drawString("Up: " + Boolean.toString(game.isUpPressed()), 10, 20);
                g.drawString("Down: " + Boolean.toString(game.isDownPressed()), 10, 40);
                g.drawString("Left: " + Boolean.toString(game.isLeftPressed()), 10, 60);
                g.drawString("Right: " + Boolean.toString(game.isRightPressed()), 10, 80);
                g.drawString("Space: " + Boolean.toString(game.isSpacePressed()), 10, 100);

            }

        }else if(Data.isPlay()){
            floor1.paintTiles(g);
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
