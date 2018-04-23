import java.awt.*;

public class Dart extends Entity {



    public Dart(int x, int y, int dir, Map map) {
        super(Color.green, x, y, 10, 10, map);
        this.dir=dir;
    }

    private boolean checkCollision(){
        int[]temp=getCornerTypes();
        for(int i=0;i<temp.length;i++)
            if(temp[i]==MapData.WALL)
                return true;
        return false;
    }

    @Override
    public void paint(Graphics g){
        g.setColor(color);
        int[]xArr=new int[3],yArr=new int[3];

        //Initial values that are the same on two of one type
        if(dir==Data.DIR_LEFT||dir==Data.DIR_RIGHT){
            yArr[0]=y;//top
            yArr[1]=y+height;//bottom
            yArr[2]=y+height/2;//mid
        }else{
            xArr[0]=x;//left
            xArr[1]=x+width;//right
            xArr[2]=x+width/2;//mid
        }
        //Find specifics on exact dir
        switch(dir){
            case Data.DIR_UP:

                break;
            case Data.DIR_RIGHT:

                break;
            case Data.DIR_DOWN:

                break;
            case Data.DIR_LEFT:

                break;
        }
        g.fillPolygon(xArr,yArr,3);
        g.setColor(Color.WHITE);
        g.drawPolygon(xArr,yArr,3);
    }

    @Override
    public void move(){
        switch(dir){
            case Data.DIR_DOWN:
                y++;
                break;
            case Data.DIR_UP:
                y--;
                break;
            case Data.DIR_LEFT:
                x--;
                break;
            case Data.DIR_RIGHT:
                x++;
                break;
        }if(checkCollision()){
            remove=true;
        }
    }
}
