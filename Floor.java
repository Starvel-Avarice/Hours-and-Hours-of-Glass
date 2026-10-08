import java.util.ArrayList;

public class Floor {
    private ArrayList<Tile> tiles;
    private String name;
    public Floor (ArrayList<Tile> tList, String n) {
        tiles = tList;
        name = n;
    }

    public Floor() {
        
    }

    public String getName() {
        return name;
    }

}