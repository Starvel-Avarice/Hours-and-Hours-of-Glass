public class Item{
    private String lore;
    private String name;
    private String action;
    public Item(String n, String l, String u){
        lore=l;
        name=n;
        action=u;
    }

    public String use(){
        return this.action;
    }
    public String inspect(){
        return name+": " +lore;
    }
    public String look(){
        return name;
    }

}