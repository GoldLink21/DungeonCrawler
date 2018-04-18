import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private int x,y,value,variant;

    //Variant is for a different look of the same tile
    //WALL=0,PATH=1,LAVA=2,END=3,START=4
    private static Color[]colors={(Color.DARK_GRAY),(Color.LIGHT_GRAY),(Color.RED).darker().darker(),(Color.WHITE),
            (Color.CYAN)};

    private int[] numVariants={1,1,1,1,1};
    private String[] tileNames={"wall","","","",""};

    private BufferedImage[] getVariants(int value,String fileName){
        BufferedImage[]temp=new BufferedImage[numVariants[value]];
        for(int i=0;i<numVariants[value];i++){
            temp[i]=ImageLoader.getImg(fileName+i+".png");
        }
        return temp;
    }

    private BufferedImage[][]getAllImages(){
        BufferedImage[][]temp;


    }

    private static BufferedImage[]images={ImageLoader.getImg("wall.png"),
            ImageLoader.getImg("clay.png"),ImageLoader.getImg("LavaGif.gif"),
            ImageLoader.getImg("start.png"),ImageLoader.getImg("end.png")};

    int tSize=Data.getTileSize();

    public Tile(int x,int y,int value){
        this.x=Data.getTileSize()*x;
        this.y=Data.getTileSize()*y;
        this.value = value;
        variant=1;
    }public Tile(int x,int y,int value,int variant){
        this.x=Data.getTileSize()*x;
        this.y=Data.getTileSize()*y;
        this.value = value;
        this.variant=variant;
    }

    public int getVariant(){return variant;}

    public int getValue(){return value;}

    public void paint(Graphics g){
        int s=Data.getTileSize();
        if(images[value]!=null) {
            g.drawImage(images[value].getScaledInstance(s, s, Image.SCALE_SMOOTH), x, y, null);
        }else{
            g.setColor(colors[value]);
            g.fill3DRect(x,y,s,s,true);
        }

    }
}
