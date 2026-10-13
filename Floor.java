import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Floor {
    private ArrayList<Tile> tiles = new ArrayList<Tile>();
    private String name;
    public Floor (ArrayList<Tile> tList, String n) {
        tiles = tList;
        name = n;
    }

    public Floor(String fileString) {
        try {
            Scanner sc = new Scanner(new File(fileString));
            while (sc.hasNextLine()) {
                String myLine = sc.nextLine();
                String[] splits = myLine.split("\t");

                int[] wallIds = stringArrayToIntArray(splits[0].split(","));
                Wall[] walls = new Wall[wallIds.length];
                for(int i=0; i<wallIds.length; i++){
                    walls[i] = new Wall(wallIds[i]);
                }
                
                int upOrDown = Integer.parseInt(splits[1]);

                int[] itemIds = stringArrayToIntArray(splits[2].split(","));
                Item[] items = new Item[itemIds.length];
                for(int i=0; i<itemIds.length; i++){
                    items[i] = new Item(itemIds[i]);
                };

                String[] coords = splits[3].split(",");
                int[] coordInts = stringArrayToIntArray(coords);

                String lookText = splits[4];
                String fullText = splits[4];

                this.addTile(new Tile(walls, upOrDown, items, coordInts, lookText, fullText));
            }
                sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public String getName() {
        return name;
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    public String[] listTiles(){
        String[] allTiles = new String[tiles.size()];
        for(int i =0; i<tiles.size(); i++){
            allTiles[i]=tiles.get(i).getFullText();
        }
        return allTiles;
    }

    public static int[] stringArrayToIntArray(String[] oldArray) {
        int[] newArray = new int[oldArray.length];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = Integer.parseInt(oldArray[i]);
        }
        return newArray;
    }
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

}

