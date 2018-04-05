import java.awt.*;

public class Player extends Entity{
    int x,y;

    private Map map;
    private final int SPEED=1;

    public Player(int x,int y,Map map){
        super(Color.BLUE,x,y,20,20);
        this.map = map;
    }

    private Polygon makePlayerShape(){
        int rad=width/2;
        int[]xPoints={x+rad,x+width,x+rad,x};
        int[]yPoints={y,y+rad,y+height,y+rad};
        return new Polygon(xPoints,yPoints,xPoints.length);
    }

    @Override
    public void paint(Graphics g){
        Polygon pShape = makePlayerShape();
        g.setColor(Color.BLUE);
        g.fillPolygon(pShape);
        //g.fillOval(x,y,SIZE,SIZE);
        g.setColor(Color.BLACK);
        g.drawPolygon(pShape);
        //g.drawOval(x,y,SIZE,SIZE);
        g.setColor(Color.ORANGE);
        g.drawRect((int)pShape.getBounds().getX(),(int)pShape.getBounds().getY(),width,height);
    }

    private int getCurTileType(Point p){
        int curX = (int)(p.getX())/Data.getTileSize();
        int curY = (int)(p.getY())/Data.getTileSize();
        return map.getTile(curX,curY).getValue();
    }

    private boolean checkCollisions(){
        Point[]points={new Point(x,y),new Point(x+width,y),new Point(x,y+height),new Point(x+width,y+height)};
        for(int i=0;i<points.length;i++)
            if(getCurTileType(points[i])==0) return true;
        return false;
    }

    @Override
    public void move(){
        int BoardWidth = Data.getNumTiles()*Data.getTileSize();
        if(Data.isUp()&&y>0){
            y-=SPEED;
            if(checkCollisions()) y+=SPEED;
        }if(Data.isDown()&&y+height<BoardWidth){
            y+=SPEED;
            if(checkCollisions()) y-=SPEED;
        }if(Data.isRight()&&x+width<BoardWidth){
            x+=SPEED;
            if(checkCollisions()) x-=SPEED;
        }if(Data.isLeft()&&x>0){
            x-=SPEED;
            if(checkCollisions()) x+=SPEED;
        }
    }
}
