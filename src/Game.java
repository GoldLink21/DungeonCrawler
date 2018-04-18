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
        setTitle("Dungeon");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        board = new Board();
        add(board);
        addKeyListener(this);
        pack();
        setLocationRelativeTo(null);
        board.newGame();
    }

    public static void main(String[]args){new Game();}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D)
            Data.setRight(true);
        if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A)
            Data.setLeft(true);
        if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W)
            Data.setUp(true);
        if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S)
            Data.setDown(true);
        if(e.getKeyCode()==KeyEvent.VK_SPACE)
            Data.setSpace(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D)
            Data.setRight(false);
        if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A)
            Data.setLeft(false);
        if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W)
            Data.setUp(false);
        if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S)
            Data.setDown(false);

        if(e.getKeyCode()==KeyEvent.VK_SPACE) {
            Data.setSpace(false);
            if(Data.isPlay()&&Data.DEBUG())board.map.randomBoard();
        }

        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(Data.isMenu()) {
                Data.setMode(Data.MODE_CLASSIC);
                Data.togglePlay();
                System.out.println("Classic Started");
            }else if(Data.isEnd()){
                board.map.setTrapsAdded(false);
                Data.toggleEnd();
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
            if(Data.isMenu()) {
                Data.setMode(Data.MODE_ENDLESS);
                Data.newEndless();
                Data.togglePlay();
                System.out.println("Endless started");
            }
        }
    }

}
