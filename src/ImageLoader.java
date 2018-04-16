import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader{
    private static BufferedImage img;
    private int x,y,newWidth,newHeight;

    public ImageLoader(int x, int y,int width, int height, int imgX,int imgY,int newWidth, int newHeight, String fileName){
        this.x=x;
        this.y=y;
        this.newHeight=newHeight;
        this.newWidth=newWidth;
        try{
            img = ImageIO.read(new File("resources/gfx/"+fileName));
        }catch(IOException e){e.printStackTrace();}
        img=img.getSubimage(imgX,imgY,width,height);
    }

    public void paint(Graphics g){g.drawImage(img.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH),x,y,null);}
}
