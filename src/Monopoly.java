import myUtil.*;

import javax.swing.*;

/**
 * This is the class which starts the game.
 * 
 * @author LIN, EN TING
 * @version 1.0
 * @since 2019-06-30
 */

public class Monopoly extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Some frame setting
	 */
	private void initFrame() {
		setTitle("NTU Monopoly Game");
 		setSize(ENV.WIDTH, ENV.HEIGHT);
 		setResizable(false);
 		setLocationRelativeTo(null);
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Constructor, initialize variables and show the frame
	 */
	public Monopoly() {
		initFrame();
		ENV.installFont();
		setContentPane(new Menu());
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Monopoly();
	}

}
