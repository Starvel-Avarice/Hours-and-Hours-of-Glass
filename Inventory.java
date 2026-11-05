import java.util.ArrayList;
public class Inventory {
    private static ArrayList<Item> inv = new ArrayList<>();

    public static ArrayList<Item> getItems(){
        return inv;
    }

    public static void addItem(Item i){
        inv.add(i);
    }

    public static void removeItem(int i){
        inv.remove(i);
    }
    
    public static int getItemIndex(Item i){
        for(int n=0; n<inv.size()-1; n++){
            if(inv.get(n).equals(i)){
                return n;
            }
        }
        return -1;
    }
}
