import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.EventHandler;


public class TitleScreen extends JFrame{
    private JFrame window;
    private Container con;
    private JPanel border;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private JPanel titlePanel;
    private JLabel titleLabel;

    private JPanel startPanel;
    private JButton startButton;

    private Font titleFont = new Font("Papyrus",Font.PLAIN,75);
    private Font normalFont = new Font("Papyrus",Font.PLAIN,30);
    
    EventHandler titleHandler = new EventHandler();

    public TitleScreen(){
        setupJFrame();
        setupFirstScreen();


        window.setVisible(true);
    }
    public void setupJFrame(){
        window= new JFrame();
        

        window.setPreferredSize(new Dimension(screenSize.width,screenSize.height));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setTitle("Hours and Hours of Glass");
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        con=window.getContentPane();
       
    }
    public void setupFirstScreen(){
        border = new JPanel();
        border.setBorder(BorderFactory.createLineBorder(Color.decode("#e8975d"), 20));
        border.setBackground(Color.decode("#f6d7b0"));
        border.setBounds(0, 0, con.getWidth(), con.getHeight());
        border.setVisible(true);
        con.add(border);

        con.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent event) {
                border.setBounds(0, 0, con.getWidth(), con.getHeight());
            }
        });
        
        titlePanel = new JPanel();
        titlePanel.setBounds(screenSize.width/128,screenSize.height/2,1000,175);
        titlePanel.setOpaque(false);
       

        titleLabel= new JLabel("<html>Hours and Hours of Glass<html>");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.decode("#4a4540"));
        titlePanel.add(titleLabel);
        titlePanel.setVisible(true);
        con.add(titlePanel);
        

        startPanel = new JPanel();
        startPanel.setBounds(1000,500,350,70);
        startPanel.setBackground(Color.BLACK);
        startPanel.setBorder(null);
        startPanel.setOpaque(false);

        startButton = new JButton("Begin");
        startButton.setBackground(Color.decode("#f6d7b0"));
        startButton.setForeground(Color.decode("#4a4540"));
        startButton.setFont(normalFont);
        startButton.addActionListener(titleHandler);
        startButton.setFocusPainted(false);

        startButton.setActionCommand("startGame");
        
        
        startPanel.add(startButton);
        con.add(startPanel);
        con.setComponentZOrder(startPanel,0);
        con.setComponentZOrder(titlePanel, 1);
        con.setComponentZOrder(border, 2);

        startPanel.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->new TitleScreen().setVisible(true));
    }

}