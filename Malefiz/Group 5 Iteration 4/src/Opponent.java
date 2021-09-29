import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Opponent extends JFrame implements ActionListener{
	private int numPlayers;
	private JPanel topPanel, bottomPanel;
	private JLabel gameName;
	private JButton comp, custom, back, quit;

	public Opponent() {
		this.setSize(800, 800);
		// Setting up the elements and the frame, after creating them first
		topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        gameName = new JLabel("<html><h1><strong><i>MALEFIZ</i></strong></h1><hr></html>");
        topPanel.add(gameName, gbc);
        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        comp = new JButton("1 v/s Computer");
        comp.addActionListener(this);
        comp.setPreferredSize(new Dimension(300, 50));
        custom = new JButton("Custom");
        custom.addActionListener(this);
        custom.setPreferredSize(new Dimension(300, 50));
        back = new JButton("Back");
        back.addActionListener(this);
        back.setPreferredSize(new Dimension(300, 50));
        quit = new JButton("Exit");
        quit.addActionListener(this);
        quit.setPreferredSize(new Dimension(300, 50));
        
        gbc.weighty = 1;
        bottomPanel.add(comp, gbc);
        bottomPanel.add(custom, gbc);
        bottomPanel.add(back, gbc);
        bottomPanel.add(quit, gbc);
        
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(bottomPanel, BorderLayout.CENTER);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ends the program once the window has closed
		setResizable(false);							// restricts the size of the window
		setVisible(true);								// makes the frame visible
	}
	
	public void actionPerformed(ActionEvent aevt)
	{
		// get the object that was selected in the GUI
		Object selected = aevt.getSource();
				
		// if the 'New Game' button is clicked then these actions are performed
		if ( selected.equals(comp) )
		{
			//1 player vs computer
			this.dispose();
			UsernameScreen usernameScreen = new UsernameScreen(1);
		}
		else if(selected.equals(custom)) {
			// Let player choose number of opponents
			Object[] playerchoices = {"1 Player", "2 Players", "3 Players", "4 Players"};
			ImageIcon imageIcon = new ImageIcon("Logo.png");
			String selection = (String)JOptionPane.showInputDialog(
					this,
					"Choose the number of players",
					"Player Selection",
					JOptionPane.WARNING_MESSAGE,
					imageIcon,
					playerchoices,
					playerchoices[0]
			);
			if (selection != null) {
				if (selection.equals(playerchoices[0])) {
					numPlayers = 1;
				} else if (selection.equals(playerchoices[1])) {
					numPlayers = 2;
				} else if (selection.equals(playerchoices[2])) {
					numPlayers = 3;
				} else if (selection.equals(playerchoices[3])) {
					numPlayers = 4;
				}
				this.dispose();
				UsernameScreen usernameScreen = new UsernameScreen(numPlayers);
			}
		}
		else if(selected.equals(back)) {
			this.dispose();
			Home_Page hm = new Home_Page();
		}
		else if(selected.equals(quit)) {
			this.dispose();
		}
	}

}
