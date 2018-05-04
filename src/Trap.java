import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Trap implements ActionListener{
    Map map;
    private Timer timer;

    protected Trap(int delay,Map map){
        this.map=map;
        timer=new Timer(5000/delay,this);
        timer.start();
    }

    protected void stop(){timer.stop();}

    @Override
    public void actionPerformed(ActionEvent e) {}
}
