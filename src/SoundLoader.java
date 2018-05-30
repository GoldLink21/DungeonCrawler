import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundLoader{

    //Does not work at school computer for some reason
    // As such, I leave it unimplemented in the final release

    private Clip clip;

    SoundLoader(final String fileName,boolean loop){
        try{
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("resources/sfx/"+fileName)));
            if(loop)
                clip.setLoopPoints(0,-1);
            clip.start();
            //Catching multiple exceptions at once with just |
        }catch(UnsupportedAudioFileException|IOException|LineUnavailableException e){e.printStackTrace();}
    }

    SoundLoader(final String fileName){
        try{
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("resources/sfx/"+fileName)));
            clip.start();
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