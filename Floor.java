import java.util.ArrayList;

public class Floor {
    private ArrayList<Tile> tiles = new ArrayList<Tile>();
    private String name;
    public Floor (ArrayList<Tile> tList, String f, String n) {
        tiles = tList;
        name = n;
        fileName = f;
    }

    public Floor() {

    }

    public String getName() {
        return name;
    }

    public void setFileName(String newFileName) {
        fileName = newFileName;
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

}

