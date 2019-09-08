import myUtil.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the PlayerInfo class, showing player's information. 
 * 
 * @author LIN, EN TING
 * @since 2019-06-30
 */
public class PlayerInfo extends JPanel {
		private static final long serialVersionUID = 1L;

		// =============== VARIABLES ===============
		public  static final int WIDTH = 340, HEIGHT = 130;
		private JLabel icon = new JLabel();
		private JPanel message = new JPanel(); // including name and money
		private JLabel name, money;
		private Player player;
		// =========================================
		
		/**
		 * constructor of no-player.
		 */
		public PlayerInfo() {
			this.setBackground(new Color(245, 222, 179));
		}
		
		/**
		 * constructor of normal player.
		 * @param _player 
		 */
		public PlayerInfo(Player _player) {
			this.player = _player;
			this.setBackground(new Color(245, 222, 179));
			this.icon.setIcon(new ImageIcon("images/Game/playerIcon/" + player.getID() + ".png"));
			
			name = new JLabel(player.getName(), JLabel.CENTER);
			money = new JLabel("Money : " + player.getCash(), JLabel.CENTER);
			name.setFont(new Font("Arial Black", Font.BOLD, 28));
			money.setFont(ENV.font.deriveFont(18f));
			
			message.setBackground(new Color(245, 222, 179));
			message.setLayout(new GridLayout(2, 1, 0, 0));
			message.add(name);
			message.add(money);
			
			setLayout(new GridLayout(1, 2, 0, 0));
			add(icon, new Integer(0));
			add(message, new Integer(0));
		}
		
		/**
		 * Update the money in information area.
		 */
		public void updateMoney() {
			message.remove(money);
			money = new JLabel("Money : " + player.getCash(), JLabel.CENTER);
			money.setFont(ENV.font.deriveFont(18f));
			message.add(money);
			validate();
			repaint();
		}
	}