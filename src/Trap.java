import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Trap implements ActionListener{
    int[]x,y;
    int cur,type;
    Map map;
    Timer timer;
    boolean isForward,first;

    public Trap(int[]x,int[]y,int delay,Map map){
        this.x=x;
        this.y=y;
        this.map=map;
        this.cur=0;
        isForward=true;
        first=true;
        this.timer=new Timer(1000/delay,this);
        timer.start();
    }

    public void reset(){
        setCurTile(type);
        cur = 0;
        setCurTile(MapData.LAVA);
    }

    private int getCurType(){return map.getTile(x[cur],y[cur]).getValue();}

    public void setCurTile(int type){map.setTile(x[cur],y[cur],type);}

    @Override
    public void actionPerformed(ActionEvent e){
        if(first){
            type=getCurType();
            first=false;
        }
        setCurTile(type);
        if(isForward){
            if(cur>x.length-2){
                isForward=false;
                cur--;
            }else
                cur++;
        }else{
            if(cur<1){
                isForward=true;
                cur++;
            }else
                cur--;
        }
        type=getCurType();
        setCurTile(MapData.LAVA);
    }
}
