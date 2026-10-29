import java.awt.event.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Game {
    public static int time = 1000;
    public static int torchTime = 100;
    public static int playerX = 0;
    public static int playerY= 0;

    public static volatile Thread mainGame;
    private static TitleScreen titleScreen;
    private static ActionScreen actionScreen;
    
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
      
        for(int i =0; i<4;i++){System.out.println(demoFloor.searchForTile(0,0).look(playerX, playerY, i, demoFloor));}
        Scanner playerInputScanner = new Scanner(System.in);

        String playerInput = "";

        System.out.println("WELCOME TO THE DEMO!");
        System.out.print("Enter your player name: ");
        playerInput = playerInputScanner.nextLine();   // read text input
        Tick masterTimer = new Tick(10, true, mainGame);
      
        while (!playerInput.equals("QUIT") & masterTimer.getTime() != 0) {
            Tile selectedTile = demoFloor.searchForTile(playerX, playerY);

            System.out.print("Time left: ");
            System.out.println(String.valueOf(masterTimer.getTime()));

            if (newTile) {
                newTile = false;

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
                    Tile lookTile = demoFloor.searchForTile(playerX,playerY);
                    if (lookTile.getWalls()[0].getSolid()) {
                        System.out.print("North: ");
                        lookTile.look(playerX, playerY, 0, demoFloor);
                    } if (lookTile.getWalls()[2].getSolid()) {
                        System.out.print("South: ");
                        lookTile.look(playerX, playerY, 2, demoFloor);
                    } if (lookTile.getWalls()[3].getSolid()) {
                        System.out.print("West: ");
                        lookTile.look(playerX, playerY, 3, demoFloor);
                    } if (lookTile.getWalls()[1].getSolid()) {
                        System.out.print("East: ");
                       lookTile.look(playerX, playerY, 1, demoFloor);
                    }
                }
                if (playerX == 0 && playerY == -3) {
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
                    System.out.println(selectedTile.look(playerX, playerY, 0, demoFloor));
                } else if (playerInput.equals("LS")) {
                    System.out.println(selectedTile.look(playerX, playerY, 2, demoFloor));
                } else if (playerInput.equals("LW")) {
                   System.out.println(selectedTile.look(playerX, playerY, 3, demoFloor));
                } else if (playerInput.equals("LE")) {
                    System.out.println(selectedTile.look(playerX, playerY, 1, demoFloor));
                } else {
                    wallToCheck = -1;
                }
            }
          
            if (wallToCheck != -1) {
                if (!selectedTile.getWall(wallToCheck).getSolid()) {
                    newTile = true;
                    if (wallToCheck == 0) {
                        playerY++;
                    } else if (wallToCheck == 1) {
                        playerY--;
                    } else if (wallToCheck == 2) {
                        playerX--;
                    } else if (wallToCheck == 3) {
                        playerX++;
                    }
                } else {
                    System.out.println("There is a wall here.");
                }
            }
        }
        playerInputScanner.close(); 
        System.out.println("TIME OUT!");
        System.exit(0);
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
                case "left":
                case "current":
                case "right":
                case "behind":
                    actionScreen.reset();
                    break;
                default: 
                    break;
            }
        }
    }

}
