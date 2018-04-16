import java.awt.Graphics;

public interface Move {
    void paint(Graphics g);
    void setPosition(int x, int y);
    void move();
}
