import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    private Map map;
    private static int ticks = 0;
    private Timer timer;
    private boolean mapMade=false;

    private ArrayList<Entity>entities=new ArrayList<>();

    public Board(){
        setBackground(Color.LIGHT_GRAY);
        final int bSize=Data.getTileSize()*Data.getNumTiles();
        setPreferredSize(new Dimension(bSize,bSize));
        MapData.setupFloors();
    }

    private void addDarts(){
        ArrayList<Trap>traps=Map.getTraps();
        for(Trap t:traps){
            if(t instanceof DartTrap){
                DartTrap d=(DartTrap)t;
                if(d.isToFire()){
                    entities.add(new Dart(d.getX(), d.getY(), d.getDir(), map));
                    ((DartTrap)t).setToFire(false);
                }
            }
        }
    }

    public void newGame(){
        timer = new Timer(1000/60,this);
        timer.start();
    }

    public void restartGame(){
        if(!mapMade)
            map=new Map();
        if(Data.isModeClassic())
            map.loadFloor(0);
        else
            map.loadNextFloor();
        if(first){
            entities.add(0,new Player(map));
            first = false;
        }
        ((Player)entities.get(0)).resetPosition();
    }

    private void paintAndCollisions(Graphics g){
        map.paint(g);
        for(Entity e:entities){
            e.paint(g);
            e.move();
        }
        addDarts();
        checkCollisions();
        removeEntities();
    }

    private void checkCollisions(){
        for(int i=1;i<entities.size();i++){
            if(entities.get(i).collidesWith(entities.get(0))){
                entities.remove(i);
                ((Player)entities.get(0)).resetPosition();
                if(Data.isModeEndless())
                    Data.setEndlessLives(Data.getEndlessLives()-1);
            }
        }
    }

    private void removeEntities(){
        int i=0;
        while(i<entities.size()){
            if(entities.get(i).isRemove())entities.remove(i);
            else i++;
        }
    }

    private void removeDarts(){
        int i=1;
        while(i<entities.size()){
            if(entities.get(i)instanceof Dart) {
                entities.remove(i);
                i--;
            }
            i++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        repaint();
        if(Data.isPlay()&&Data.isModeClassic())ticks++;
        if(Data.isMenu())ticks=0;
    }

    private boolean first = true;

    private final Font titleFont=new Font("TimesRoman",Font.BOLD,28),
            subtitleFont=new Font("TimesRoman",Font.BOLD,16);

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(Data.isPlay()) {
            paintAndCollisions(g);
        }else if(Data.isMenu()) {
            printCentered("Dungeon Crawler",titleFont,1.0/3,g);
            printCentered("Press Enter to play Classic mode",subtitleFont,.5,g);
            printCentered("Press Backspace to play Endless mode",subtitleFont,2/3.0,g);
        }else if(Data.isEnd()){
            printCentered("The End",titleFont,1.0/3,g);
            if(Data.isModeClassic()){
                printCentered("You made it in "+Math.round(ticks/60.0*10)/10.0+" seconds",subtitleFont,3/6.0,g);
            }else{
                String flr = Data.getEndlessLevels()+" floor";
                if(Data.getEndlessLevels()!=1)flr+="s";
                printCentered("You made it through "+flr,subtitleFont,3/6.0,g);
            }
            printCentered("Press Enter to continue",subtitleFont,4/6.0,g);
        }
    }

    private void printSimpleString(String s,int width,int YPos,Graphics g){
        g.drawString(s,(width/2-(int)(g.getFontMetrics().getStringBounds(s,g).getWidth())/2),YPos);}

    private void printCentered(String s,Font font,double yPos,Graphics g){
        g.setFont(font);
        printSimpleString(s,getWidth(),(int)(getHeight()*yPos),g);
    }
}