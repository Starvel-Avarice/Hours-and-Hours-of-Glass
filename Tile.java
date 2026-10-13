import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public String getFullText(){
        return fullText;
    }
}