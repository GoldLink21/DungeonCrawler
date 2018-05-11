import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundLoader{

    private Clip clip;

    public SoundLoader(final String fileName,boolean loop,boolean playOnStart){
        try{
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("resources/sfx/"+fileName)));
            if(loop)
                clip.setLoopPoints(0,-1);
            if(playOnStart)
                clip.start();
            //Catching multiple exceptions at once with just |
        }catch(UnsupportedAudioFileException|IOException|LineUnavailableException e){e.printStackTrace();}
    }

    public void stop(){clip.stop();}

    public void start(){
        if(clip.isRunning()){
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }else{
            clip.start();
        }
    }
}