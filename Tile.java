public class Tile {
    private static Item items;
    private static Wall[] walls;
    private static int ladder;

    public Tile(Item[] i, Wall[] wI, int l){
        items = i;
        walls =wI;
        ladder =l;
    }
    public Tile(){
        walls = new wall[4];
    }
    public static String look(int facing){
        return walls[facing].getName();
    }
    
    public static String inspectWall(int direction){
        return wallInfo[direction].inspect();
    }

}
