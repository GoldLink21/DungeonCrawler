import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader{
    //Helps load in images either by making an object that you can paint, or fetching the image for you
    private static BufferedImage img;
    private int x,y,newWidth,newHeight;

    ImageLoader(int x, int y,int width, int height, int imgX,int imgY,int newWidth, int newHeight, String fileName){
        this.x=x;
        this.y=y;
        this.newHeight=newHeight;
        this.newWidth=newWidth;
        try{
            img = ImageIO.read(new File("resources/gfx/"+fileName));
            img=img.getSubimage(imgX,imgY,width,height);
        }catch(IOException e){
            e.printStackTrace();
            img = null;
        }
    }

    public void paint(Graphics g){
        try{
            g.drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), x, y, null);
        }catch(NullPointerException e){
            g.fillRect(x,y,newWidth,newHeight);
        }
    }

    public static BufferedImage getImg(String fileName){
        //Used to fetch the image itself, bypassing the object
        
        try{
            return ImageIO.read(new File("resources/gfx/"+fileName));
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;


    }
}
