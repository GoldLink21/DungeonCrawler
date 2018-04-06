import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    private final int BOARD_SIZE = Data.getTileSize()*Data.getNumTiles();

    //With it like this, the array index values are the x,y coords


    Map map;
    Timer timer;
    Game game;

    ArrayList<Entity> entities = new ArrayList<>();

    public Board(Game game){
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(BOARD_SIZE,BOARD_SIZE));
        this.game = game;
        MapData.setupFloors();
        map = new Map();


    }

    public void startGame(){
        timer = new Timer(1000/60,this);
        timer.start();


    }

    public void paintAndMoveEntities(Graphics g){
        for(int i=0;i<entities.size();i++) {
            entities.get(i).paint(g);
            entities.get(i).move();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private boolean first = true;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(Data.isPlay()) {
            if(first){
                entities.add(0,new Player(map));
                entities.get(0).setPosition(1,1);
                first = false;
            }
            map.paint(g);
            paintAndMoveEntities(g);

        }

        Font titleFont = new Font("TimesRoman",Font.BOLD,30);

        if(Data.isMenu()){
            g.setFont(titleFont);
            printSimpleString("Dungeon Crawler",getWidth(),0,(int)(getHeight()*1.0/3),g);


        }else if(Data.isPlay()){

        }
    }

    private void printDebugText(Graphics g){
        if(Data.DEBUG()) {
            g.setFont(new Font("TimesRoman",Font.PLAIN,15));
            g.setColor(Color.BLACK);
            int curY=0;
            g.drawString("Up: "+Boolean.toString(Data.isUp()),10,curY+=20);
            g.drawString("Down: "+Boolean.toString(Data.isDown()),10,curY+=20);
            g.drawString("Left: "+Boolean.toString(Data.isLeft()),10,curY+=20);
            g.drawString("Right: "+Boolean.toString(Data.isRight()),10,curY+=20);
            g.drawString("Space: "+Boolean.toString(Data.isSpace()),10,curY+=20);

        }
    }

    private void printSimpleString(String s,int width,int XPos,int YPos,Graphics g2d){
        g2d.drawString(s,(width/2-(int)(g2d.getFontMetrics().getStringBounds(s,g2d).getWidth())/2)+XPos,YPos);
    }

}
