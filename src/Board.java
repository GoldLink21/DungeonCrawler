import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    Map map;
    Timer timer;

    private ArrayList<Entity>entities=new ArrayList<>();

    public Board(){
        setBackground(Color.LIGHT_GRAY);
        final int bSize=Data.getTileSize()*Data.getNumTiles();
        setPreferredSize(new Dimension(bSize,bSize));
        MapData.setupFloors();
        map = new Map();
    }

    public void startGame(){
        timer = new Timer(1000/60,this);
        timer.start();
    }

    private void paintItAll(Graphics g){
        map.paint(g);
        for(Entity e:entities){
            e.paint(g);
            e.move();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){repaint();}

    private boolean first = true;

    private final Font titleFont = new Font("TimesRoman",Font.BOLD,30);

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(Data.isPlay()) {
            if(first){
                entities.add(0,new Player(map));
                ((Player)entities.get(0)).resetPosition();
                first = false;
            }
            paintItAll(g);
        }else if(Data.isMenu()) {
            printCentered("Dungeon Crawler",titleFont,1.0/3,g);
        }else if(Data.isEnd()){
            printCentered("The End",titleFont,1.0/3,g);
        }
    }

    private void printSimpleString(String s,int width,int YPos,Graphics g){
        g.drawString(s,(width/2-(int)(g.getFontMetrics().getStringBounds(s,g).getWidth())/2),YPos);}

    private void printCentered(String s,Font font,double yPos,Graphics g){
        g.setFont(font);
        printSimpleString(s,getWidth(),(int)(getHeight()*yPos),g);
    }
}