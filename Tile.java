import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tile {
    private int[] items;
    private int[] walls;
    private int ladder;
    private int[] coords;
    private String lookText;
    private String fullText;

    public Tile(int[] wI, int l, int[] i, int[] cs, String lt, String ft){
        items = i;
        ladder = l;
        walls = wI;
        coords = cs;
        lookText = lt;
        fullText = ft;
    }

    public Tile(Boolean[] w){
    items = null;
    ladder = 0;
    }       

    public Tile() {

    }

    public String[] look(){

       ArrayList<String> interests = new ArrayList<String>();

        if(items!=null){
        for(int i=0;i<(items.length);i++){
            System.out.println(items);
            interests.add(Integer.toString(items[i]));
        }
        for(int j=0;j<4;j++){
            interests.add(Integer.toString(walls[j]));
        }
        }else{
            for(int l=0; l<4; l++){
            interests.add(Integer.toString(walls[l]));;
            }
        }
        String[] interestsArray = new String[interests.size()];
        for(int i=0; i<interests.size(); i++){
            interestsArray[i]=interests.get(i);
        }
        return interestsArray;
    }
    
    public String inspectWall(int direction){
        return Integer.toString(walls[direction]);
    }


}