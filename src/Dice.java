import java.util.Random;

import javax.swing.*;

/**
 * This is the Dice class. 
 * 
 * @author LIN, EN TING
 * @since 2019-06-30
 */

public class Dice extends JButton {
	private static final long serialVersionUID = 1L;
	
	// =============== VARIABLES ===============
	private static Random r = new Random();
	private static ImageIcon[] iconList = new ImageIcon[6];
	public  static int WIDTH = 64, HEIGHT = 64;
	
	private int num;
	private boolean lock; // True if the dice cannot be rolled
	// =========================================
	
	/**
	 * Constructor, create a dice which default number is 1.
	 */
	public Dice() {
		this.num = 1;
		for (int i = 0; i < 6; i++) {
			iconList[i] = new ImageIcon("images/Game/dice/die" + (i+1) + ".png");
		}
		setIcon(iconList[0]);
	}
	
	/**
	 * @return current number of dice.
	 */
	public int getNum() {
		return num;
	}
	
	/**
	 * Roll the dice, to get the new number, use getNum().
	 */
	public void nextNum() {
		num = r.nextInt(6) + 1;
		setIcon(iconList[num-1]);
	}
	
	/**
	 * To check if the dice can be rolled.
	 * @return true for the dice can be rolled, instead.
	 */
	public boolean canRoll() {
		return !lock;
	}
	
	/**
	 * To lock the dice.
	 */
	public void lock() {
		lock = true;
	}
	
	/**
	 * To unlocked the dice.
	 */
	public void unlock() {
		lock = false;
	}
}
