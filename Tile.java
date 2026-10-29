import java.util.ArrayList;

public class Tile {
    private ArrayList<Item> items;
    private Wall[] walls;
    private int ladder;
    private int[] coords;
    private String lookText;
    private String fullText;
    private boolean dark;

    public Tile( Wall[] w, int l, ArrayList<Item> i, int[] cds, String lt, String ft, boolean d){
        walls = w;
        items = i;
        ladder = l;
        coords = cds;
        lookText = lt;
        fullText = ft;
        dark = d;
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

    public Boolean[] solidWalls(){
        Boolean[] solids = new Boolean[4];
        int i =0;
        for(Wall w:this.getWalls()){
            solids[i]=w.getSolid();
            i++;
        }
        return solids;
    }

    public String look(int x, int y, int dir, Floor fl) {
        Wall[] myWalls = this.getWalls();

        int lookX = x;
        int lookY = y;

        if (dir==0) {
            lookY--;
        } else if (dir==1) {
            lookX++;
        } else if (dir==2) {
            lookY++;
        } else if (dir==3) {
            lookX--;
        }
        
        Tile targetTile = fl.searchForTile(lookX, lookY);
        
        if(!myWalls[dir].getSolid()) {
            if (Game.torchTime>0 || !targetTile.isDark()) {
                    return targetTile.getLookText();
            }   else{
            if(targetTile.isDark() && !this.isDark()){
                    return "There's nothing in the way";
                }else{
                    return "Too dark to see (no wall)";
                }
        } 
    }else{
        if(targetTile.isDark() && !this.isDark()){
                    return "There's something in the way";
        }else{
                return "Too dark to see (Something in the way)";
        }
 }
}
       
    public boolean isDark() {
        return dark;
    }

}