import java.awt.event.*;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Item.genItemHash();
        Wall.genWallHash();


        Floor demoFloor = new Floor("demo.txt");

        for(String  s:demoFloor.listTiles()){
            System.out.println(s);
        // Tile test = new Tile(lights, Walls, 0);
        // Tile test1 = new Tile(null, Walls, 1);
        // String[] looked= test.look();
        // System.out.println(test.inspectWall(1));
        // for(String s:looked){
        // System.out.println(s);} 

        int x = -1;
        int y = -3;

        boolean Light = false;
        
        Scanner playerInputScanner = new Scanner(System.in);

        String playerInput = "";

        System.out.println("WELCOME TO THE DEMO!");
        System.out.println("Enter your player name: ");
        playerInput = playerInputScanner.nextLine();   // read text input

        while (!playerInput.equals("QUIT")) {
            Tile selectedTile = demoFloor.searchForTile(x, y);

            System.out.println();
            System.out.println("Current coordinates: (" + Integer.toString(x) + ", " + Integer.toString(y) + ")");

            System.out.println();
            System.out.println(selectedTile.getFullText());

            System.out.println();
            System.out.print("Type N to go North, W to go West, S to go South and E to go East. ");
            playerInput = playerInputScanner.nextLine();
            
            int wallToCheck = -1;

            if (playerInput.equals("N")) {
                wallToCheck = 0;
            } else if (playerInput.equals("S")) {
                wallToCheck = 1;
            } else if (playerInput.equals("W")) {
                wallToCheck = 2;
            } else if (playerInput.equals("E")) {
                wallToCheck = 3;
            } else {
                wallToCheck = -1;
            }

            if (wallToCheck != -1) {
                if (!selectedTile.getWall(wallToCheck).getSolid()) {
                    System.out.println("Success!");
                    if (wallToCheck == 0) {
                        y++;
                    } else if (wallToCheck == 1) {
                        y--;
                    } else if (wallToCheck == 2) {
                        x--;
                    } else if (wallToCheck == 3) {
                        x++;
                    }
                } else {
                    System.out.println("There is a wall here.");
                }
            }
        }
        playerInputScanner.close(); }
    }

    public class EventHandler implements ActionListener {
        public EventHandler(){

        }
        public void actionPerformed(ActionEvent event){
        
        }
    }
}
