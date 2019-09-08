import myUtil.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;

/**
 * This is the Property class, which is a JPanel referring to the square on the map.
 * 
 * @author LIN, EN TING
 * @since 2019-06-30
 */

public class Property extends JPanel {
	private static final long serialVersionUID = 1L;
	
	// =============== VARIABLES ===============
	final static int WIDTH = 85, HEIGHT = 75;
	private String name;
	private Color borderColor;
	private int x, y;
	private int value;
	private Player owner;
	// =========================================
	
	/**
	 * Constructor for "START"
	 */
	public Property(String link, String _name, Color textColor, Color bgColor, Color _borderColor, int _x, int _y) {
		this.name = _name;
		this.borderColor = _borderColor;
		this.x = _x;
		this.y = _y;
		this.value = 0;
		this.owner = null;
		
		JLabel nameText = new LinkLabel(name, link);
		nameText.setHorizontalAlignment(SwingConstants.CENTER);
		nameText.setFont(new Font("Arial Black", Font.BOLD, 17));
		nameText.setForeground(textColor);
		
		setLayout(new GridLayout(1, 1, 0, 0));
		setOpaque(true);
        setBackground(bgColor);
		setBorder(new MatteBorder(5, 5, 5, 5, _borderColor));
		add(nameText);
	}
	
	/**
	 * Constructor for special landmarks.
	 */
	public Property(String link, String _name, Color textColor, Color bgColor, Color _borderColor, String text, int _x, int _y) {
		this.name = _name;
		this.borderColor = _borderColor;
		this.x = _x;
		this.y = _y;
		this.value = 0;
		this.owner = null;
		
		JLabel nameText = new LinkLabel(name, link);
		nameText.setHorizontalAlignment(SwingConstants.CENTER);
		nameText.setFont(new Font("Arial Black", Font.BOLD, 17));
		nameText.setForeground(textColor);
		JLabel descriptText = new JLabel(" " + text, JLabel.CENTER);
		descriptText.setFont(ENV.font.deriveFont(14f));

		setLayout(new GridLayout(2, 1, 0, 0));
		setOpaque(true);
        setBackground(bgColor);
		setBorder(new MatteBorder(5, 5, 5, 5, _borderColor));
		add(nameText);
		add(descriptText);
	}
	
	/**
	 * Constructor for department landmarks.
	 */
	public Property(String link, String _name, Color textColor, Color bgColor, Color _borderColor, int _value, int _x, int _y) {
		this.name = _name;
		this.borderColor = _borderColor;
		this.x = _x;
		this.y = _y;
		this.value = _value;
		this.owner = null;
		JLabel nameText = new LinkLabel(name,link);
		nameText.setHorizontalAlignment(SwingConstants.CENTER);
		nameText.setFont(new Font("Arial Black", Font.BOLD, 17));
		nameText.setForeground(textColor);
		JLabel descriptText = (value >= 0) ? new JLabel("$" + value, JLabel.CENTER) : new JLabel("-$" + (-value), JLabel.CENTER);
		descriptText.setFont(ENV.font.deriveFont(14f));
		
		setLayout(new GridLayout(2, 1, 0, 0));
		setOpaque(true);
        setBackground(bgColor);
		setBorder(new MatteBorder(5, 5, 5, 5, _borderColor));
		add(nameText);
		add(descriptText);
	}
	
	/**
	 * @return the current X coordinate of the property.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return the current Y coordinate of the property.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @return the value of the property.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * set an owner to the property.
	 * @param newOwner the new owner of the property, null for releasing the property.
	 */
	public void setOwner(Player newOwner) {
		owner = newOwner;
		if (newOwner == null)
			this.setBorder(new MatteBorder(5, 5, 5, 5, borderColor));
		else
			this.setBorder(new MatteBorder(5, 5, 5, 5, Player.colorList[newOwner.getID()]));
		validate();
		repaint();
	}
	
	/**
	 * @return the owner of the property.
	 */
	public Player getOwner() {
		return owner;
	}
	
	/**
	 * @return the name of the property.
	 */
	public String getName() {
		return name;
	}
}
