import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Home_Page extends JFrame implements ActionListener{
	
	private JPanel topPanel, bottomPanel;
	private JLabel gameName;
	private JButton newGame, cont_inue, help, quit;
	
	public Home_Page() {
		
		this.setSize(800, 800);
		
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
        
        newGame = new JButton("New Game");
        newGame.addActionListener(this);
        cont_inue = new JButton("Continue");
        cont_inue.addActionListener(this);
        help = new JButton("Help");
        help.addActionListener(this);
        quit = new JButton("Exit");
        quit.addActionListener(this);
        
        gbc.weighty = 1;
        bottomPanel.add(newGame, gbc);
        bottomPanel.add(cont_inue, gbc);
        bottomPanel.add(help, gbc);
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
		if ( selected.equals(newGame) )
		{
			this.dispose();
			Opponent op = new Opponent();
		}
		else if(selected.equals(cont_inue))
		{
			// resume saved game
		}
		else if(selected.equals(help))
		{
			// display hints and rules
			displayHelp();
		}
		else if(selected.equals(quit))
		{
			this.dispose();
		}
		
	}
	
	private void displayHelp() {
		JOptionPane.showMessageDialog(this, "Gameplay: \nFirst play may be determined by a die-roll or another manner of the players' choosing.\n\n"
										+ "At the beginning of each turn, the active player rolls the die. That player selects one of her five pawns\n"
										+ "and moves it a number of steps equal to the number shown on the die. That pawn may begin traveling in any\n"
										+ "direction and may turn to continue through corners in the path, but it may not double-back along its course,\n"
										+ "and it may not forgo any steps. The spaces within the players' houses are not counted against the die-roll;\n"
										+ "the first space counted by any pawn is the space immediately above the house.\n\n"
										+ "During the course of a move, a pawn may pass other pawns (regardless of colour) with no effect. In the\n"
										+ "event that a pawn finishes its move by landing on a space occupied by another pawn, the pawn occupying\n"
										+ "that space is captured. Captured pawns are returned to their respective houses and become available to\n"
										+ "rejoin play upon their owner's next turn.\n\n"
										+ "Unlike pawns, barricade pieces may not be passed. In order for play to progress past a barricade, the\n"
										+ "barricade must be captured by a pawn. A player who captures a barricade must relocate the barrier to an\n"
										+ "unoccupied space on the board. Barricades may not be placed in the four houses or in any of the 17\n"
										+ "spaces in the bottom-most row.\n\n"
										+ "A pawn may not be moved if doing so would cause it either to pass a barricade or to overshoot the\n"
										+ "uppermost space on the board.\n\n"
										+ "After the move is complete, play passes to the next player.\n\n"
										+ "A player may forgo her move if and only if none of her pawns may be moved.\n\n"
										+ "Win condition: \nThe first player to land a pawn in the uppermost space is the winner.");
	}

}
