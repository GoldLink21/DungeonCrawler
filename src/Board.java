import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    private final int BOARD_SIZE = Data.getTileSize()*Data.getNumTiles();

    Map map;
    Timer timer;

    ArrayList<Entity> entities = new ArrayList<>();

    public Board(){
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(BOARD_SIZE,BOARD_SIZE));
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

    private final Font titleFont = new Font("TimesRoman",Font.BOLD,30);

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if(Data.isPlay()) {
            if(first){
                entities.add(0,new Player(map));
                entities.get(0).setPosition(1,1);
                first = false;
            }
            map.paint(g);
            paintAndMoveEntities(g2);
        }else if(Data.isMenu()) {
            printCentered("Dungeon Crawler",titleFont,1.0/3,g2);
        }else if(Data.isEnd()){
            printCentered("The End",titleFont,1.0/3,g2);
        }
    }

    private void printSimpleString(String s,int width,int XPos,int YPos,Graphics g2d){
        g2d.drawString(s,(width/2-(int)(g2d.getFontMetrics().getStringBounds(s,g2d).getWidth())/2)+XPos,YPos);
    }

    private void printCentered(String s,Font font,double yPos,Graphics g){
        g.setFont(font);
        printSimpleString(s,getWidth(),0,(int)(getHeight()*yPos),g);
    }

}
