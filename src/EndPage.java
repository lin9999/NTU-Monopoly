import myUtil.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.MatteBorder;

/**
 * This is the EndPage class. 
 * 
 * @author LIN, EN TING
 * @since 2019-06-30
 */

public class EndPage extends JPanel {
	private static final long serialVersionUID = 1L;

	// =============== VARIABLES ===============
	private JLayeredPane layeredPane;
	
	private JPanel content = new JPanel();
	private JPanel contentBorder = new JPanel();
	private JPanel buttons = new JPanel();
	private JLabel menuButt = new JLabel("BACK TO MENU", JLabel.CENTER);
	private JLabel exitButt = new JLabel("EXIT", JLabel.CENTER);
	// =========================================
	
	/**
	 * Initialize the content of title.
	 * @param winner the winner of the game.
	 */
	private void initContent(Player winner) {
		JLabel titleText = new JLabel("!! Congratulations !!", JLabel.CENTER);
		titleText.setFont(ENV.font.deriveFont(70f)); 
		titleText.setForeground(new Color(115, 74, 18));
		titleText.setBackground(new Color(250, 235, 215));
		
		JLabel contentText = new JLabel("", JLabel.CENTER);
		contentText.setText("<html><body><p align=center>" + winner.getName() + "<br>has become the king of NTU</p></body></html>");
		contentText.setFont(new Font("Arial Black", Font.BOLD, 40));
		contentText.setForeground(new Color(115, 74, 18));
		contentText.setBackground(new Color(250, 235, 215));

		content.setBorder(new MatteBorder(5, 5, 5, 5, new Color(61, 89, 171)));
		content.setLayout(new GridLayout(2, 1, 0, 0));
		content.add(titleText);
		content.add(contentText);
		
		contentBorder.setBorder(new MatteBorder(5, 5, 5, 5, Color.WHITE));
		contentBorder.setLayout(new GridLayout(1, 1, 0, 0));
		contentBorder.add(content);
	}
	
	/**
	 * Initialize buttons.
	 */
	private void initButtons() {
		menuButt.setFont(ENV.font.deriveFont(Font.BOLD, 35f));
		menuButt.setForeground(Color.BLACK);
		exitButt.setFont(ENV.font.deriveFont(Font.BOLD, 35f));
		exitButt.setForeground(Color.BLACK);
		
		buttons.setLayout(new GridLayout(1, 2, 0, 0));
		buttons.add(menuButt);
		buttons.add(exitButt);
		buttons.setBorder(new MatteBorder(7, 7, 7, 7, Color.WHITE));
		buttons.setBackground(new Color(245, 222, 179));
	}
	
	/**
	 * Initialize the layeredPane, add objects to the frame.
	 */
	private void initLayeredPane() {
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(ENV.WIDTH, ENV.HEIGHT));

		// add background
		JLabel background = new JLabel(new ImageIcon("images/EndPage/endPageBackground.png"));
		background.setBounds(0, 0, ENV.WIDTH, ENV.HEIGHT);
		layeredPane.add(background, new Integer(0));
		
		// add text
		contentBorder.setBounds(76, 120, 1000, 300);
		layeredPane.add(contentBorder, new Integer(1));
		
		// add buttons
		buttons.setBounds(192, 500, 768, 120);
		layeredPane.add(buttons, new Integer(1));

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
			if (e.getSource() == menuButt) {
				EndPage.this.setVisible(false);
				JFrame root = (JFrame)SwingUtilities.getRoot(EndPage.this);
				root.setContentPane(new Menu());
			} else if (e.getSource() == exitButt) { 
				System.exit(0);
			}
		}
	};
	
	/**
	 * constructor of EndPage class.
	 * @param winner the winner of the game.
	 */
	public EndPage(Player winner) {
		initContent(winner);
		initButtons();
		initLayeredPane();
		
		menuButt.addMouseListener(ml);
		exitButt.addMouseListener(ml);
	}
	
	/**
	 * driver method
	 */
	public static void main(String[] args) {
		ENV.installFont();
		JFrame f = new JFrame();
		f.setTitle("NTU Monopoly Game (EndPage test)");
 		f.setSize(ENV.WIDTH, ENV.HEIGHT);
 		f.setResizable(false);
 		f.setLocationRelativeTo(null);
 		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
		f.add(new EndPage(new Player(0, "Player1")));
		f.setVisible(true);
	}

}
