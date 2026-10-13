import java.awt.event.*;

public class Game {
    public static void main(String[] args) {
        Item.genItemHash();
        Wall.genWallHash();


        Floor demoFloor = new Floor("demo.txt");
        for(String  s:demoFloor.listTiles()){
            System.out.println(s);
        }
    }

    public class EventHandler implements ActionListener {
        public EventHandler(){

        }
        public void actionPerformed(ActionEvent event){
        
        }
    }
}
