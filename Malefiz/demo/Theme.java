import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;

import javax.swing.border.*;


public class Theme extends JFrame implements ActionListener {
    JPanel panel,topPanel, bottomPanel;
    JLabel user_label, text, message;
    JTextField userName_text;
    JButton btn, back;

    public Theme() {
        // TODO Auto-generated method stub
        this.setSize(800, 800);

    panel = new JPanel(new GridLayout(3, 0));
      topPanel = new JPanel(new FlowLayout());
      bottomPanel = new JPanel();

      

      

      JLabel title = new JLabel("<html><h1><strong>Select a theme</strong></h1><hr></html>");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);


    topPanel.add(title);
    



    String[] choices = { "R,G,B,Y", "X, O ,Y , □", "❤︎ , ♦ , ♠ , ♣", };

    final JComboBox<String> cb = new JComboBox<String>(choices);

    cb.setMaximumSize(cb.getPreferredSize());
    cb.setAlignmentX(Component.CENTER_ALIGNMENT);
    

    bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        btn = new JButton("Submit");
        btn.setPreferredSize(new Dimension(300, 50));
        btn.addActionListener(this);

        back = new JButton("Back");
        back.addActionListener(this);
        back.setPreferredSize(new Dimension(300, 50));
        
        gbc.weighty = 1;
        bottomPanel.add(btn, gbc);
        bottomPanel.add(back, gbc);

        panel.add(cb); 

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH);
    getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    getContentPane().add(panel, BorderLayout.CENTER);
		
     
     
     
      setTitle("Enter your username Here !");
      setVisible(true);
    }

    public void actionPerformed(ActionEvent aevt)
	{
		// get the object that was selected in the GUI
		Object selected = aevt.getSource();
				
		// if the 'New Game' button is clicked then these actions are performed
		if ( selected.equals(btn) )
		{
			this.dispose();
			//Go to difficulty
		}
		if ( selected.equals(back) )
		{
			this.dispose();
			Username username = new Username();
		}
		
		
	}
    
}
