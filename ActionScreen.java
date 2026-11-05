import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import java.awt.*;

import java.util.Timer;
import java.util.TimerTask;

public class ActionScreen {
    private JPanel infoPanel;
    private JTextArea infoText;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Container grabber;
    private Timer timer;
    private TimerTask timerTask;

    private String textPanelString = "";

    private JPanel inventoryPanel;
    private JPanel lookPanel;
    private JPanel movePanel;
    private JPanel interPanel;
    private JPanel textboxPanel;

    private JButton inventoryButton;
    private JButton lookButton;
    private JButton moveButton;
    private JButton interButton;

    private JPanel dirCompassGrid;
    private JPanel lookCompassGrid;

    private JButton north;
    private JButton east;
    private JButton south;
    private JButton west;
    private JButton upDown;

    private JPanel invList;
    private JPanel intList;
    private JButton back;

    
    private JTextField enterTextField;
    private JTextArea enterTextArea;

    private Game game = new Game();
    Game.EventHandler choiceHandler = game.new EventHandler();
    Game.EventHandler compassHandler = game.new EventHandler();
    Game.EventHandler textboxHandler = game.new EventHandler();

    private String beige = "#f6d7b0";
    private String darkGrey = "#4a4540";
    private String darkBeige = "#c8ab92";
    private String sand = "#efb787";
    private String orangeSand = "#e8975d";
    
    private Font normalFont = new Font("Papyrus",Font.PLAIN,30);
    private Font mainBoxFont = new Font("Papyrus",Font.PLAIN,25);

    public ActionScreen(Container con){
        grabber=con;
        setupMain();
    }
    
    public void setupMain(){
        infoPanel = new JPanel();
        infoPanel.setBounds(screenSize.width/2-300,screenSize.height/2-200,600,300);
        infoPanel.setLayout(null);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#e8975d"),15));
        infoPanel.setBackground(Color.decode(sand));
        infoPanel.setVisible(false);

        infoText = new JTextArea();
        infoText.setBounds(screenSize.width/2-690,screenSize.height/2-425,570,270);
        infoText.setForeground(Color.decode(darkGrey));
        infoText.setFont(mainBoxFont);
        infoText.setEditable(false);
        infoText.setFocusable(false);
        infoText.setLineWrap(true);
        infoText.setWrapStyleWord(true);
        infoText.setOpaque(false);
 
        infoPanel.add(infoText);
        grabber.add(infoPanel,1);

        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BorderLayout());
        inventoryPanel.setBounds(screenSize.width/2-300, screenSize.height/2-285, 175, 75);
        inventoryPanel.setBackground(Color.decode(sand));
        inventoryPanel.setVisible(false);

        lookPanel = new JPanel();
        lookPanel.setLayout(new BorderLayout());
        lookPanel.setBounds(screenSize.width/2-88, screenSize.height/2+110, 175, 75);
        lookPanel.setBackground(Color.decode(sand));
        lookPanel.setVisible(false);
         
        movePanel = new JPanel();
        movePanel.setLayout(new BorderLayout());
        movePanel.setBounds(screenSize.width/2+125, screenSize.height/2+110, 175, 75);
        movePanel.setBackground(Color.decode(sand));
        movePanel.setVisible(false);

        interPanel = new JPanel();
        interPanel.setLayout(new BorderLayout());
        interPanel.setBounds(screenSize.width/2-300, screenSize.height/2+110, 175, 75);
        interPanel.setBackground(Color.decode(sand));
        interPanel.setVisible(false);

        inventoryButton = buttonBuilder("Inventory", sand, darkGrey, choiceHandler, "goInv");
        lookButton = buttonBuilder("Look", sand, darkGrey, choiceHandler, "goLook");
        moveButton = buttonBuilder("Move", sand, darkGrey, choiceHandler, "goMove");
        interButton = buttonBuilder("Interact", sand, darkGrey, choiceHandler, "goInter");

        inventoryPanel.add(inventoryButton);
        lookPanel.add(lookButton);
        movePanel.add(moveButton);
        interPanel.add(interButton);

