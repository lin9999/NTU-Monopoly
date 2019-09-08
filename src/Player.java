import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

/**
 * This is the Player class, which is a JLabel referring to the figure of players. 
 * 
 * @author LIN, EN TING
 * @since 2019-06-30
 */

public class Player extends JLabel {
	private static final long serialVersionUID = 1L;
	
	// =============== VARIABLES ===============
	private static int[][] offsetList = {{-10, -10}, {Property.WIDTH-10, Property.HEIGHT-10}, {-10, Property.HEIGHT-10}, {Property.WIDTH-10, -10}};
	public  static Color[] colorList = {new Color(205, 38, 38), new Color(0, 100, 80), new Color(24, 116, 205), new Color(255, 185, 15)};
	
	private int ID;
	private String name;
	private int cash;
	private ArrayList<Property> propertyList;
	private int locat;
	private boolean stop;
	private boolean bankruptcy;
	// =========================================

	/**
	 * Constructor, create a player instance.
	 * @param _ID player's ID number.
	 * @param _Name player's name.
	 */
	public Player(int _ID, String _Name) {
		this.ID = _ID;
		this.name = _Name;
		this.cash = 4000;
		this.propertyList = new ArrayList<Property>();
		this.locat = 0;
		this.stop = false;
		this.bankruptcy = false;
		
		setOpaque(true);
		setBackground(colorList[this.ID]);
	}
	
	/**
	 * Default constructor, which should not be used.
	 */
	public Player() {
		this.ID = -1;
		this.name = "No Name";
		this.cash = 0;
		this.propertyList = new ArrayList<Property>();
		this.locat = 0;
		this.stop = false;
		this.bankruptcy = false;
	}
	
	/**
	 * @return the player's ID number.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * @return the player's Name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * To change player's cash.
	 * @param newCash the number that player's cash should be added, it can be any integer.
	 */
	public void addCash(int newCash) {
		cash += newCash;
	}

	/**
	 * @return the player's cash.
	 */
	public int getCash() {
		return cash;
	}
	
	/**
	 * To add a new property to player.
	 * @param newproperty new property of the player.
	 */
	public void addProperty(Property newproperty) {
		propertyList.add(newproperty);
	}
	
	/**
	 * Get a list of properties which the player has owned.
	 * @return the list of properties which player has owned.
	 */
	public ArrayList<Property> getPropertyList() {
		ArrayList<Property> ret = new ArrayList<Property>();
		for (int i = 0; i < propertyList.size(); i++)
			ret.add(propertyList.get(i));
		return ret;
	}
	
	/**
	 * To move the player to a new location.
	 * @param newLocat ID number of new location.
	 * @param p property which newLocat refers to.
	 */
	public void setLocat(int newLocat, Property p) {
		locat = newLocat;
		setBounds(p.getX() + offsetList[this.ID][0], p.getY() + offsetList[this.ID][1], 20, 20);
	}
	
	/**
	 * @return the player's current location.
	 */
	public int getLocat() {
		return locat;
	}
	
	/**
	 * To make the player movable/immovable.
	 * @param _s true if the player cannot move, false instead.
	 */
	public void setStop(boolean _s) {
		stop = _s;
	}
	
	/**
	 * @return true if the player cannot move, false instead.
	 */
	public boolean isStop() {
		return stop;
	}
	
	/**
	 * To change player's status to bankrupted.
	 */
	public void setBankruptcy() {
		bankruptcy = true;
	}
	
	/**
	 * @return true if the player is bankrupted, false instead.
	 */
	public boolean isBankrupted() {
		return bankruptcy;
	}
}