import java.awt.*;

public class Dart extends Entity {

    public Dart(int x, int y, int dir, Map map) {
        super(Color.green, x, y, 10, 10, map);
        this.dir=dir;
        this.x+=3;
        this.y+=3;
    }

    private boolean checkCollision(){
        int[]temp=getCornerTypes();
        for(int i:temp)
            if(i==MapData.WALL)
                return true;
        return false;
    }

    private final boolean done=true;

    @Override
    public void paint(Graphics g){
        Polygon p=getPoly();
        g.setColor(color);
        g.fillPolygon(p);
        g.setColor(Color.WHITE);
        g.drawPolygon(p);
    }

    private Polygon getPoly(){
        Polygon p=new Polygon();
        switch (dir) {
            case Data.DIR_UP:
                p.addPoint(x+width/2,y);
                p.addPoint(x+width,y+height);
                p.addPoint(x,y+height);
                break;
            case Data.DIR_RIGHT:
                p.addPoint(x,y);
                p.addPoint(x,y+height);
                p.addPoint(x+width,y+height/2);
                break;
            case Data.DIR_DOWN:
                p.addPoint(x,y);
                p.addPoint(x+width,y);
                p.addPoint(x+width/2,y+height);
                break;
            case Data.DIR_LEFT:
                p.addPoint(x+width,y);
                p.addPoint(x+width,y+height);
                p.addPoint(x,y+height/2);
                break;
        }
        return p;
    }

    @Override
    public void move(){
        switch(dir){
            case Data.DIR_DOWN:y++;break;
            case Data.DIR_UP:y--;break;
            case Data.DIR_LEFT:x--;break;
            case Data.DIR_RIGHT:x++;break;
        }if(checkCollision())
            remove=true;
    }
}
