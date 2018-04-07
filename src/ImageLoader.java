import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader extends Component {

    BufferedImage img;
    private int x,y,width,height;

    public ImageLoader(int x, int y,int width, int height, int imgx,int imgy, String fileName){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        try{
            img = ImageIO.read(new File(fileName));
        }catch (IOException e){}
        //img = img.getSubimage(imgx,imgy,width,height);

    }
    
    public BufferedImage resizeImg(int scaleWidth, int scaleHeight){
        BufferedImage out = new BufferedImage(scaleWidth, scaleHeight, img.getType());
        return out;
        
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(img,x,y,width,height,null);
    }
}
