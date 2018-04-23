import java.awt.event.ActionEvent;

public class DartTrap extends Trap {

    private int x,y,dir;

    private boolean toFire = false,tileSet=false;

    public DartTrap(int x, int y, int delay,int dir, Map map) {
        super(delay, map);
        this.x=x;
        this.y=y;
        this.dir=dir;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!tileSet){
            map.setTile(x,y,MapData.TRAP);
            tileSet=true;
        }
        toFire=true;
    }

    public void setToFire(boolean bool){toFire=bool;}

    public boolean isToFire(){return toFire;}

    public int getDir(){return dir;}

    public int getX() {return x;}

    public int getY() {return y;}
}
