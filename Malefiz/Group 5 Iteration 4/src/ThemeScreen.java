import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemeScreen extends JFrame implements ActionListener {
    private JLabel gameName, title;
    private JPanel titlePanel, themesPanel;
    private JComboBox themeComboBox;
    private JButton nextButton, backButton;
    private int gamePlayers;

    public ThemeScreen(int numPlayers) {
        gamePlayers = numPlayers;
        // Theme options for the combo box
        String[] themeOptions = {"R G B Y",
                "<html>X O &#9651; &#9634;</html>",
                "<html>&#9829; &#9830; &#9824; &#9827; </html>"};
        GridBagConstraints gc = new GridBagConstraints();

        // Setting up panels and other gui elements
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        themesPanel = new JPanel();
        themesPanel.setLayout(new GridBagLayout());

        gameName = new JLabel("<html><h1><strong><i>MALEFIZ</i></strong></h1><hr></html>");
        title = new JLabel("<html><h2>Theme</h2></html>");

        themeComboBox = new JComboBox(themeOptions);
        themeComboBox.setPreferredSize(new Dimension(300, 50));

        nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(300, 50));
        nextButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(300, 50));
        backButton.addActionListener(this);

        // adding elements to the gui
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy = 0;
        titlePanel.add(gameName, gc);

        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.SOUTH;
        themesPanel.add(title, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.NORTH;
        themesPanel.add(themeComboBox, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.CENTER;
        themesPanel.add(backButton, gc);

        gc.gridx = 0;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.CENTER;
        themesPanel.add(nextButton, gc);

        // setting up the frame and basic parameters
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titlePanel, BorderLayout.NORTH);
        getContentPane().add(themesPanel, BorderLayout.CENTER);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setSize(800, 800);
    }

    // Action event methods move forwards or backwards
    @Override
    public void actionPerformed(ActionEvent e) {
        Object selected = e.getSource();
        if (selected.equals(nextButton)){
            dispose();
            DifficultyScreen difficultyScreen = new DifficultyScreen(gamePlayers);
        }
        if (selected.equals(backButton)){
            dispose();
            UsernameScreen usernameScreen = new UsernameScreen(gamePlayers);
        }
    }
}
