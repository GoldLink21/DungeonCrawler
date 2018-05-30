import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class Board extends JPanel implements ActionListener {

    private Map map;
    private static int ticks = 0;
    private static Timer timer;
    private boolean mapMade=false;

    private static ArrayList<Entity>entities=new ArrayList<>();
    private static ArrayList<Entity>toAdd=new ArrayList<>();

    Board(){
        setBackground(Color.LIGHT_GRAY);
        final int bSize=Data.getTileSize()*Data.getNumTiles();
        setPreferredSize(new Dimension(bSize,bSize));
        MapData.setupFloors();
    }

    private void addDarts(){
        //Adds darts to traps that are ready to fire
        ArrayList<Trap>traps=Map.getTraps();
        for(Trap t:traps){
            if(t instanceof DartTrap){
                DartTrap d=(DartTrap)t;
                if(d.isToFire()){
                    entities.add(new Dart(d.getX(), d.getY(), d.getDir()));
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
        //This is run whenever you start a new game and sets the map
        if(!mapMade)
            map=new Map();
        Map.loadNextFloor();
        if(first){
            entities.add(0,new Player());
            first = false;
        }
        ((Player)entities.get(0)).resetPosition();
        Data.resetKeys();
    }



    private void paintAndCollisions(Graphics g){
        map.paint(g);
        for(Entity e:entities){
            e.paint(g);
            e.move();
        }
        addEntities();
        addDarts();
        checkCollisions();
        removeEntities();
    }

    private void addEntities(){
        //Takes from the entity que and adds to the actual entities
        //This fixes the InterruptedException
        while(toAdd.size()>0)
            entities.add(toAdd.remove(0));
    }

    private void checkCollisions(){
        for(int i=1;i<entities.size();i++){
            if(entities.get(i).collidesWith(entities.get(0))){
                if(entities.get(i)instanceof Dart) {
                    ((Player) entities.get(0)).resetPosition();
                    if (Data.isModeEndless())
                        Data.setEndlessLives(Data.getEndlessLives() - 1);
                }else if(entities.get(i)instanceof Key){
                    Data.increaseKeys();
                    System.out.println("Keys: "+Data.getKeys());
                }
                entities.remove(i);
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

    public static void removeDarts(){
        int i=1;
        while(i<entities.size()){
            if(entities.get(i)instanceof Dart||entities.get(i)instanceof Key) {
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

    //Helps determine the very first tick to create the map and add only a single player
    private boolean first = true;

    private final Font titleFont=new Font("TimesRoman",Font.BOLD,28),
            subtitleFont=new Font("TimesRoman",Font.BOLD,16);

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(Data.isPlay()) {
            paintAndCollisions(g);
        }
        printAllText(g);
    }

    private void printAllText(Graphics g){
        //This method handles all of the text to be rendered on the screen
        g.setColor(Color.BLACK);
        if(Data.isPlay()){
            g.setColor(Color.WHITE);
            g.setFont(subtitleFont);
            g.drawString("Keys: "+Data.getKeys(),getHeight()-70,16);
            if(Data.isModeEndless()){
                g.drawString("Lives: "+Data.getEndlessLives(),10,16);
            }
        }if(Data.isMenu()){
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
        //I condensed it down to only one line
        g.drawString(s,(width/2-(int)(g.getFontMetrics().getStringBounds(s,g).getWidth())/2),YPos);
    }


    private void printCentered(String s,Font font,double yPos,Graphics g){
        //Modified version of printSimpleString for only printing in the center of the screen
        g.setFont(font);
        printSimpleString(s,getWidth(),(int)(getHeight()*yPos),g);
    }

    public static void addEntity(Entity e){toAdd.add(e);}
}