import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Trap implements ActionListener{

    int delay;
    Map map;

    Timer timer;

    protected Trap(int delay,Map map){
        this.delay=delay;
        this.map=map;
        timer=new Timer(5000/delay,this);
        timer.start();
    }

    protected void stop(){timer.stop();}

    @Override
    public void actionPerformed(ActionEvent e) {}
}
