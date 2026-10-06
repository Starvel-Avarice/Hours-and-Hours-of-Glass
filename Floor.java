public class Floor {
    private Tile[] tiles;
    private String name;
    private String action;
    public Floor (Tile[] tList) {
        tiles = tList;
    }

    public String use(){
        return this.action;
    }
    public String inspect(){
        return name+": "+lore;
    }
    public String getName(){
        return name;
    }

}