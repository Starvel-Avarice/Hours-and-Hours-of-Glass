import java.awt.event.*;

public class Game {
    public static void main(String[] args) {
        new Item().genItemHash();

            Item Compass = new Item(0);
            System.out.print(Compass.getLore());
    }
    public class EventHandler implements ActionListener {
        public EventHandler(){

        }
        public void actionPerformed(ActionEvent event){
        
        }
    }
}
