import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundLoader{
    
    public static void playSound(String fileName){
      Clip clip = null;
      try{
        AudioInputStream audioIn = AudioSystem.getAudioInputStream( getClass().getResource( filename ) );
        clip = AudioSystem.getClip();
        clip.open( audioIn );
      }catch( Exception e ){e.printStackTrace();}
      
      if( clip.isRunning() ) clip.stop();
            clip.setFramePosition( 0 );
      clip.start();
    }
  
}
