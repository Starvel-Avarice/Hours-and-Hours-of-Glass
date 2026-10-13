import java.util.ArrayList;

public class Tile {
    private Item[] items;
    private Wall[] walls;
    private int ladder;
    private int[] coords;
    private String lookText;
    private String fullText;

    public Tile( Wall[] w, int l, Item[] i, int[] cds, String lt, String ft ){
        walls = w;
        items = i;
        ladder = l;
        coords = cds;
        lookText = lt;
        fullText = ft;
    }

    public Tile(Boolean[] w){
    items = null;
    ladder = 0;
    }       
    public Tile() {

    }

    public int[] getCoords() {
        return coords;
    }

    public Wall[] getWalls() {
        return walls;
    }

    public Wall getWall(int x) {
        return walls[x];
    }

    public Item[] getItems() {
        return items;
    }

    public String getLookText() {
        return lookText;
    }

    public String getFullText() {
        return fullText;
    }

}