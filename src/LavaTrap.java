import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LavaTrap extends Trap implements ActionListener{
    private int cur,type;
    private boolean isForward,first,circular;

    private int[]x,y;

    LavaTrap(int[]x,int[]y,int delay,boolean circular,Map map){
        super(delay,map);
        this.x=x;
        this.y=y;
        type=getCurType();
        cur=0;
        first=true;
        isForward=true;
        this.circular=circular;
    }

    private int getCurType(){return map.getTile(x[cur],y[cur]).getValue();}

    private void setCurTile(int type){map.setTile(x[cur],y[cur],type);}

    @Override
    public void actionPerformed(ActionEvent e){
        setCurTile(type);
        if(first){
            type=getCurType();
            first=false;
            cur--;
        }
        if(timer.isRunning()){
            if(x.length > 1){
                if(circular) {
                    if (isForward) {
                        if (cur > x.length - 2) {
                            isForward = false;
                            cur--;
                        } else
                            cur++;
                    } else {
                        if (cur < 1) {
                            isForward = true;
                            cur++;
                        } else
                            cur--;
                    }
                    type = getCurType();
                    setCurTile(MapData.LAVA);
                }else{
                    if(cur>x.length-2){
                        cur=0;
                    }else
                        cur++;
                    type = getCurType();
                    setCurTile(MapData.LAVA);
                }
            }else
                setCurTile(MapData.LAVA);
        }
    }
}
