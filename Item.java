import java.util.HashMap;

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
        genItemHash();
       if(itemHash.containsKey(id)){
        Item i = itemHash.get(id);
        lore = i.getLore();
        name = i.getName();
        //action = i.use();
        knownId = id;
        obtainable = i.isObtainable();
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
        itemHash.put(6, new Item("Green Key", "A key to a door.", "KeyUse", 6, true));
        itemHash.put(7, new Item("Pickaxe", "Allows the player to break blocks.", "PickaxeUse", 7, true));
        itemHash.put(8, new Item("Burning Torch", "Lights up the way", "TorchUse", 1, false));
        itemHash.put(9, new Item("Red Key", "A key to a door.", "KeyUse", 9, true));
        itemHash.put(10, new Item("Chest", "Stores 5 glass shards.", "ChestUse", 10, true));
        itemHash.put(666, new Item("Death", "Kills the player", "InstantDeath", 666, false));
        itemHash.put(1000, new Item("Glass Shards", "Stores glass shards (back-end)", "ShardHolderUse", 1000, true));
        itemHash.put(2000, new Item("Shop", "Allows the user to purchase something.", "ShopUse", 2000, false));
        itemHash.put(3000, new Item("Goblin", "Combat with the goblin.", "Goblin", 3000, false));
        itemHash.put(4001, new Item("Syria", "Ancient Syria", "SyriaTalk", 4001, false));
    }

    public int getItemId(){
        return knownId;
    }

    public void use(){
        switch (knownId) {
            case 1:
                Game.torchTime = 10000;
                Game.hasTorch = true;
                break;
            case 2: 
                if (Game.currentFloor.getName().equals("floor1_1.txt") && Game.playerX == 1 && Game.playerY == 2) {
                    Tile theTile = Game.currentFloor.searchForTile(1, 2);
                    theTile.getWalls()[3].setSolid(false);
                    Game.currentFloor.searchForTile(2, 2);
                    theTile.getWalls()[2].setSolid(false);
                    Inventory.removeItem(2);
                    Game.actionScreen.reset();
                    Game.actionScreen.updateInfo("You unlock the door, which guards a hallway. What could it lead to?");
                }
                break;
            case 6: 
                if (Game.currentFloor.getName().equals("floor1_1.txt") && Game.playerX == 2 && Game.playerY == 2) {
                    Tile theTile = Game.currentFloor.searchForTile(2, 2);
                    theTile.getWalls()[3].setSolid(false);
                    theTile = Game.currentFloor.searchForTile(3, 2);
                    theTile.getWalls()[2].setSolid(false);
                    Inventory.removeItem(6);
                    Game.actionScreen.reset();
                    Game.actionScreen.updateInfo("You unlock the door, which guards an ominous-looking room.");
                }
                break;
            case 7:
                Game.actionScreen.breakWallsCompass();
                break;
            case 9: 
                if (Game.currentFloor.getName().equals("floor2.txt") && Game.playerX == 1 && Game.playerY == 1) {
                    Tile theTile = Game.currentFloor.searchForTile(1, 1);
                    theTile.getWalls()[1].setSolid(false);
                    theTile = Game.currentFloor.searchForTile(1, 0);
                    theTile.getWalls()[0].setSolid(false);
                    Inventory.removeItem(9);
                    Game.actionScreen.reset();
                    Game.actionScreen.updateInfo("You unlock the door, which guards a small, nearly-empty room.");
                }
                break;
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

    public boolean isObtainable(){
        return obtainable;
    }

    public void changeName(String n){
        name = n;
    }

}