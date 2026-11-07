import java.awt.event.*;


public class Game {
    public static int time = 1000;
    public static int torchTime = 100;
    public static int playerX = 0;
    public static int playerY = 0;
    public static String currentScreen = "main";
    public static boolean hasTorch = false;

    public static volatile Thread mainGame;
    private static TitleScreen titleScreen;
    public static ActionScreen actionScreen;
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
        titleScreen = new TitleScreen();
        boolean newTile = true;
        Item.genItemHash();
        Wall.genWallHash();
        Floor demoFloor = new Floor("demo.txt");
        Floor floor1 = new Floor("floor1_1.txt");
        currentFloor = floor1; 
      
        Tick masterTimer = new Tick(1000, true, mainGame);
    }

    public static void displayText(boolean torch) {
        if (torch || !currentFloor.searchForTile(playerX, playerY).isDark()) {
            actionScreen.updateInfo(currentFloor.searchForTile(playerX, playerY).getFullText());
        } else {
            actionScreen.updateInfo("You can't see anything.");
        };
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
            Tile targetTile = currentFloor.searchForTile(playerX, playerY);
            if(currentScreen.equals("Inventory") & command.substring(0,4).equals("Item")){
                System.out.println(Inventory.getItems());
                Inventory.getItems().get(Integer.parseInt(command.substring(4))).use(); }
            if(currentScreen.equals("Interact") & command.substring(0,4).equals("Item")){
                Item targetItem = targetTile.getItems().get(Integer.parseInt(command.substring(4)));
                String itemName = targetItem.getName();
                System.out.println(itemName);
                if (targetItem.isObtainable()) {
                    System.out.println("WOO");
                    Inventory.addItem(targetTile.takeItem(itemName));
                }
                if (itemName.equals("Torch")) {
                    hasTorch = true;
                }
                actionScreen.reset();
            }
                
            switch (command) {
                case "startGame":
                    titleScreen.closeFirstScreen();
                    actionScreen=new ActionScreen(titleScreen.getCon());
                    actionScreen.openMainScreen();
                    actionScreen.updateInfo("This is the main menu");
                    break;
                case "goMove":
                    actionScreen.dirCompass();
                    // actionScreen.textboxPanel(true);
                    break;
                case "goLook":
                    actionScreen.lookCompass();
                    break;
                case "north":
                        actionScreen.reset();
                        movePlayer(0);
                        displayText(hasTorch);
                break;
                case "east":
                        actionScreen.reset();
                        movePlayer(3);
                        displayText(hasTorch);

                break;
                case "west":
                        actionScreen.reset();
                        movePlayer(2);
                        displayText(hasTorch);
                break;
                case "south":
                        actionScreen.reset();
                        movePlayer(1);
                        displayText(hasTorch);
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
                        actionScreen.updateInfo(currentFloor.searchForTile(playerX, playerY).look(playerX, playerY, 1, currentFloor));
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