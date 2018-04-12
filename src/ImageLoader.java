import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class ImageLoader extends Component {

    private static BufferedImage img;
    private int x,y,width,height,newWidth,newHeight;

    public ImageLoader(int x, int y,int width, int height, int imgX,int imgY,int newWidth, int newHeight, String fileName){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.newHeight=newHeight;
        this.newWidth=newWidth;
        try{
            img = ImageIO.read(new File("resources/gfx/"+fileName));
        }catch (IOException e){}
        img = img.getSubimage(imgX,imgY,width,height);
    }

    @Override
    public void paint(Graphics g){
        Image tmp = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        g.drawImage(tmp, x, y, null);
        //g.drawImage(img,x,y,width,height,null);
    }

    /*
    @Override
    public void paint(Graphics g){
        g.drawImage(img,x,y,width,height,null);
    }
    */
}
