import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundLoader{
    public SoundLoader(final String fileName){
        try{
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("resources/sfx/"+fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }catch(UnsupportedAudioFileException e){e.printStackTrace();
        }catch(IOException e){e.printStackTrace();
        }catch(LineUnavailableException e){e.printStackTrace();}
    }
}