import java.awt.event.ActionEvent;

public class DartTrap extends Trap {

    private int x,y,dir;

    private boolean toFire = false;

    DartTrap(int x, int y, int delay,int dir) {
        super(delay);
        this.x=x;
        this.y=y;
        this.dir=dir;
        Map.setTile(x,y,MapData.TRAP);
    }

    public void setToFire(boolean bool){toFire=bool;}

    public boolean isToFire(){return toFire;}

    public int getDir(){return dir;}

    public int getX() {return x;}

    public int getY() {return y;}

    @Override
    public void actionPerformed(ActionEvent e) {
        //Every time the timer ticks which is controlled by delay, it fires
        toFire=true;
    }
}
