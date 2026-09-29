public class Tile {
    private static Item items;
    private static String[] wallInfo;
    private static int ladder;

    public Tile(Item[] i, String[] wI, int l){
        items = i;
        wallInfo =wI;
        ladder =l;
    }

    public static String inspectWall(int direction){
        return wallInfo[direction];
    }

}
