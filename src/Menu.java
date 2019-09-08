import myUtil.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

/**
 * This is the Menu class. 
 * 
 * @author LIN, EN TING
 * @since 2019-06-30
 */

public class Menu extends JPanel {
	private static final long serialVersionUID = 1L;

	// =============== VARIABLES ===============
	private JLayeredPane layeredPane;
	private JPanel title = new JPanel();
	
	private JPanel subMenu = new JPanel();
	private JLabel startButt_sub = new JLabel("START", JLabel.CENTER);
	private JLabel creditButt_sub = new JLabel("CREDITS", JLabel.CENTER);
	private JLabel exitButt_sub = new JLabel("EXIT", JLabel.CENTER);
	
	private JPanel nameSetting = new JPanel();
	private JTextField[] nameList = new JTextField[4];
	private JLabel warning = new JLabel("THERE SHOULD BE AT LEAST 2 PLAYERS", JLabel.CENTER);
	private JLabel backButt_set = new JLabel("BACK", JLabel.CENTER);
	private JLabel startButt_set = new JLabel("START", JLabel.CENTER);
	
	private JPanel credit = new JPanel();
	private JLabel backButt_credit = new JLabel("BACK", JLabel.CENTER);
	// =========================================
	
	/**
	 * Initialize the title.
	 */
	private void initTitle() {
		JLabel titleText = new JLabel("== NTU MONOPOLY ==", JLabel.CENTER);
		title.setBorder(new MatteBorder(5, 5, 5, 5, Color.WHITE));
		title.setLayout(new GridLayout(1, 1, 0, 0));
		titleText.setFont(ENV.font.deriveFont(70f)); 
		titleText.setForeground(new Color(115, 74, 18));
		titleText.setBackground(new Color(250, 235, 215));
		titleText.setBorder(new MatteBorder(5, 5, 5, 5, new Color(61, 89, 171)));
		title.add(titleText);
	}
	
	/**
	 * Initialize the submenu.
	 */
	private void initSubMenu() {
		startButt_sub.setFont(ENV.font.deriveFont(Font.BOLD, 40f));
		startButt_sub.setForeground(Color.BLACK);
		creditButt_sub.setFont(ENV.font.deriveFont(Font.BOLD, 40f));
		creditButt_sub.setForeground(Color.BLACK);
		exitButt_sub.setFont(ENV.font.deriveFont(Font.BOLD, 40f));
		exitButt_sub.setForeground(Color.BLACK);
		
		subMenu.setLayout(new GridLayout(3, 1, 0, 0));
		subMenu.add(startButt_sub);
		subMenu.add(creditButt_sub);
		subMenu.add(exitButt_sub);
		subMenu.setBorder(new MatteBorder(7, 7, 7, 7, Color.WHITE));
		subMenu.setBackground(new Color(245, 222, 179));
	}
	
	/**
	 * Initialize the game setting area.
	 */
	private void initGameSetting() {
		nameSetting.setLayout(new GridLayout(7, 1, 0, 0));
		nameSetting.setOpaque(false);
		nameSetting.setBorder(new MatteBorder(7, 7, 7, 7, Color.white));
		
		JLabel remind = new JLabel("Input players' name (English Only)", JLabel.CENTER);
		remind.setOpaque(true);
		remind.setBackground(new Color(245, 222, 179));
		remind.setFont(ENV.font.deriveFont(20f));
		nameSetting.add(remind);
		
		for (int i = 0; i < 4; i++) {
			JPanel col = new JPanel();
			JLabel player = new JLabel("Player " + (i+1) + " : ");
			player.setFont(ENV.font.deriveFont(Font.BOLD, 20f));
			nameList[i] = new JTextField(15);
			nameList[i].setFont(new Font("Serif", Font.BOLD, 23));
			nameList[i].setBackground(new Color(232, 232, 232, 100));
			col.setLayout(new FlowLayout(FlowLayout.CENTER));
			col.setBorder(new EmptyBorder(0, 40, 10, 40));
			col.setBackground(new Color(245, 222, 179));
			col.add(player);
			col.add(nameList[i]);
			nameSetting.add(col);
		}

		warning.setOpaque(true);
		warning.setBackground(new Color(245, 222, 179));
		warning.setForeground(new Color(245, 222, 179));
		warning.setFont(ENV.font.deriveFont(18f));
		nameSetting.add(warning);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setBackground(new Color(245, 222, 179));
		backButt_set.setFont(ENV.font.deriveFont(Font.BOLD, 24f));
		startButt_set.setFont(ENV.font.deriveFont(Font.BOLD, 24f));
		buttons.add(backButt_set);
		buttons.add(startButt_set);
		nameSetting.add(buttons);
	}
	
