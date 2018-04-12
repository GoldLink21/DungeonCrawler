import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener{

    private Board board;

    public Game(){
        setVisible(true);
        setResizable(false);
        setFocusable(true);
        setTitle("Dungeon");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        board = new Board(this);

        add(board);
        addKeyListener(this);
        pack();
        setLocationRelativeTo(null);
        board.startGame();
    }

    public static void main(String[]args){new Game();}

    @Override
    public void keyTyped(KeyEvent e) {

    }

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
            if(Data.isPlay()&&Data.DEBUG()){
                board.map.randomBoard();
            }
        }if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(Data.isMenu()) {
                board.startGame();
                Data.togglePlay();
            }else if(Data.isEnd()){
                Data.toggleEnd();
            }
        }
    }

}
