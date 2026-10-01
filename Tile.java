import java.util.ArrayList;

public class Tile {
    private  Item[] items;
    private  Wall[] walls;
    private  int ladder;

    public Tile(Item[] i, Wall[] wI, int l){
        items = i;
        walls = wI;
        ladder =l;
    }
    public String[] look(){

       ArrayList<String> interests = new ArrayList<String>();

        if(items!=null){
        for(int i=0;i<(items.length);i++){
            System.out.println(items);
            interests.add(items[i].getName());
        }
        for(int j=0;j<4;j++){
            interests.add(walls[j].getName());
        }
        }else{
            for(int l=0; l<4; l++){
            interests.add(walls[l].getName());
            }
        }
        String[] interestsArray = new String[interests.size()];
        for(int i=0; i<interests.size(); i++){
            interestsArray[i]=interests.get(i);
        }
        return interestsArray;
    }
    
    public String inspectWall(int direction){
        return walls[direction].inspect();
    }


}
