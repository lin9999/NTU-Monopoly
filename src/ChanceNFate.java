import java.util.ArrayList;
import java.util.Random;

/**
 * This is the ChanceNFate class, which is to generate kinds of event when player got to "CHANCE". 
 * 
 * @author LIN, EN TING
 * @since 2019-06-30
 */

public class ChanceNFate {
	// =============== VARIABLES ===============
	private Random r;
	private String text; // used to show the message
	private ArrayList<Event> eventList;
	// =========================================
	
	public ChanceNFate() {
		r = new Random();
		text = "";
		eventList = new ArrayList<Event>();
	}
	
	/**
	 * Get new event.
	 * @param curPlayer player who had got to "CHANCE".
	 */
	public void nextCnF(Player curPlayer) {
		int eventNum = r.nextInt(8);
		switch (eventNum) {
			case 0:
				text = "<html><body><p align=center>Meet with a NPC<br><font size=5>lose $500</p></body></html>\"";
				eventList.add(new Event(curPlayer, 1, -500));
				break;
			case 1:
				text = "<html><body><p align=center>Riding a bike in the building<br><font size=5>fine $800</p></body></html>\"";
				eventList.add(new Event(curPlayer, 1, -800));
				break;
			case 2:
				text = "<html><body><p align=center>Speeding on Royal Palm Blvd.<br><font size=5>fine $400</p></body></html>\"";
				eventList.add(new Event(curPlayer, 1, -400));
				break;
			case 3:
				text = "<html><body><p align=center>Help someone got his way<br><font size=5>get $300</p></body></html>\"";
				eventList.add(new Event(curPlayer, 1, 300));
				break;
			case 4:
				text = "<html><body><p align=center>Zhoushan Rd. is flooding<br><font size=5>lose $200</p></body></html>\"";
				eventList.add(new Event(curPlayer, 1, -200));
				break;
			case 5:
				text = "<html><body><p align=center>Take a photo for tourists<br><font size=5>get $800</p></body></html>\"";
				eventList.add(new Event(curPlayer, 1, 800));
				break;
			case 6:
				text = "<html><body><p align=center>Got grade F on Chinese<br><font size=5>suspend once</p></body></html>\"";
				eventList.add(new Event(curPlayer, 0, 1));
				break;
			case 7:
				text = "<html><body><p align=center>Clean up the building<br><font size=5>get $300</p></body></html>\"";
				eventList.add(new Event(curPlayer, 1, 300));
				break;
		}
	}
	
	/**
	 * @return the message of the event in HTML format. 
	 */
	public String getText() {
		if (!eventList.isEmpty())
			return text;
		else {
			System.out.println("There has no event!");
			return "";
		}
	}
	
	/**
	 * @return ArrayList class including all the Event class of such event.
	 */
	public ArrayList<Event> getEventList() {
		if (!eventList.isEmpty())
			return eventList;
		else {
			System.out.println("There has no event!");
			return null;
		}	
	}
}
