import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundLoader{

    public SoundLoader(String fileName){play(fileName);}

    //I stole most of these methods, just made them more efficient
    private boolean completed;

    public void play(String audioFilePath){
        try{
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(audioFilePath));
            AudioFormat format = audioStream.getFormat();
            Clip audioClip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, format));

            //I figured out how to use a lambda!
            audioClip.addLineListener(e->{if(e.getType()==LineEvent.Type.STOP)completed = true;});

            audioClip.open(audioStream);
            audioClip.start();
            while(!completed)try{Thread.sleep(0);}catch(InterruptedException ex){ex.printStackTrace();}
            audioClip.close();
        }catch(UnsupportedAudioFileException ex){ex.printStackTrace();
        }catch(LineUnavailableException ex){ex.printStackTrace();
        }catch(IOException ex){ex.printStackTrace();}
    }
}
