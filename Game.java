import java.awt.event.*;
import java.util.Currency;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Game {

    

    public static int time = 1000;
    public static int torchTime = 10;
    public static int playerX = -1;
    public static int playerY= -3;

    public static volatile Thread mainGame;
    private static TitleScreen titleScreen;
    private static ActionScreen actionScreen;
<<<<<<< HEAD


=======
    public static Floor currentFloor;
    
>>>>>>> 98adc942ffcf2ae5e642014ed091e8cecd55410f
    public static void main(String[] args) {
        
        mainGame = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // Perform task
                    Thread.sleep(5);    // changing to sleep(5) puts the thread in a sleep state and allows it to be woken up, printing the "Thread interrupted" message below
                }
            } catch (InterruptedException e) {
                System.out.println();
                // System.out.println("Thread interrupted.");

                // added the following code...not sure if this is what you wanted?  System.exit(0) kills the application.  But did you want to find a way to keep running the app, just not the Thread?
                System.out.println("The Turning has taken place. Game over!");
                System.exit(0);
            }
        });
        mainGame.start();
        runGame();
    }
    
    
    public static void runGame() {
        titleScreen = new TitleScreen();

        boolean newTile = true;
        Item.genItemHash();
        Wall.genWallHash();
        boolean hasTorch = false;
        Floor demoFloor = new Floor("demo.txt");
<<<<<<< HEAD

        // for(String  s:demoFloor.listTiles()){
        //     System.out.println(s); }
        // Tile test = new Tile(lights, Walls, 0);
        // Tile test1 = new Tile(null, Walls, 1);
        // String[] looked= test.look();
        // System.out.println(test.inspectWall(1));
        // for(String s:looked){
        // System.out.println(s);} 


        System.out.println(demoFloor.searchForTile(0,0).look(playerX, playerX,0, demoFloor));
=======
        currentFloor = demoFloor; 
      
        for(int i =0; i<4;i++){System.out.println(currentFloor.searchForTile(0,0).look(playerX, playerY, i, currentFloor));}
>>>>>>> 98adc942ffcf2ae5e642014ed091e8cecd55410f
        Scanner playerInputScanner = new Scanner(System.in);

        String playerInput = "";

        System.out.println("WELCOME TO THE DEMO!");
        System.out.print("Enter your player name: ");
        playerInput = playerInputScanner.nextLine();   // read text input
        Tick masterTimer = new Tick(10, true, mainGame);
      
        while (!playerInput.equals("QUIT") & masterTimer.getTime() != 0) {
            Tile selectedTile = currentFloor.searchForTile(playerX, playerY);

        //     if (newTile) {
        //         newTile = false;

                System.out.println();
                System.out.println("Current coordinates: (" + Integer.toString(playerX) + ", " + Integer.toString(playerY) + ")");

                System.out.println();


                if (!selectedTile.isDark() || hasTorch) {
                    System.out.println(selectedTile.getFullText());
                    System.out.println();
                } else {
                    System.out.println("You can't see anything.");
                    System.out.println();
                }

                if (true) {
<<<<<<< HEAD
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
=======
                    Tile lookTile = currentFloor.searchForTile(playerX,playerY);
                    if (lookTile.getWalls()[0].getSolid()) {
                        System.out.print("North: ");
                        lookTile.look(playerX, playerY, 0, currentFloor);
                    } if (lookTile.getWalls()[2].getSolid()) {
                        System.out.print("South: ");
                        lookTile.look(playerX, playerY, 2, currentFloor);
                    } if (lookTile.getWalls()[3].getSolid()) {
                        System.out.print("West: ");
                        lookTile.look(playerX, playerY, 3, currentFloor);
                    } if (lookTile.getWalls()[1].getSolid()) {
                        System.out.print("East: ");
                       lookTile.look(playerX, playerY, 1, currentFloor);
>>>>>>> 98adc942ffcf2ae5e642014ed091e8cecd55410f
                    }
                }
                if (x == 0 && y == -3) {
                    System.out.println("Light acquired!");
                    hasTorch = true;
                }
            }
            System.out.println();
            System.out.print("Type N to go North, W to go West, S to go South and E to go East. ");
            System.out.print("Type LN to look North, LW to look West, LS to look South, and LE to look East. ");}
            //playerInput = playerInputScanner.nextLine();
            
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
<<<<<<< HEAD
                    look(selectedTile, x, y, "N", demoFloor, hasTorch, true, true);
                } else if (playerInput.equals("LS")) {
                    look(selectedTile, x, y, "S", demoFloor, hasTorch, true, true);
                } else if (playerInput.equals("LW")) {
                    look(selectedTile, x, y, "W", demoFloor, hasTorch, true, true);
                } else if (playerInput.equals("LE")) {
                    look(selectedTile, x, y, "E", demoFloor, hasTorch, true, true);
=======
                    System.out.println(selectedTile.look(playerX, playerY, 0, currentFloor));
                } else if (playerInput.equals("LS")) {
                    System.out.println(selectedTile.look(playerX, playerY, 2, currentFloor));
                } else if (playerInput.equals("LW")) {
                   System.out.println(selectedTile.look(playerX, playerY, 3, currentFloor));
                } else if (playerInput.equals("LE")) {
                    System.out.println(selectedTile.look(playerX, playerY, 1, currentFloor));
>>>>>>> 98adc942ffcf2ae5e642014ed091e8cecd55410f
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

    public static boolean look(Tile myTile, int x, int y, String dir, Floor myFloor, boolean hasTorch, boolean printOrNot) {
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
            String command = event.getActionCommand();
            switch (command) {
                case "startGame":
                    titleScreen.closeFirstScreen();
                    actionScreen=new ActionScreen(titleScreen.getCon());
                    actionScreen.openMainScreen();
                    actionScreen.updateInfo("This is the main menu");
                    break;
                case "goMove":
                    actionScreen.dirCompass();
                    break;
                case "goLook":
                    actionScreen.lookCompass();
                    break;
                case "north":

                case "east":

                case "west":

                case "south":
                case "up":
                case "ahead":
                    actionScreen.reset();
                    actionScreen.updateInfo(currentFloor.searchForTile(playerX, playerX).look(playerX, playerY, 0, currentFloor));
                        break;
                case "left":
                    actionScreen.reset();
                    actionScreen.updateInfo(currentFloor.searchForTile(playerX, playerX).look(playerX, playerY, 2, currentFloor));
                     break;
                case "current":
                    actionScreen.reset();
                    currentFloor.searchForTile(playerX, playerX).getLookText();
                    break;
                case "right":
                    actionScreen.reset();
                    actionScreen.updateInfo(currentFloor.searchForTile(playerX, playerX).look(playerX, playerY, 1, currentFloor));
                     break;
                case "behind":
                     actionScreen.reset();
                     actionScreen.updateInfo(currentFloor.searchForTile(playerX, playerX).look(playerX, playerY,3, currentFloor));
                     break;
                default: 
                    break;
            }
        }
    }

}
