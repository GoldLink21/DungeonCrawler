import java.awt.*;

public class Dart extends Entity {
    public Dart(int x, int y, int dir, Map map) {
        super(Color.green, x, y, 10, 10, map);
    }

    private boolean checkCollision(){
        int[]temp=getCornerTypes();
        for(int i=0;i<temp.length;i++)
            if(temp[i]==MapData.WALL)
                return true;
        return false;
    }

    @Override
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x,y,width,height);
        g.setColor(Color.WHITE);
        g.drawRect(x,y,width,height);
    }

    @Override
    public void move(){
        switch(dir){
            case Data.DIR_DOWN:
                y++;
                break;
            case Data.DIR_UP:
                y--;
                break;
            case Data.DIR_LEFT:
                x--;
                break;
            case Data.DIR_RIGHT:
                x++;
                break;
        }
    }
}
