import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener{

    private Board board;

    private Game(){
        setVisible(true);
        setResizable(false);
        setFocusable(true);
        setTitle("Dungeon Crawler");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        board = new Board();
        add(board);
        addKeyListener(this);
        board.newGame();
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[]args){new Game();}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {setKeys(e,true);}

    @Override
    public void keyReleased(KeyEvent e) {
        setKeys(e,false);

        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(Data.isMenu()) {
                Data.setMode(Data.MODE_CLASSIC);
                Data.startGame();


                board.restartGame();

                System.out.println("Classic Started");
            }else if(Data.isEnd()){
                Map.clearTraps();
                Data.toMenu();
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
            if(Data.isMenu()) {
                Data.setMode(Data.MODE_ENDLESS);
                Data.newEndless();
                Data.startGame();
                board.restartGame();
                System.out.println("Endless started");
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            Data.toMenu();
            System.out.println("Rage Quit!");
            Map.setFloor(-1);
        }
    }

    //Helper method for making the keys for the controls easier to setup
    private void setKeys(KeyEvent e,boolean bool){
        if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D)
            Data.setRight(bool);
        if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A)
            Data.setLeft(bool);
        if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W)
            Data.setUp(bool);
        if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S)
            Data.setDown(bool);
    }

}
