import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsernameScreen extends JFrame implements ActionListener {
    private JPanel titlePanel, mainPanel;
    private JLabel player1Userame, gameName, player2Userame, player3Userame, player4Userame;
    private JTextField Player1UsernameTextField, Player2UsernameTextField, Player3UsernameTextField,
            Player4UsernameTextField;
    private JButton nextButton, backButton;
    private int mainPanelycoord, gamePlayers;

    public UsernameScreen(int numPlayers){
        gamePlayers = numPlayers;
        GridBagConstraints gc = new GridBagConstraints();

        // Setting up the panels and other gui elements
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        // Setting up gui elements
        gameName = new JLabel("<html><h1><strong><i>MALEFIZ</i></strong></h1><hr></html>");

        player1Userame = new JLabel("<html><h2>Player 1 Username:</h2></html>");
        player1Userame.setPreferredSize(new Dimension(300, 50));

        Player1UsernameTextField = new JTextField();
        Player1UsernameTextField.setPreferredSize(new Dimension(300, 50));

        // Checking how many different usernames are required and creating the required number of text boxes for that
        if (numPlayers > 1) {

            player2Userame = new JLabel("<html><h2>Player 2 Username:</h2></html>");
            player2Userame.setPreferredSize(new Dimension(300, 50));

            Player2UsernameTextField = new JTextField();
            Player2UsernameTextField.setPreferredSize(new Dimension(300, 50));

            if (numPlayers > 2) {

                player3Userame = new JLabel("<html><h2>Player 3 Username:</h2></html>");
                player3Userame.setPreferredSize(new Dimension(300, 50));

                Player3UsernameTextField = new JTextField();
                Player3UsernameTextField.setPreferredSize(new Dimension(300, 50));

                if (numPlayers > 3) {

                    player4Userame = new JLabel("<html><h2>Player 4 Username:</h2></html>");
                    player4Userame.setPreferredSize(new Dimension(300, 50));

                    Player4UsernameTextField = new JTextField();
                    Player4UsernameTextField.setPreferredSize(new Dimension(300, 50));
                }
            }
        }
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        nextButton.setPreferredSize(new Dimension(300, 50));

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(300, 50));
        backButton.addActionListener(this);

        // adding elements to the gui

        gc.anchor = GridBagConstraints.NORTH;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy = 0;
        titlePanel.add(gameName, gc);

        mainPanelycoord = 0;
        gc.gridx = 0;
        gc.gridy = mainPanelycoord;
        gc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(player1Userame, gc);

        gc.gridx = 1;
        gc.gridy = mainPanelycoord;
        gc.anchor = GridBagConstraints.WEST;
        mainPanel.add(Player1UsernameTextField, gc);

        // Getting the usernames based on how many players are there
        // These lines create the text boxes for that and place them in the appropriate position on the screen
        if (numPlayers > 1){
            mainPanelycoord++;
            gc.gridx = 0;
            gc.gridy = mainPanelycoord;
            gc.anchor = GridBagConstraints.LINE_END;
            mainPanel.add(player2Userame, gc);

            gc.gridx = 1;
            gc.gridy = mainPanelycoord;
            gc.anchor = GridBagConstraints.WEST;
            mainPanel.add(Player2UsernameTextField, gc);

            if (numPlayers > 2){
                mainPanelycoord++;
                gc.gridx = 0;
                gc.gridy = mainPanelycoord;
                gc.anchor = GridBagConstraints.LINE_END;
                mainPanel.add(player3Userame, gc);

                gc.gridx = 1;
                gc.gridy = mainPanelycoord;
                gc.anchor = GridBagConstraints.WEST;
                mainPanel.add(Player3UsernameTextField, gc);

                if (numPlayers > 3){
                    mainPanelycoord++;
                    gc.gridx = 0;
                    gc.gridy = mainPanelycoord;
                    gc.anchor = GridBagConstraints.LINE_END;
                    mainPanel.add(player4Userame, gc);

                    gc.gridx = 1;
                    gc.gridy = mainPanelycoord;
                    gc.anchor = GridBagConstraints.WEST;
                    mainPanel.add(Player4UsernameTextField, gc);

                }
            }
        }
        mainPanelycoord++;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.gridx = 0;
        gc.gridy = mainPanelycoord;
        gc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(backButton, gc);

        mainPanelycoord++;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.gridx = 0;
        gc.gridy = mainPanelycoord;
        gc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(nextButton, gc);

        // Setting up some basic frame parameters
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titlePanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setSize(800, 800);
    }

    // Action event methods
    @Override
    public void actionPerformed(ActionEvent e) {
        Object selected = e.getSource();
        boolean hasEmpty = false;
        // After the next button is clicked there's a check for if every required user has entered the username
        // If a user hasn't put in a username an error message is displayed and the user is made to enter a username
        if (selected.equals(nextButton)){
            if (gamePlayers == 1){
                if ( Player1UsernameTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Player 1 needs a username!");
                    hasEmpty = true;
                }
            }
            else if (gamePlayers == 2){
                if ( Player1UsernameTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Player 1 needs a username!");
                    hasEmpty = true;
                }
                if ( Player2UsernameTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Player 2 needs a username!");
                    hasEmpty = true;
                }
            }
            else if (gamePlayers == 3){
                if ( Player1UsernameTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Player 1 needs a username!");
                    hasEmpty = true;
                }
                if ( Player2UsernameTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Player 2 needs a username!");
                    hasEmpty = true;
                }
                if ( Player3UsernameTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Player 3 needs a username!");
                    hasEmpty = true;
                }
            }
            else if (gamePlayers == 4){
                if ( Player4UsernameTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Player 4 needs a username!");
                    hasEmpty = true;
                }
                if ( Player1UsernameTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Player 1 needs a username!");
                    hasEmpty = true;
                }
                if ( Player2UsernameTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Player 2 needs a username!");
                    hasEmpty = true;
                }
                if ( Player3UsernameTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Player 3 needs a username!");
                    hasEmpty = true;
                }
            }
            // if there's no empty usernames then move forward to the next screen
            if (!hasEmpty) {
                dispose();
                ThemeScreen themeScreen = new ThemeScreen(gamePlayers);
            }
        }
        // going back to the previous screen
        if (selected.equals(backButton)){
            dispose();
            Opponent opponent = new Opponent();
        }
    }
}
