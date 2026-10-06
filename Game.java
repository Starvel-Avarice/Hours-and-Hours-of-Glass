import java.awt.event.*;

public class Game {
    public static void main(String[] args) {
        Wall testWall = new Wall();
        Wall testDoor = new Wall("A locked door with a green gem embeded in it", true);
        int[] Walls = new int[] {0, 0, 2, 0};

        Item Torch = new Item("torch", "Lights the way", "torchUse");
        Item[] lights = {Torch, Torch};

        Tile test = new Tile(lights, Walls, 0);
        Tile test1 = new Tile(null, Walls, 1);
        String[] looked= test.look();
        System.out.println(test.inspectWall(1));
        for(String s:looked){
        System.out.println(s);}

    }
    public class EventHandler implements ActionListener {
        public EventHandler(){

        }
        public void actionPerformed(ActionEvent event){
        
        }
    }
}
