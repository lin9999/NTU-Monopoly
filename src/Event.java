/**
 * This is the Event class.
 * 
 * @author LIN, EN TING
 * @since 2019-06-30
 */
public class Event {
	// =============== VARIABLES ===============
	private Player player;
	private int type;
	/*
	 * type 0 : the player should stop/move in next round (library)
	 * type 1 : pay/get money
	 */
	private int value; // 1/0 as true/false when type=0
	// =========================================
	
	/**
	 * Constructor, each event can have only one player, for event which influence multiple players, use multiple events.
	 */
	public Event(Player _player, int _type, int _value) {
		this.player = _player;
		this.type = _type;
		this.value = _value;
	}
	
	/**
	 * @return the player of the event
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * @return the type of the event
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * @return the value of the event
	 */
	public int getValue() {
		return value;
	}
}