        textboxPanel = new JPanel();
        textboxPanel.setLayout(new BorderLayout());
        textboxPanel.setBounds(screenSize.width/2-250, screenSize.height/2-150, 500, 200);
        textboxPanel.setBackground(Color.decode(sand));
        textboxPanel.setVisible(false);

        enterTextField = new JTextField();
        enterTextField.addActionListener(textboxHandler);
        enterTextField.setFont(normalFont);
        enterTextField.setHorizontalAlignment(JTextField.CENTER);
        enterTextField.setEditable(true);

        textboxPanel.add(enterTextField);

        grabber.add(inventoryPanel,1);
        grabber.add(lookPanel,1);
        grabber.add(movePanel,1);
        grabber.add(interPanel,1);
        grabber.add(textboxPanel,0);

        enterTextField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                textPanelString = enterTextField.getText();
                if (textPanelString.equals("12345")) {
                    textboxPanel(false);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                textPanelString = enterTextField.getText();
                if (textPanelString.equals("12345")) {
                    textboxPanel(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                textPanelString = enterTextField.getText();
                if (textPanelString.equals("12345")) {
                    textboxPanel(false);
                }
            }

        });
    }
    
    public void openMainScreen(){
        infoPanel.setVisible(true);
        inventoryPanel.setVisible(true);
        lookPanel.setVisible(true);
        movePanel.setVisible(true);
        interPanel.setVisible(true);
        grabber.revalidate();
        grabber.repaint();
    }
    
    public void updateInfo(String text){
        infoText.setText(text);
    }
    


    public void dirCompass(){
        Boolean[] moveOptions = Game.currentFloor.searchForTile(Game.playerX, Game.playerY).solidWalls();
        interPanel.setVisible(false);
        lookPanel.setVisible(false);
        movePanel.setVisible(false);
        Game.currentScreen ="move";
        updateInfo("What direction do you want to go?");
        for(Boolean t:moveOptions){System.out.println(t+"as "+Game.playerX+"-"+Game.playerY);}

        dirCompassGrid = new JPanel();
        dirCompassGrid.setBounds(screenSize.width*11/32,screenSize.height*37/60,450,250);
        dirCompassGrid.setBackground(Color.decode(beige));
        dirCompassGrid.setLayout(new GridLayout(3,3));
        dirCompassGrid.setVisible(true);
        dirCompassGrid.add(new JLabel());

        north = buttonBuilder("North", darkBeige, darkGrey, compassHandler, "");
        if(!moveOptions[0]){
            north.setBackground(Color.decode(sand));
            north.setActionCommand("north");
        } else {
            north.setEnabled(false);
        }
        dirCompassGrid.add(north);
        dirCompassGrid.add(new JLabel());

        west = buttonBuilder("West", darkBeige, darkGrey, compassHandler, "");
        if(!moveOptions[2]){
            west.setBackground(Color.decode(sand));
            west.setActionCommand("west");
        } else {
            west.setEnabled(false);
        }
        dirCompassGrid.add(west);
        
        upDown=buttonBuilder("", darkBeige, darkGrey, compassHandler, "");
        upDown.setVisible(false);
        if(Game.currentFloor.searchForTile(Game.playerX,Game.playerY).getLadder()!=0){
        upDown.setActionCommand("up");
        }
        dirCompassGrid.add(upDown);

        east = buttonBuilder("East", darkBeige, darkGrey, compassHandler, "");
        if(!moveOptions[3]){
            east.setActionCommand("east");
            east.setBackground(Color.decode(sand));
        } else { 
            east.setEnabled(false);
        }
        dirCompassGrid.add(east);
        dirCompassGrid.add(new JLabel());

        south = buttonBuilder("South", darkBeige, darkGrey, compassHandler, "");
        if(!moveOptions[1]){
            south.setActionCommand("south");
            south.setBackground(Color.decode(sand));
        } else {
            south.setEnabled(false);
        }
        
      
        dirCompassGrid.add(south);
        dirCompassGrid.add(new JLabel());
 
        backButton();
        grabber.add(dirCompassGrid,0);        
        
    }
    
    public void lookCompass(){
        interPanel.setVisible(false);
        lookPanel.setVisible(false);
        movePanel.setVisible(false);
        Game.currentScreen ="look";
        updateInfo("Where do you want to look?");

        lookCompassGrid = new JPanel();
        lookCompassGrid.setBounds(screenSize.width*11/32,screenSize.height*37/60,450,250);
        lookCompassGrid.setBackground(Color.decode(beige));
        lookCompassGrid.setLayout(new GridLayout(3,3));
        lookCompassGrid.setVisible(true);
        lookCompassGrid.add(new JLabel());

        north = buttonBuilder("North", sand, darkGrey, choiceHandler, "ahead");        
        lookCompassGrid.add(north);
        lookCompassGrid.add(new JLabel());

        west = buttonBuilder("West", sand, darkGrey, choiceHandler, "left");
        lookCompassGrid.add(west);

        upDown=buttonBuilder("Current", sand, darkGrey, choiceHandler, "current");
        lookCompassGrid.add(upDown);

        east = buttonBuilder("East", sand, darkGrey, choiceHandler, "right");
        lookCompassGrid.add(east);
        lookCompassGrid.add(new JLabel());

        south = buttonBuilder("South", sand, darkGrey, choiceHandler, "behind");
        lookCompassGrid.add(south);
        lookCompassGrid.add(new JLabel());
        
        backButton();
        grabber.add(lookCompassGrid,0);
    }

    public void inventory(){
        lookPanel.setVisible(false);
        movePanel.setVisible(false);
        interPanel.setVisible(false);
        
        invList = new JPanel();
        invList.setBounds(screenSize.width/2-285,screenSize.height/2-120,570,205);
        invList.setLayout(new GridLayout(2,5));
        invList.setBackground(Color.decode(sand));
        updateInfo("Select an item to use");
        invList.setVisible(true);

        backButton();
        grabber.add(invList,0);


    }

    public void interact(){
        lookPanel.setVisible(false);
        movePanel.setVisible(false);
        interPanel.setVisible(false);

        updateInfo("");
        intList= new JPanel();
        intList.setBounds(screenSize.width/2-285,screenSize.height/2-185,190,270);
        intList.setLayout(new GridLayout(4,1));
        intList.setBackground(Color.decode(sand));
        intList.setVisible(true);

        backButton();
        grabber.add(intList,0);
    }
    
    public void backButton(){

        back = buttonBuilder("Back", sand, darkGrey, choiceHandler, "back");
        inventoryButton.setVisible(false);
        inventoryPanel.add(back);
    }
    
    public void reset(){
        if (dirCompassGrid != null) {
            dirCompassGrid.setVisible(false);
            inventoryButton.setVisible(true);
            back.setVisible(false);
        }
        if (lookCompassGrid != null) {
            lookCompassGrid.setVisible(false);
            inventoryButton.setVisible(true);
            back.setVisible(false);
        }
        if (invList!=null){
            invList.setVisible(false);
            inventoryButton.setVisible(true);
            back.setVisible(false);
        }
        if (intList!=null){
            intList.setVisible(false);
            inventoryButton.setVisible(true);
            back.setVisible(false);
        }
        inventoryPanel.setVisible(true);
        lookPanel.setVisible(true);
        movePanel.setVisible(true);
        interPanel.setVisible(true);
        
        
        updateInfo("This is the main menu");
    }

    public void textboxPanel(boolean toggle) {
        if (toggle) {
            textboxPanel.setVisible(true);
        } else {
            enterTextField.setBackground(Color.GREEN);
            timer = new Timer();
            timerTask = new TimerTask() {
                public void run() {
                    textboxPanel.setVisible(false);
                };
            };
            timer.schedule(timerTask, 2000);
        }
    }
    
    public JButton buttonBuilder(String butTitle, String backCol, String foreCol, Game.EventHandler handle, String actCom) {
        JButton sender = new JButton(butTitle);
        sender.setBackground(Color.decode(backCol));
        sender.setForeground(Color.decode(foreCol));
        sender.setFont(normalFont);
        sender.addActionListener(handle);
        sender.setFocusPainted(false);
        sender.setActionCommand(actCom);
        return sender;
}
}
