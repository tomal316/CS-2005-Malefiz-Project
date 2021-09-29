import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Opponent extends JFrame implements ActionListener{
	
	private JPanel topPanel, bottomPanel;
	private JLabel gameName;
	private JButton comp, custom, back, quit;

	public Opponent() {
		// TODO Auto-generated method stub

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
        
        comp = new JButton("1 v/s Computer");
        comp.addActionListener(this);
        custom = new JButton("Custom");
        custom.addActionListener(this);
        back = new JButton("Back");
        back.addActionListener(this);
        quit = new JButton("Exit");
        quit.addActionListener(this);
        
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
			Username userName = new Username();
		}
		else if(selected.equals(custom)) {
			// Let player choose number of opponents
			this.dispose();
			Username userName = new Username();
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
