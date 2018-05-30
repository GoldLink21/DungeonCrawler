import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Trap implements ActionListener{

    //Just a thing that does things on a timer

    Timer timer;

    Trap(int delay){
        timer=new Timer(5000/delay,this);
        timer.start();
    }

    protected void stop(){timer.stop();}

    @Override
    public void actionPerformed(ActionEvent e) {}
}