	/**
	 * Initialize the credit.
	 */
	private void initCredit() {
		JTextArea content = new JTextArea();
		credit.setBorder(new MatteBorder(7, 7, 7, 7, Color.WHITE));
		credit.setBackground(new Color(245, 222, 179));
		content.append("This game have been created by : Lin, En Ting\n");
		content.append("↓ The origin version is on Github ↓\n");
		content.append("https://github.com/lin9999/OOP_final_project\n");
		content.setBackground(new Color(245, 222, 179));
		content.setFont(new Font("Arial Black", Font.BOLD, 20));
		backButt_credit.setFont(ENV.font.deriveFont(Font.BOLD, 24f));
		credit.add(content);
		credit.add(backButt_credit);
	}
	
	/**
	 * Initialize the layeredPane, add objects to the frame.
	 */
	private void initLayeredPane() {
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(ENV.WIDTH, ENV.HEIGHT));
		
		// add background
		JLabel background = new JLabel(new ImageIcon("images/Menu/menuBackground.png"));
		background.setBounds(0, 0, ENV.WIDTH, ENV.HEIGHT);
		layeredPane.add(background, new Integer(0));
		
		// add title
		title.setBounds(76, 120, 1000, 120);
		layeredPane.add(title, new Integer(1));
		
		// add subMenu		   
		subMenu.setBounds(384, 374, 384, 225);
		layeredPane.add(subMenu, new Integer(1));
		
		// add game setting
		nameSetting.setBounds(300, 374, 552, 280);
		
		// add credit
		credit.setBounds(301, 374, 550, 250);
		
		this.add(layeredPane);
	}
	
	/**
	 * A mouse listener which control buttons' action.
	 */
	MouseListener ml = new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			l.setForeground(Color.RED);
		}
		
		public void mouseExited(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			l.setForeground(Color.BLACK);
		}
		
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == creditButt_sub) {
				layeredPane.remove(subMenu);
				layeredPane.add(credit, new Integer(1));
				validate();
				repaint();
				creditButt_sub.setForeground(Color.BLACK);
			} else if (e.getSource() == backButt_credit) {
				layeredPane.remove(credit);
				layeredPane.add(subMenu, new Integer(1));
				validate();
				repaint();
				backButt_credit.setForeground(Color.black);
			} else if (e.getSource() == exitButt_sub) { 
				System.exit(0);
			} else if (e.getSource() == startButt_sub) {
				layeredPane.remove(subMenu);
				layeredPane.add(nameSetting, new Integer(1));
				validate();
				repaint();
				startButt_sub.setForeground(Color.BLACK);
			} else if (e.getSource() == backButt_set) {
				layeredPane.remove(nameSetting);
				layeredPane.add(subMenu, new Integer(1));
				validate();
				repaint();
				warning.setForeground(new Color(245, 222, 179));
				backButt_set.setForeground(Color.BLACK);
			} else if (e.getSource() == startButt_set) {
				ArrayList<Player> playerList = new ArrayList<Player>();
				for (int i = 0, n = 0; i < 4; i++) {
					if (!Menu.this.nameList[i].getText().isEmpty()) {
						System.out.println(nameList[i].getText());
						playerList.add(new Player(n++, Menu.this.nameList[i].getText()));
					}
				}
				
				// player should >= 2
				if (playerList.size() < 2) {
					warning.setForeground(Color.RED);
					validate();
					repaint();
				} else {
					Menu.this.setVisible(false);
					JFrame root = (JFrame)SwingUtilities.getRoot(Menu.this);
					root.setContentPane(new Game(playerList));
				}
			}
		}
	};
	
	/**
	 * constructor of Menu class.
	 */
	public Menu() {
		initTitle();
		initSubMenu();
		initGameSetting();
		initCredit();
		initLayeredPane();
		
		startButt_sub.addMouseListener(ml);
		creditButt_sub.addMouseListener(ml);
		exitButt_sub.addMouseListener(ml);
		backButt_set.addMouseListener(ml);
		startButt_set.addMouseListener(ml);
		backButt_credit.addMouseListener(ml);
	}
	
	/**
	 * driver method
	 */
	public static void main(String[] args) {
		ENV.installFont();
		JFrame f = new JFrame();
		f.setTitle("NTU Monopoly Game (Menu test)");
		f.setSize(ENV.WIDTH, ENV.HEIGHT);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		f.setContentPane(new Menu());
		f.setVisible(true);
	}

}
