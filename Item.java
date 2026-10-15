import java.util.HashMap;

public class Item {
    private String lore;
    private String name;
    private String action;
    private int knownId;
    private static HashMap<Integer, Item> itemHash= new HashMap<>();

    public Item(String n, String l, String u, int id){
        lore=l;
        name=n;
        action=u;
        knownId =id;
    }

    public Item(int id){
       if(itemHash.containsKey(id)){
        Item i = itemHash.get(id);
        lore = i.getLore();
        name = i.getName();
        action = i.use();
        knownId = id;
       }else{
        System.out.println("Unkown Item ID:"+id);
       }
    }

    public static void genItemHash(){
        itemHash.put(0, new Item("Compass","Tells you where North is", "NONE",0));
        itemHash.put(1, new Item("Torch","Lights up the way", "TorchUse",1));
    }

    public int getItemId(){
        return knownId;
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