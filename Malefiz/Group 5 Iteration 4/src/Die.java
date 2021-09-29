import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Randomly rolls a number between 1 and 6.
 */
public class Die {
	private static Die inst = new Die();
	private int faceValue = 0;
	private static JLabel die;

	/**
	 * Private constructor.
	 */
	private Die() {
	}
	
	/**
	 * Rolls the die.
	 * 
	 * @return value is the face value of the die
	 */
	public int roll() {
		faceValue = (int)(Math.random()*6) + 1;
		return faceValue;
	}
	
	/**
	 * Updates the image of the die.
	 * 
	 * @param icon the image after the roll            
	 */
	public void setImage(final ImageIcon icon) {
		die.setIcon(icon);
	}

	/**
	 * Instance of the die.
	 * @return inst is the instance of the die.
	 */
	public static Die getInstance(final JLabel icon) {
		if (inst == null) {
			synchronized (Die.class) {
				if (inst == null) {
					inst = new Die();
				}
			}
		}
		die = icon;
		return inst;
	}
}
