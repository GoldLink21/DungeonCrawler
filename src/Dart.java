import java.awt.*;

public class Dart extends Entity {

    Dart(int x, int y, int dir) {
        super(Color.green, x, y, 9, 9);
        this.dir=dir;
        this.x+=3;
        this.y+=3;
    }

    private boolean checkCollision(){
        //Checks if the Dart has hit a wall
        int[]temp=getCornerTypes();
        for(int i:temp)
            if(i==MapData.WALL)
                return true;
        return false;
    }

    private Polygon getPoly(){
        //Makes a triangle that faces different ways depending on the direction of the Dart
        Polygon p=new Polygon();
        switch(dir){
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
    public void paint(Graphics g){
        Polygon p=getPoly();
        g.setColor(color);
        g.fillPolygon(p);
        g.setColor(Color.WHITE);
        g.drawPolygon(p);
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
