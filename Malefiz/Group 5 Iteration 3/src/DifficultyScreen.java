import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyScreen extends JFrame implements ActionListener { // Implements action listener to be added later when actions are added
    private JPanel difficultyPanel, titlePanel;
    private JLabel difficultyLabel, gameName;
    private JRadioButton easy_RadioButton, hard_RadioButton;
    private ButtonGroup radioButtonGroup;
    private JButton nextButton, backButton;
    private int gamePlayers;

    public DifficultyScreen(int numPlayers){
        GridBagConstraints gc = new GridBagConstraints();
        // Panels and other gui elements created
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new GridBagLayout());

        gameName = new JLabel("<html><h1><strong><i>MALEFIZ</i></strong></h1><hr></html>");

        difficultyLabel = new JLabel("<html><h2>DIFFICULTY</h2></html>");

        easy_RadioButton = new JRadioButton("<html><h2>Easy</h2></html>");
        easy_RadioButton.setSelected(true);
        easy_RadioButton.setPreferredSize(new Dimension(300, 50));

        hard_RadioButton = new JRadioButton("<html><h2>Hard</h2></html>");
        hard_RadioButton.setPreferredSize(new Dimension(300, 50));

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(300, 50));
        backButton.addActionListener(this);

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        nextButton.setPreferredSize(new Dimension(300, 50));

        // setting up a radiobutton group
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(easy_RadioButton);
        radioButtonGroup.add(hard_RadioButton);

        // adding elements to the grid layout
        gc.anchor = GridBagConstraints.NORTH;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy = 0;
        titlePanel.add(gameName, gc);

        gc.anchor = GridBagConstraints.CENTER;
        gc.gridwidth = GridBagConstraints.REMAINDER;

        difficultyPanel.add(difficultyLabel, gc);

        gc.anchor = GridBagConstraints.NORTH;
        gc.gridx = 0;
        gc.gridy = 1;
        difficultyPanel.add(easy_RadioButton, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        difficultyPanel.add(hard_RadioButton, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        difficultyPanel.add(backButton, gc);

        gc.gridx = 0;
        gc.gridy = 4;
        difficultyPanel.add(nextButton, gc);


        // setting up the content frame and some parameters
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titlePanel, BorderLayout.NORTH);
        getContentPane().add(difficultyPanel);
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
        if (selected.equals(nextButton)){
            dispose();
//            GameWindow gameWindow = new GameWindow();
    		JFrame frame = new JFrame("Malefiz");
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		
    		GameWindow contentPane = new GameWindow();
    		contentPane.setOpaque(true);
    		contentPane.setBackground(new Color(169, 149, 123));
    		frame.setContentPane(contentPane);
    		
    		frame.pack();
    		frame.setResizable(false);
    		frame.setVisible(true);
        }
        if (selected.equals(backButton)){
            dispose();
            ThemeScreen themeScreen = new ThemeScreen(gamePlayers);
        }
    }
}
