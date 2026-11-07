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
        for (int j = 0; j < inv.size(); j++) {
            if (inv.get(j).getItemId() == i) {
                inv.remove(j);
                System.out.println("WORKING!");
            }
        }
    }

}
