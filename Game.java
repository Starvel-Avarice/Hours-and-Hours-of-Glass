import java.awt.Font;
import java.awt.event.*;

public class Game {
    public static int time = 1000;
    public static int torchTime = 100;
    public static int playerX = 0;
    public static int playerY = 0;
    public static String currentScreen = "main";
    public static int floor = 1;
    public static boolean hasTorch = false;
    public static int glassShards = 0;
    public static String interruption = "";
    public static boolean goblinEncountered = false;

    public static volatile Thread mainGame;
    private static TitleScreen titleScreen;
    public static ActionScreen actionScreen;
    public static Floor currentFloor;

    public static Floor demoFloor = new Floor("demo.txt");
    public static Floor floor1 = new Floor("floor1_1.txt");
    public static Floor floor2 = new Floor("floor2.txt");
    
    public static void main(String[] args) {
        
        mainGame = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // Perform task
                    Thread.sleep(5);    // changing to sleep(5) puts the thread in a sleep state and allows it to be woken up, printing the "Thread interrupted" message below
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
                if (interruption.equals("endGame")) {
                    actionScreen.togglePanels(false);
                    // added the following code...not sure if this is what you wanted?  System.exit(0) kills the application.  But did you want to find a way to keep running the app, just not the Thread?
                    actionScreen.updateInfo("You gasp as the entire structure starts to rotate, throwing you violently around. Unfortunately, you hit your head on a wall, dying upon impact.");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        System.out.println("Failed");
                    }
                    actionScreen.updateInfo("GAME OVER!");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        System.out.println("Failed");
                    }
                    System.exit(0);
                } else if (interruption.equals("goblinEncounter")) {
                    actionScreen.togglePanels(false);
                    actionScreen.updateInfo("You enter an ominous-looking dark room, and you hear a gate close behind you, trapping you inside. At the center of the room, you find a massive, snarling goblin!");
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException ex) {
                        System.out.println("Failed");
                    }
                    if (glassShards >= 2) {
                        actionScreen.updateInfo("Luckily, you picked up some glass shards! Your first throw misses, but your second throw connects, impaling the goblin on impact.");
                    } else if (Game.glassShards == 1) {
                        actionScreen.updateInfo("Luckily, you picked up a glass shard! You try throwing it, but unfortunately it misses, and the goblin is not happy. The last thing you see before death is the goblin lurching towards you.");
                    } else {
                        actionScreen.updateInfo("As soon as he makes eye contact with you, he lunges towards you, killing you.");
                    }
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException ex) {
                        System.out.println("Failed");
                    }
                    if (glassShards < 2) {
                        actionScreen.updateInfo("GAME OVER!");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            System.out.println("Failed");
                        }
                        System.exit(0);
                    } else {
                        actionScreen.togglePanels(true);
                        actionScreen.updateInfo("Congratulations: you have defeated the goblin!");
                    }
                } else if (interruption.equals("torchBurnedOut")) {
                    actionScreen.reset();
                    actionScreen.updateInfo("Your torch burned out!");
                } else if (interruption.equals("Death")) {
                    actionScreen.togglePanels(false);
                    // added the following code...not sure if this is what you wanted?  System.exit(0) kills the application.  But did you want to find a way to keep running the app, just not the Thread?
                    actionScreen.updateInfo("For one reason or another, you died! This text will be developed further once this game is more fleshed out.");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        System.out.println("Failed");
                    }
                    actionScreen.updateInfo("GAME OVER!");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        System.out.println("Failed");
                    }
                    System.exit(0);
                }
                
            }
        });
        mainGame.start();
        runGame();
    }
    
    
    public static void runGame() {
        titleScreen = new TitleScreen();
        Item.genItemHash();
        Wall.genWallHash();
        currentFloor = floor1; 
    }

    public static void displayText(boolean torch) {
        if (torch || !currentFloor.searchForTile(playerX, playerY).isDark()) {
            actionScreen.updateInfo(currentFloor.searchForTile(playerX, playerY).getFullText());
        } else {
            actionScreen.updateInfo("You can't see anything.");
        }
    }

    public void movePlayer(int dir){
        if(dir==0){playerY++;}
        if(dir==3){playerX++;}
        if(dir==1){playerY--;}
        if(dir==2){playerX--;}

        if (currentFloor.searchForTile(playerX, playerY).hasItem("Death")) {
            interruption = "Death";
            mainGame.interrupt();
        }
    }

    public class EventHandler implements ActionListener {
        public EventHandler(){}
        public void actionPerformed(ActionEvent event){
            String command = event.getActionCommand();
            Tile targetTile = currentFloor.searchForTile(playerX, playerY);
            if(currentScreen.equals("Inventory") && command.substring(0,4).equals("Item")){
                System.out.println(Inventory.getItems());
                Inventory.getItems().get(Integer.parseInt(command.substring(4))).use(); }
            if(currentScreen.equals("Interact") && command.substring(0,4).equals("Item")){
                Item targetItem = targetTile.getItems().get(Integer.parseInt(command.substring(4)));
                String itemName = targetItem.getName();
                System.out.println(itemName);
                if (targetItem.isObtainable()) {
                    System.out.println("WOO");
                    if (itemName.equals("Glass Shards")) {
                        if (glassShards == 0) {
                            Inventory.addItem(targetTile.takeItem(itemName));
                        }
                        Item glassShardsItem = Inventory.findItem(1000);
                        glassShards++;
                        glassShardsItem.changeName("Glass Shards: " + String.valueOf(glassShards));
                        actionScreen.reset();
                        actionScreen.updateInfo("Glass Shard(s) acquired!");
                    } else if (itemName.equals("Chest")) {
                        if (glassShards == 0) {
                            Inventory.addItem(new Item(1000));
                        }
                        Item glassShardsItem = Inventory.findItem(1000);
                        glassShards = glassShards + 5;
                        glassShardsItem.changeName("Glass Shards: " + String.valueOf(glassShards));
                        actionScreen.reset();
                        actionScreen.updateInfo("5 Glass Shards acquired!");
                        targetTile.takeItem(itemName);
                    } else {
                        Inventory.addItem(targetTile.takeItem(itemName));
                        actionScreen.reset();
                        actionScreen.updateInfo(itemName + " acquired!");
                    }
                }
                if (itemName.equals("Torch")) {
                    hasTorch = true;
                }
                if (itemName.equals("Burning Torch")) {
                    hasTorch = true;
                    Tick torchTick = new Tick(120, false, mainGame, "torch");
                    Inventory.addItem(new Item(8));
                    actionScreen.reset();
                    actionScreen.updateInfo("Burning Torch acquired!");
                }
                if (itemName.equals("The Pink Puzzle")) {
                    actionScreen.textboxPanel(true);
                }
                if (itemName.equals("Syria")) {
                    actionScreen.reset();
                    actionScreen.updateInfo("\"Thank goodness! Another soul!\" you exclaim. \"Hello traveler. My name is Syria,\" the old woman explains. \"I'm afraid I'm the only one left down here... thanks to that stupid curse. The others all got Turned.\" She wouldn't explain further what that meant, shuddering at the very thought.");
                    actionScreen.infoText.setFont(new Font("Papyrus",Font.PLAIN, 20));
                }
                if (itemName.equals("Shop")) {
                    actionScreen.reset();
                    actionScreen.updateInfo("Coming soon!");
                }
            }
                
            switch (command) {
                case "startGame":
                    titleScreen.closeFirstScreen();
                    actionScreen=new ActionScreen(titleScreen.getCon());
                    actionScreen.openMainScreen();
                    actionScreen.updateInfo("You wake up at the bottom of a very deep pit. Glancing up, you can see what almost looks like glass at the top of this impossibly-high chasm. In a flash, you remember falling into this place: tripping on something, and tumbling into this abyss. How are you alive?");
                    Tick masterTimer = new Tick(1000, true, mainGame, "Master");
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
                        checkForInterruption();
                break;
                case "east":
                        actionScreen.reset();
                        movePlayer(3);
                        displayText(hasTorch);
                        checkForInterruption();

                break;
                case "west":
                        actionScreen.reset();
                        movePlayer(2);
                        displayText(hasTorch);
                        checkForInterruption();
                break;
                case "south":
                        actionScreen.reset();
                        movePlayer(1);
                        displayText(hasTorch);
                        checkForInterruption();
                break;
                case "upXX":
                    if (floor == 1) {
                        currentFloor = floor2;
                        floor = 2;
                        actionScreen.reset();
                        Inventory.removeItem(1);
                        if (Inventory.findItem(8) == null) {
                            hasTorch = false;
                        }
                        actionScreen.updateInfo("For some reason, your torch doesn't follow you, like it is tied to something. You hope you find another torch, and carry on. This room seems to lead to more of the cave, and a lantern hanging from the ceiling lights your way.");
                    } else {
                        actionScreen.updateInfo("Congrats! You've reached the end of the demo.");
                    }
                    break;
                case "down":
                    System.out.println("WOO");
                    floor = 1;
                    currentFloor = floor1;
                    actionScreen.reset();
                    displayText(hasTorch);
                    break;
                case "ahead":
                    if(currentScreen.equals("look")){
                        actionScreen.reset();
                        actionScreen.updateInfo(targetTile.look(playerX, playerY, 0, currentFloor));
                    }
                    break;
                case "left":                   
                    if(currentScreen.equals("look")){
                        actionScreen.reset();
                        actionScreen.updateInfo(targetTile.look(playerX, playerY, 2, currentFloor));
                    }
                    break;
                case "current":       
                    if(currentScreen.equals("look")){
                        actionScreen.reset();
                        Game.displayText(hasTorch);
                    }
                    break;
                case "right":
                    if(currentScreen.equals("look")){
                        actionScreen.reset();
                        actionScreen.updateInfo(targetTile.look(playerX, playerY, 3, currentFloor));
                    }
                    break;
                case "behind":
                    if(currentScreen.equals("look")){
                        actionScreen.reset();
                        actionScreen.updateInfo(targetTile.look(playerX, playerY, 1, currentFloor));
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
                case "breakNorth":
                    if (targetTile.getWalls()[0].getInspect().equals("A breakable wall")) {
                        targetTile.getWalls()[0].setSolid(false);
                        currentFloor.searchForTile(playerX, playerY + 1).getWalls()[1].setSolid(false);
                        actionScreen.reset();
                        actionScreen.updateInfo("Wall broken!");
                    } else {
                        actionScreen.reset();
                        actionScreen.updateInfo("This wall can't be broken.");
                    }
                    break;
                case "breakSouth":
                    if (targetTile.getWalls()[1].getInspect().equals("A breakable wall")) {
                        targetTile.getWalls()[1].setSolid(false);
                        currentFloor.searchForTile(playerX, playerY - 1).getWalls()[0].setSolid(false);
                        actionScreen.reset();
                        actionScreen.updateInfo("Wall broken!");
                    } else {
                        actionScreen.reset();
                        actionScreen.updateInfo("This wall can't be broken.");
                    }
                    break;
                case "breakWest":
                    if (targetTile.getWalls()[2].getInspect().equals("A breakable wall")) {
                        targetTile.getWalls()[2].setSolid(false);
                        currentFloor.searchForTile(playerX - 1, playerY).getWalls()[3].setSolid(false);
                        actionScreen.reset();
                        actionScreen.updateInfo("Wall broken!");
                    } else {
                        actionScreen.reset();
                        actionScreen.updateInfo("This wall can't be broken.");
                    }
                    break;
                case "breakEast":
                    if (targetTile.getWalls()[3].getInspect().equals("A breakable wall")) {
                        targetTile.getWalls()[3].setSolid(false);
                        currentFloor.searchForTile(playerX + 1, playerY).getWalls()[2].setSolid(false);
                        actionScreen.reset();
                        actionScreen.updateInfo("Wall broken!");
                    } else {
                        actionScreen.reset();
                        actionScreen.updateInfo("This wall can't be broken.");
                    }
                    break;
                default: 
                    break;
            }
        }
    }

    public void checkForInterruption() {
        if (playerX == 1 && playerY == -2 && currentFloor.getName().equals("floor1_1.txt")) {
            if (!goblinEncountered) {
                interruption = "goblinEncounter";
                mainGame.interrupt();
            }
        }
    }

}