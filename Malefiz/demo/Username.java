import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;

import javax.swing.border.*;


public class Username extends JFrame implements ActionListener {
    JPanel panel,topPanel, bottomPanel;
    JLabel user_label, text, message;
    JTextField userName_text;
    JButton submit, back;

    public Username() {
        // TODO Auto-generated method stub
        this.setSize(800, 800);
		user_label = new JLabel();
      user_label.setText("User Name :");
      
      userName_text = new JTextField(20);
      // Submit
      

      
      panel = new JPanel(new GridLayout(3, 1));

      topPanel = new JPanel(new FlowLayout());
      bottomPanel = new JPanel();

      



      message = new JLabel("<html><h1><strong>Enter username</strong></h1><hr></html>");
      message.setHorizontalAlignment(SwingConstants.CENTER);
      message.setVerticalAlignment(SwingConstants.CENTER);
      panel.add(message);
      
      topPanel.setSize(200,100);
      topPanel.setAlignmentY(SwingConstants.CENTER);

      Border b2 = new EmptyBorder(100, 100, 100, 100);



      bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        

        topPanel.add(user_label, gbc);
      topPanel.add(userName_text,gbc);

        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setPreferredSize(new Dimension(300, 50));
        
        back = new JButton("Back");
        back.addActionListener(this);
        back.setPreferredSize(new Dimension(300, 50));
        
        gbc.weighty = 1;
        bottomPanel.add(submit, gbc);
        bottomPanel.add(back, gbc);


		getContentPane().setLayout(new BorderLayout());
		
    getContentPane().add(panel, BorderLayout.NORTH);
    getContentPane().add(topPanel, BorderLayout.CENTER);
    getContentPane().add(bottomPanel ,BorderLayout.SOUTH);
		
     
 

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Adding the listeners to components..
      submit.addActionListener(this);
      back.addActionListener(this);
    
     
      setTitle("Enter your username Here !");
      setVisible(true);
    }

    public void actionPerformed(ActionEvent aevt)
	{
		// get the object that was selected in the GUI
		Object selected = aevt.getSource();
				
		// if the 'New Game' button is clicked then these actions are performed
		if ( selected.equals(submit) )
		{
			this.dispose();
			Theme op = new Theme();
		}
		if ( selected.equals(back) )
		{
			this.dispose();
			Opponent op = new Opponent();
		}
		
		
	}
    
}
