import java.awt.event.*;
import java.util.Currency;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Game {
    public static int time = 1000;
    public static int torchTime = 100;
    public static int playerX = -1;
    public static int playerY= -3;
    public static String currentScreen;

    public static volatile Thread mainGame;
    private static TitleScreen titleScreen;
    private static ActionScreen actionScreen;
    public static Floor currentFloor;
    
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
                System.out.println("Game over!");
                System.exit(0);
            }
        });
        mainGame.start();
        runGame();
    }
    
    
    public static void runGame() {
        Item.genItemHash();
        Wall.genWallHash();
        titleScreen = new TitleScreen();
        boolean newTile = true;
        boolean hasTorch = false;
        Floor demoFloor = new Floor("demo.txt");
        Floor floor1 = new Floor("floor1_1.txt");
        currentFloor = demoFloor; 
      
        Tick masterTimer = new Tick(45, true, mainGame);
        Inventory.addItem(new Item(1));
    }

    public void movePlayer(int dir){
        if(dir==0){playerY++;}
        if(dir==3){playerX++;}
        if(dir==1){playerY--;}
        if(dir==2){playerX--;}
    }

    public class EventHandler implements ActionListener {
        public EventHandler(){}
        public void actionPerformed(ActionEvent event){
            String command = event.getActionCommand();
            if(currentScreen.equals("Inventory")){
                if(command.equals("back")){actionScreen.reset();}else{
                Inventory.getItems().get(Integer.parseInt(command.substring(4))).use();
                Inventory.removeItem(Integer.parseInt(command.substring(4)));
                actionScreen.reset();
            }
            }
            System.out.println(command);

            switch (command) {
                
                case "startGame":
                    titleScreen.closeFirstScreen();
                    actionScreen=new ActionScreen(titleScreen.getCon());
                    actionScreen.openMainScreen();
                    actionScreen.updateInfo("This is the main menu");
                    break;
                case "goMove":
                    actionScreen.dirCompass();
                    //actionScreen.textboxPanel(true);
                    break;
                case "goLook":
                    actionScreen.lookCompass();
                    break;
                case "north":
                        actionScreen.reset();
                        movePlayer(0);
                        actionScreen.updateInfo("X:"+playerX+"Y:"+playerY);
                break;
                case "east":
                        actionScreen.reset();
                        movePlayer(3);
                        actionScreen.updateInfo("X:"+playerX+"Y:"+playerY);

                break;
                case "west":
                        actionScreen.reset();
                        movePlayer(2);
                        actionScreen.updateInfo("X:"+playerX+"Y:"+playerY);
                break;
                case "south":
                        actionScreen.reset();
                        movePlayer(1);
                        actionScreen.updateInfo("X:"+playerX+"Y:"+playerY);
                break;
                case "up":
                break;
                case "ahead":
                    if(currentScreen.equals("look")){
                        actionScreen.reset();
                        actionScreen.updateInfo(currentFloor.searchForTile(playerX, playerY).look(playerX, playerY, 0, currentFloor));
                     }
                    break;
                case "left":                   
                    if(currentScreen.equals("look")){
                        actionScreen.reset();
                        actionScreen.updateInfo(currentFloor.searchForTile(playerX, playerY).look(playerX, playerY, 2, currentFloor));
                    }
                    break;
                case "current":       
                    if(currentScreen.equals("look")){
                        actionScreen.reset();
                        actionScreen.updateInfo(currentFloor.searchForTile(playerX, playerY).getLookText());
                    }
                    break;
                case "right":
                    if(currentScreen.equals("look")){
                        actionScreen.reset();
                        actionScreen.updateInfo(currentFloor.searchForTile(playerX, playerY).look(playerX, playerY, 3, currentFloor));
                    }
                    break;
                case "behind":
                    if(currentScreen.equals("look")){
                        actionScreen.reset();
                        actionScreen.updateInfo(currentFloor.searchForTile(playerX, playerX).look(playerX, playerY, 1, currentFloor));
                    }
                    break;
                case "goInv":
                    actionScreen.inventory();
                    break;
                case "goInter":
                    actionScreen.interact();
                    break;
                case "back":
                    actionScreen.reset();
                    break;
                default: 
                    break;
            }
        }
    }

}