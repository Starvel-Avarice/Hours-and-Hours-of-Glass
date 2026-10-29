import java.util.HashMap;
import java.util.Timer;

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
        //action = i.use();
        knownId = id;
       }else{
        System.out.println("Unkown Item ID:"+id);
       }
    }

    public static void genItemHash(){
        itemHash.put(0, new Item("Compass","Tells you where North is", "NONE",0));
        itemHash.put(1, new Item("Torch","Lights up the way", "TorchUse",1));
        itemHash.put(2, new Item("Key","Might open a door", "Key1Use",2));
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