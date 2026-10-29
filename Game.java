import java.awt.event.*;
import java.util.Scanner;

public class Game {
    public static int time = 1000;
    public static int torchTime = 100;
    public static int playerX = 0;
    public static int playerY= 0;

    public static void main(String[] args) {
        boolean newTile = true;
        Item.genItemHash();
        Wall.genWallHash();
        boolean hasTorch = false;
        Floor demoFloor = new Floor("demo.txt");

        for(int i =0; i<4;i++){System.out.println(demoFloor.searchForTile(0,0).look(playerX, playerY, i, demoFloor));}
        Scanner playerInputScanner = new Scanner(System.in);

        String playerInput = "";

        System.out.println("WELCOME TO THE DEMO!");
        System.out.print("Enter your player name: ");
    
    
    }


    public class EventHandler implements ActionListener {
        public EventHandler(){

        }
        public void actionPerformed(ActionEvent event){
        
        }

    }}
