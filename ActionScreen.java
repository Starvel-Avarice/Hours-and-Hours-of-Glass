import javax.swing.*;
import java.awt.*;

public class ActionScreen {
    private JPanel infoPanel;
    private JTextArea infoText;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Container grabber;

    private JPanel inventoryPanel;
    private JPanel lookPanel;
    private  JPanel movePanel;
    private JPanel interPanel;

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

    private Game game = new Game();
    Game.EventHandler choiceHandler = game.new EventHandler();
    Game.EventHandler compassHandler = game.new EventHandler();

    
    private Font normalFont = new Font("Papyrus",Font.PLAIN,30);

    public ActionScreen(Container con){
        grabber=con;
        setupMain();
    }
    
    public void setupMain(){
        infoPanel = new JPanel();
        infoPanel.setBounds(screenSize.width/2-300,screenSize.height/2-200,600,300);
        infoPanel.setLayout(null);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#e8975d"),15));
        infoPanel.setBackground(Color.decode("#efb787"));
        infoPanel.setVisible(false);

        infoText = new JTextArea();
        infoText.setBounds(screenSize.width/2-690,screenSize.height/2-425,570,270);
        infoText.setForeground(Color.decode("#4a4540"));
        infoText.setFont(normalFont);
        infoText.setEditable(false);
        infoText.setFocusable(false);
        infoText.setLineWrap(true);
        infoText.setWrapStyleWord(true);
        infoText.setOpaque(false);

        infoPanel.add(infoText);
        grabber.add(infoPanel,0);

        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BorderLayout());
        inventoryPanel.setBounds(screenSize.width/2-300, screenSize.height/2-285, 175, 75);
        inventoryPanel.setBackground(Color.decode("#efb787"));
        inventoryPanel.setVisible(false);

        lookPanel = new JPanel();
        lookPanel.setLayout(new BorderLayout());
        lookPanel.setBounds(screenSize.width/2-88, screenSize.height/2+110, 175, 75);
        lookPanel.setBackground(Color.decode("#efb787"));
        lookPanel.setVisible(false);
         
        movePanel = new JPanel();
        movePanel.setLayout(new BorderLayout());
        movePanel.setBounds(screenSize.width/2+125, screenSize.height/2+110, 175, 75);
        movePanel.setBackground(Color.decode("#efb787"));
        movePanel.setVisible(false);

        interPanel = new JPanel();
        interPanel.setLayout(new BorderLayout());
        interPanel.setBounds(screenSize.width/2-300, screenSize.height/2+110, 175, 75);
        interPanel.setBackground(Color.decode("#efb787"));
        interPanel.setVisible(false);

        inventoryButton = new JButton("Inventory");
        inventoryButton.setBackground(Color.decode("#efb787"));
        inventoryButton.setForeground(Color.decode("#4a4540"));
        inventoryButton.setFont(normalFont);
        inventoryButton.addActionListener(choiceHandler);
        inventoryButton.setFocusPainted(false);
        inventoryButton.setActionCommand("goInv");
        
        lookButton = new JButton("Look");
        lookButton.setBackground(Color.decode("#efb787"));
        lookButton.setForeground(Color.decode("#4a4540"));
        lookButton.setFont(normalFont);
        lookButton.addActionListener(choiceHandler);
        lookButton.setFocusPainted(false);
        lookButton.setActionCommand("goLook");

        moveButton = new JButton("Move");
        moveButton.setBackground(Color.decode("#efb787"));
        moveButton.setForeground(Color.decode("#4a4540"));
        moveButton.setFont(normalFont);
        moveButton.addActionListener(choiceHandler);
        moveButton.setFocusPainted(false);
        moveButton.setActionCommand("goMove");

        interButton = new JButton("Interact");
        interButton.setBackground(Color.decode("#efb787"));
        interButton.setForeground(Color.decode("#4a4540"));
        interButton.setFont(normalFont);
        interButton.addActionListener(choiceHandler);
        interButton.setFocusPainted(false);
        interButton.setActionCommand("goInter");

        inventoryPanel.add(inventoryButton);
        lookPanel.add(lookButton);
        movePanel.add(moveButton);
        interPanel.add(interButton);

        grabber.add(inventoryPanel,0);
        grabber.add(lookPanel,0);
        grabber.add(movePanel,0);
        grabber.add(interPanel,0);
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
        interPanel.setVisible(false);
        lookPanel.setVisible(false);
        movePanel.setVisible(false);
        updateInfo("What direction do you want to go?");

        dirCompassGrid = new JPanel();
        dirCompassGrid.setBounds(screenSize.width/2-225,555,450,250);
        dirCompassGrid.setBackground(Color.decode("#f6d7b0"));
        dirCompassGrid.setLayout(new GridLayout(3,3));
        dirCompassGrid.setVisible(true);
        dirCompassGrid.add(new JLabel());

