import java.util.HashMap;
import java.util.Timer;

public class Item {
    private String lore;
    private String name;
    private String action;
    private boolean obtainable;
    private int knownId;
    private static HashMap<Integer, Item> itemHash= new HashMap<>();

    public Item(String n, String l, String u, int id, boolean o){
        lore=l;
        name=n;
        action=u;
        knownId=id;
        obtainable = o;
    }

    public Item(int id){
       if(itemHash.containsKey(id)){
        Item i = itemHash.get(id);
        lore = i.getLore();
        name = i.getName();
        //action = i.use();
        knownId = id;
       }else{
        System.out.println("Unkown Item ID:"+id);
       }
    }

    public static void genItemHash(){
        // itemHash.put(0, new Item("Compass","Tells you where North is", "NONE"));
        itemHash.put(1, new Item("Torch", "Lights up the way", "TorchUse", 1, true));
        itemHash.put(2, new Item("Blue Key", "A key to a door.", "KeyUse", 2, true));
        itemHash.put(4, new Item("The Ring", "Grants passageway to the Hinge.", "RingUse", 4, true));
        itemHash.put(5, new Item("The Pink Puzzle", "The Pink Puzzle", "PinkPuzzleUse", 5, false));
        itemHash.put(666, new Item("Death", "Kills the player", "InstantDeath", 666, false));
        itemHash.put(1000, new Item("Glass Shard Holder", "Stores glass shards (back-end)", "ShardHolderUse", 1000, false));
        itemHash.put(3000, new Item("Goblin", "Combat with the goblin.", "Goblin", 3000, false));
    }

    public int getItemId(){
        return knownId;
    }

    public void use(){
        switch (knownId) {
            case 1:
                Game.torchTime = 10000;
                break;
            case 2: 
                if (Game.currentFloor.getName() == "floor1_1.txt" && Game.playerX == 1 && Game.playerY == 2) {
                    Tile theTile = Game.currentFloor.searchForTile(1, 2);
                    theTile.getWalls()[1].setSolid(false);
                }
            case 5:
                if (Game.currentFloor.getName() == "floor1_1.txt" && Game.playerX == -3 && Game.playerY == 0) {
                    Tile theTile = Game.currentFloor.searchForTile(-3, 0);
                    theTile.getWalls()[2].setSolid(false);
                }
            
            default:
                System.out.println("Does nothing");
                break;
        }
    }

    public String getLore(){
        return lore;
    }

    public String getName(){
        return name;
    }

}