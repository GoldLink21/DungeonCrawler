import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private int x,y,value,variant;

    //Variant is for a different look of the same tile
    //WALL=0,PATH=1,LAVA=2,END=3,START=4,TRAP=5;
    private static Color[]colors={(Color.DARK_GRAY),(Color.LIGHT_GRAY),(Color.RED).darker().darker(),(Color.WHITE),
            (Color.CYAN),(Color.ORANGE)};

    private int[] numVariants={1,1,1,1,1,1};

    private int maxVariants=1;
    private String[] tileNames={"wall","","","","",""};

    private BufferedImage[] getVariants(int value,String fileName){
        BufferedImage[]temp=new BufferedImage[numVariants[value]];
        for(int i=0;i<numVariants[value];i++){
            temp[i]=ImageLoader.getImg(fileName+(i+1)+".png");
        }
        return temp;
    }


    private BufferedImage[][]getAllImages(){
        BufferedImage[][]temp=new BufferedImage[numVariants.length][maxVariants];
        for(int i=0;i<temp.length;i++){
            temp[i]=getVariants(i,tileNames[i]);
        }
        return temp;
    }


    private static BufferedImage[]images={ImageLoader.getImg("wall.png"),
            ImageLoader.getImg("clay.png"),ImageLoader.getImg("LavaGif.gif"),
            ImageLoader.getImg("start.png"),ImageLoader.getImg("end.png"),ImageLoader.getImg("start.png")};

    final int tSize=Data.getTileSize();

    public Tile(int x,int y,int value){
        this.x=tSize*x;
        this.y=tSize*y;
        this.value = value;
        variant=1;
    }public Tile(int x,int y,int value,int variant){
        this.x=tSize*x;
        this.y=tSize*y;
        this.value = value;
        this.variant=variant;
    }

    public int getVariant(){return variant;}

    public int getValue(){return value;}

    public void paint(Graphics g){
        if(images[value]!=null) {
            g.drawImage(images[value].getScaledInstance(tSize,tSize,Image.SCALE_SMOOTH),x,y,null);
        }else{
            g.setColor(colors[value]);
            g.fill3DRect(x,y,tSize,tSize,true);
        }
    }
}
