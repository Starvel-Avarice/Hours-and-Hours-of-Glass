import java.awt.event.*;
import java.util.Scanner;

public class Game {
    public static int time = 1000;
    public static int torchTime = 10;
    public static int playerX = -1;
    public static int playerY= -3;

    public static void main(String[] args) {
        boolean newTile = true;
        Item.genItemHash();
        Wall.genWallHash();
        boolean hasTorch = false;
        Floor demoFloor = new Floor("demo.txt");

        System.out.println(demoFloor.searchForTile(0,0).look(playerX, playerX,0, demoFloor));
        Scanner playerInputScanner = new Scanner(System.in);

        String playerInput = "";

        System.out.println("WELCOME TO THE DEMO!");
        System.out.print("Enter your player name: ");
        playerInput = playerInputScanner.nextLine();   // read text input

        // while (!playerInput.equals("QUIT")) {
        //     Tile selectedTile = demoFloor.searchForTile(playerX, playerY);

        //     if (newTile) {
        //         newTile = false;

        //         System.out.println();
        //         System.out.println("Current coordinates: (" + Integer.toString(playerX) + ", " + Integer.toString(playerY) + ")");

        //         System.out.println();

        //         if (!selectedTile.isDark() || hasTorch) {
        //             System.out.println(selectedTile.getFullText());
        //         } else {
        //             System.out.println("You can't see anything.");
        //         }

        //         if (!hasTorch) {
        //             if (look(selectedTile, playerX, playerY, "N", demoFloor, false, false)) {
        //                 System.out.print("North: ");
        //                 look(selectedTile, playerX, playerY, "N", demoFloor, false, true);
        //             } if (look(selectedTile, playerX,  playerY, "S", demoFloor, false, false)) {
        //                 System.out.print("South: ");
        //                 look(selectedTile,  playerX,  playerY, "S", demoFloor, false, true);
        //             } if (look(selectedTile,  playerX, playerY, "W", demoFloor, false, false)) {
        //                 System.out.print("West: ");
        //                 look(selectedTile,  playerX, playerY, "W", demoFloor, false, true);
        //             } if (look(selectedTile,  playerX, playerY, "E", demoFloor, false, false)) {
        //                 System.out.print("East: ");
        //                 look(selectedTile,  playerX, playerY, "E", demoFloor, false, true);
        //             }
        //         }
        //     }
        //     System.out.println();
        //     System.out.print("Type N to go North, W to go West, S to go South and E to go East. ");
        //     System.out.print("Type LN to look North, LW to look West, LS to look South, and LE to look East. ");
        //     playerInput = playerInputScanner.nextLine();
            
        //     int wallToCheck = -1;
        //     if (playerInput.length() == 1) {
        //         if (playerInput.equals("N")) {
        //             wallToCheck = 0;
        //         } else if (playerInput.equals("S")) {
        //             wallToCheck = 1;
        //         } else if (playerInput.equals("W")) {
        //             wallToCheck = 2;
        //         } else if (playerInput.equals("E")) {
        //             wallToCheck = 3;
        //         } else {
        //             wallToCheck = -1;
        //         }
        //     } else if (playerInput.length() == 2) {
        //         if (playerInput.equals("LN")) {
        //             look(selectedTile, playerX, playerY, "N", demoFloor, hasTorch, true);
        //         } else if (playerInput.equals("LS")) {
        //             look(selectedTile, playerX, playerY, "S", demoFloor, hasTorch, true);
        //         } else if (playerInput.equals("LW")) {
        //             look(selectedTile, playerX, playerY, "W", demoFloor, hasTorch, true);
        //         } else if (playerInput.equals("LE")) {
        //             look(selectedTile, playerX, playerY, "E", demoFloor, hasTorch, true);
        //         } else {
        //             wallToCheck = -1;
        //         }
        //     }

        //     if (wallToCheck != -1) {
        //         if (!selectedTile.getWall(wallToCheck).getSolid()) {
        //             newTile = true;
        //             if (wallToCheck == 0) {
        //                 y++;
        //             } else if (wallToCheck == 1) {
        //                 y--;
        //             } else if (wallToCheck == 2) {
        //                 x--;
        //             } else if (wallToCheck == 3) {
        //                 x++;
        //             }
        //         } else {
        //             System.out.println("There is a wall here.");
        //         }
        //     }
        // }
        // playerInputScanner.close();
         }


    public class EventHandler implements ActionListener {
        public EventHandler(){

        }
        public void actionPerformed(ActionEvent event){
        
        }

    }}
