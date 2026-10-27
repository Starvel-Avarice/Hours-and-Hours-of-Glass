import java.awt.event.*;
import java.util.Scanner;

public class Game {
    private static TitleScreen titleScreen;
    private static ActionScreen actionScreen;
    public static void main(String[] args) {
        boolean newTile = true;
        Item.genItemHash();
        Wall.genWallHash();

        boolean hasTorch = false;
        Floor demoFloor = new Floor("demo.txt");
        // for(String  s:demoFloor.listTiles()){
        //     System.out.println(s); }
        // Tile test = new Tile(lights, Walls, 0);
        // Tile test1 = new Tile(null, Walls, 1);
        // String[] looked= test.look();
        // System.out.println(test.inspectWall(1));
        // for(String s:looked){
        // System.out.println(s);} 

        int x = -1;
        int y = -3;
        
        Scanner playerInputScanner = new Scanner(System.in);

        String playerInput = "";

        System.out.println("WELCOME TO THE DEMO!");
        System.out.print("Enter your player name: ");
        playerInput = playerInputScanner.nextLine();   // read text input

        while (!playerInput.equals("QUIT")) {
            Tile selectedTile = searchForTile(x, y, demoFloor);

            if (newTile) {
                newTile = false;

                System.out.println();
                System.out.println("Current coordinates: (" + Integer.toString(x) + ", " + Integer.toString(y) + ")");

                System.out.println();

                if (!selectedTile.isDark() || hasTorch) {
                    System.out.println(selectedTile.getFullText());
                    System.out.println();
                } else {
                    System.out.println("You can't see anything.");
                    System.out.println();
                }

                if (true) {
                    if (look(selectedTile, x, y, "N", demoFloor, false, false, false)) {
                        System.out.print("North: ");
                        look(selectedTile, x, y, "N", demoFloor, false, true, false);
                    } if (look(selectedTile, x, y, "S", demoFloor, false, false, false)) {
                        System.out.print("South: ");
                        look(selectedTile, x, y, "S", demoFloor, false, true, false);
                    } if (look(selectedTile, x, y, "W", demoFloor, false, false, false)) {
                        System.out.print("West: ");
                        look(selectedTile, x, y, "W", demoFloor, false, true, false);
                    } if (look(selectedTile, x, y, "E", demoFloor, false, false, false)) {
                        System.out.print("East: ");
                        look(selectedTile, x, y, "E", demoFloor, false, true, false);
                    }
                }
                if (x == 0 && y == -3) {
                    System.out.println("Light acquired!");
                    hasTorch = true;
                }
            }
            System.out.println();
            System.out.print("Type N to go North, W to go West, S to go South and E to go East. ");
            System.out.print("Type LN to look North, LW to look West, LS to look South, and LE to look East. ");
            playerInput = playerInputScanner.nextLine();
            
            int wallToCheck = -1;
            if (playerInput.length() == 1) {
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
            } else if (playerInput.length() == 2) {
                if (playerInput.equals("LN")) {
                    look(selectedTile, x, y, "N", demoFloor, hasTorch, true, true);
                } else if (playerInput.equals("LS")) {
                    look(selectedTile, x, y, "S", demoFloor, hasTorch, true, true);
                } else if (playerInput.equals("LW")) {
                    look(selectedTile, x, y, "W", demoFloor, hasTorch, true, true);
                } else if (playerInput.equals("LE")) {
                    look(selectedTile, x, y, "E", demoFloor, hasTorch, true, true);
                } else {
                    wallToCheck = -1;
                }
            }
            if (wallToCheck != -1) {
                if (!selectedTile.getWall(wallToCheck).getSolid()) {
                    newTile = true;
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
        playerInputScanner.close(); 
    }
    public static Tile searchForTile(int x, int y, Floor myFloor) {
        for (Tile myTile : myFloor.getTiles()) {
            int[] coords = myTile.getCoords();
            if (coords[0] == x && coords[1] == y) {
                return myTile;
            }
        }
        return new Tile();
    }

    public static boolean look(Tile myTile, int x, int y, String dir, Floor myFloor, boolean hasTorch, boolean printOrNot, boolean detailed) {
        Wall[] myWalls = myTile.getWalls();
        int wallToCheck = -1;

        int lookX = x;
        int lookY = y;

        if (dir.equals("N")) {
            wallToCheck = 0;
            lookY++;
        } else if (dir.equals("S")) {
            wallToCheck = 1;
            lookY--;
        } else if (dir.equals("W")) {
            wallToCheck = 2;
            lookX--;
        } else if (dir.equals("E")) {
            wallToCheck = 3;
            lookX++;
        } else {
            wallToCheck = -1;
        }

        Tile targetTile = searchForTile(lookX, lookY, myFloor);
        if (!myWalls[wallToCheck].getSolid()) {
            if (hasTorch || !targetTile.isDark()) {
                if (printOrNot) {
                    if (detailed) {
                        System.out.println(targetTile.getLookText());
                    } else {
                        System.out.println("There is something here. Take a look!");
                    }
                }
                return true;
            } else {
                if (myTile.isDark()) {
                    if (printOrNot) {
                        System.out.println("Too dark to see...");
                    }
                    return false;
                } else {
                    if (printOrNot) {
                        System.out.println("There is something here, but it is too dark to see what it is...");
                    }
                    return true;
                }
            }
        } else {
            if (printOrNot) {
                System.out.println("Too dark to see...");
            }
            return false;
        }
    }

    public class EventHandler implements ActionListener {
        public EventHandler(){

        }
        public void actionPerformed(ActionEvent event){
            String command = event.getActionCommand();
            switch (command) {
                case "startGame":
                    titleScreen.closeFirstScreen();
                    actionScreen=new ActionScreen(titleScreen.getCon());
                    actionScreen.openMainScreen();
                    actionScreen.updateInfo("Please tell me this works");
                    break;
            
                default:
                    break;
            }
        }
    }
}