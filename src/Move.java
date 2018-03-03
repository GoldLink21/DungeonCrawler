import java.awt.*;

public interface Move {
    void paint(Graphics g);
    void setPosition(double x, double y);
    void move();
    Rectangle getBounds();
}