        north = new JButton("North");
        north.setBackground(Color.decode("#efb787"));
        north.setForeground(Color.decode("#4a4540"));
        north.setFont(normalFont);
        north.addActionListener(compassHandler);
        north.setFocusPainted(false);
        north.setActionCommand("north");


        dirCompassGrid.add(north);
        dirCompassGrid.add(new JLabel());

        west = new JButton("West");
        west.setBackground(Color.decode("#efb787"));
        west.setForeground(Color.decode("#4a4540"));
        west.setFont(normalFont);
        west.addActionListener(compassHandler);
        west.setFocusPainted(false);
        west.setActionCommand("west");

        dirCompassGrid.add(west);

        upDown = new JButton("Up");
        upDown.setBackground(Color.decode("#efb787"));
        upDown.setForeground(Color.decode("#4a4540"));
        upDown.setFont(normalFont);
        upDown.addActionListener(compassHandler);
        upDown.setFocusPainted(false);
        upDown.setVisible(false);
        upDown.setActionCommand("up");

        dirCompassGrid.add(upDown);

        east = new JButton("East");
        east.setBackground(Color.decode("#efb787"));
        east.setForeground(Color.decode("#4a4540"));
        east.setFont(normalFont);
        east.addActionListener(compassHandler);
        east.setFocusPainted(false);
        east.setActionCommand("east");

        dirCompassGrid.add(east);
        dirCompassGrid.add(new JLabel());

        south = new JButton("South");
        south.setBackground(Color.decode("#efb787"));
        south.setForeground(Color.decode("#4a4540"));
        south.setFont(normalFont);
        south.addActionListener(compassHandler);
        south.setFocusPainted(false);
        south.setActionCommand("south");

        dirCompassGrid.add(south);
        dirCompassGrid.add(new JLabel());
        
        grabber.add(dirCompassGrid,0);

        
    }
    
    public void lookCompass(){
        interPanel.setVisible(false);
        lookPanel.setVisible(false);
        movePanel.setVisible(false);
        updateInfo("Where do you want to look?");

        lookCompassGrid = new JPanel();
        lookCompassGrid.setBounds(screenSize.width/2-225,555,450,250);
        lookCompassGrid.setBackground(Color.decode("#f6d7b0"));
        lookCompassGrid.setLayout(new GridLayout(3,3));
        lookCompassGrid.setVisible(true);
        lookCompassGrid.add(new JLabel());

        north = new JButton("Ahead");
        north.setBackground(Color.decode("#efb787"));
        north.setForeground(Color.decode("#4a4540"));
        north.setFont(normalFont);
        north.addActionListener(choiceHandler);
        north.setFocusPainted(false);
        north.setActionCommand("ahead");


        lookCompassGrid.add(north);
        lookCompassGrid.add(new JLabel());

        west = new JButton("Left");
        west.setBackground(Color.decode("#efb787"));
        west.setForeground(Color.decode("#4a4540"));
        west.setFont(normalFont);
        west.addActionListener(choiceHandler);
        west.setFocusPainted(false);
        west.setActionCommand("left");

        lookCompassGrid.add(west);

        upDown = new JButton("Current");
        upDown.setBackground(Color.decode("#efb787"));
        upDown.setForeground(Color.decode("#4a4540"));
        upDown.setFont(normalFont);
        upDown.addActionListener(choiceHandler);
        upDown.setFocusPainted(false);
        upDown.setActionCommand("current");

        lookCompassGrid.add(upDown);

        east = new JButton("Right");
        east.setBackground(Color.decode("#efb787"));
        east.setForeground(Color.decode("#4a4540"));
        east.setFont(normalFont);
        east.addActionListener(choiceHandler);
        east.setFocusPainted(false);
        east.setActionCommand("right");

        lookCompassGrid.add(east);
        lookCompassGrid.add(new JLabel());

        south = new JButton("Behind");
        south.setBackground(Color.decode("#efb787"));
        south.setForeground(Color.decode("#4a4540"));
        south.setFont(normalFont);
        south.addActionListener(choiceHandler);
        south.setFocusPainted(false);
        south.setActionCommand("behind");

        lookCompassGrid.add(south);
        lookCompassGrid.add(new JLabel());
        
        grabber.add(lookCompassGrid,0);
    }

    public void reset(){
        if (dirCompassGrid != null) {
            dirCompassGrid.setVisible(false);
        }
        if (lookCompassGrid != null) {
            lookCompassGrid.setVisible(false);
        }
        lookPanel.setVisible(true);
        movePanel.setVisible(true);
        interPanel.setVisible(true);
        updateInfo("This is the main menu");
    }
}
