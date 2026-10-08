import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        int[] Walls = new int[] {0, 0, 2, 0};

        Item Torch = new Item("torch", "Lights the way", "torchUse");
        Item[] lights = {Torch, Torch};

        // Tile test = new Tile(lights, Walls, 0);
        // Tile test1 = new Tile(null, Walls, 1);
        // String[] looked= test.look();
        // System.out.println(test.inspectWall(1));
        // for(String s:looked){
        // System.out.println(s);}
        
        Floor demoFloor = new Floor();
        String fileName = "demo.txt";

        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNextLine()) {
                String myLine = sc.nextLine();
                String[] splits = myLine.split("\t");

                String[] walls = splits[0].split(",");
                int[] wallInts = stringArrayToIntArray(walls);
                
                int upOrDown = Integer.parseInt(splits[1]);

                String[] items = splits[2].split(",");
                int[] itemIds = stringArrayToIntArray(items);

                String[] coords = splits[3].split(",");
                int[] coordInts = stringArrayToIntArray(coords);

                String lookText = splits[4];
                String fullText = splits[4];

                demoFloor.addTile(new Tile(wallInts, upOrDown, itemIds, coordInts, lookText, fullText));

            }
                sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static int[] stringArrayToIntArray(String[] oldArray) {
        int[] newArray = new int[oldArray.length];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = Integer.parseInt(oldArray[i]);
        }
        return newArray;
    }

    public class EventHandler implements ActionListener {
        public EventHandler(){

        }
        public void actionPerformed(ActionEvent event){
        
        }
    }
}
