import java.util.HashMap;

public class Item {
    private String lore;
    private String name;
    private String action;
    private boolean obtainable;
    private static HashMap<Integer, Item> itemHash= new HashMap<>();

    public Item(String n, String l, String u, boolean o){
        lore=l;
        name=n;
        action=u;
        obtainable = o;
    }

    public Item(int id){
       if(itemHash.containsKey(id)){
        Item i = itemHash.get(id);
        lore = i.getLore();
        name = i.getName();
        action = i.use();
       }else{
        System.out.println("Unkown Item ID:"+id);
       }
    }

    public static void genItemHash(){
        // itemHash.put(0, new Item("Compass","Tells you where North is", "NONE"));
        itemHash.put(1, new Item("Torch", "Lights up the way", "TorchUse", true));
        itemHash.put(2, new Item("Blue Key", "A key to a door.", "KeyUse", true));
        itemHash.put(4, new Item("The Ring", "Grants passageway to the Hinge.", "RingUse", true));
        itemHash.put(5, new Item("The Pink Puzzle", "The Pink Puzzle", "PinkPuzzleUse", true));
        itemHash.put(666, new Item("Death", "Kills the player", "InstantDeath", false));
        itemHash.put(1000, new Item("Glass Shard Holder", "Stores glass shards (back-end)", "ShardHolderUse", false));
        itemHash.put(3000, new Item("Goblin", "Combat with the goblin.", "Goblin", false));
    }

    public String use(){
        return action;
    }

    public String getLore(){
        return lore;
    }

    public String getName(){
        return name;
    }

}