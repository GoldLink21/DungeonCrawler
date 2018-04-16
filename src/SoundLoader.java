import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundLoader{
    public SoundLoader(final String fileName){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("resources/sfx/"+fileName)));
            clip.start();
            //Catching multiple exceptions at once with just |
        }catch(UnsupportedAudioFileException|IOException|LineUnavailableException e){e.printStackTrace();}
    }
}