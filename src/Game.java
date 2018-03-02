import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener{

    private Board board;
    private Player player;

    private boolean rightPressed,leftPressed,upPressed,downPressed,spacePressed;

    public Game(){
        setVisible(true);
        setResizable(false);
        setFocusable(true);
        setTitle("Dungeon");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        board = new Board(this,player);

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
            rightPressed = true;
        if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A)
            leftPressed = true;
        if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W)
            upPressed = true;
        if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S)
            downPressed = true;
        if(e.getKeyCode()==KeyEvent.VK_SPACE)
            spacePressed=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D)
            rightPressed = false;
        if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A)
            leftPressed = false;
        if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W)
            upPressed = false;
        if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S)
            downPressed = false;
        if(e.getKeyCode()==KeyEvent.VK_SPACE&&Data.isMenu()) {
            spacePressed = false;
            board.startGame();
            Data.togglePlay();
        }
    }

    public boolean isRightPressed(){return rightPressed;}
    public boolean isLeftPressed(){return leftPressed;}
    public boolean isUpPressed(){return upPressed;}
    public boolean isDownPressed(){return downPressed;}
    public boolean isSpacePressed(){return spacePressed;}
}
