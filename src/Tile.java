import java.awt.*;

public class Tile {
    int x,y,value;


    
    public Tile(int value){

        this.value = value;
    }

    public int getValue(){return value;}

    public void setValue(int value){this.value = value;}

    public Color getColor(){
        switch(value) {
            case 0: return Color.DARK_GRAY;//Wall
            case 1: return Color.LIGHT_GRAY;//Air
            case 2: return Color.RED;//Fire/lava
            default: return Color.GREEN;
        }
    }
}
