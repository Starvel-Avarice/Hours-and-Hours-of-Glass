import java.util.ArrayList;

public class Tile {
    private ArrayList<Item> items;
    private Wall[] walls;
    private int ladder;
    private int[] coords;
    private String lookText;
    private String fullText;

    public Tile( Wall[] w, int l, ArrayList<Item> i, int[] cds, String lt, String ft ){
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

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getLookText() {
        return lookText;
    }

    public String getFullText() {
        return fullText;
    }

    public Item takeItem(String itemName){
        for(int i=0; i<items.size(); i++){
            System.out.println(items.get(i).getName());
            if(items.get(i).getName().equals(itemName)){
                int taken = items.get(i).getItemId();
                items.remove(i);
                return new Item(taken);
            }
        }
        return null;
    }



}