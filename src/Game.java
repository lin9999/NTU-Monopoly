import myUtil.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.MatteBorder;

/**
 * This is the Game class. 
 * 
 * @author LIN, EN TING
 * @since 2019-06-30
 */

public class Game extends JPanel {
	private static final long serialVersionUID = 1L;

	// =============== VARIABLES ===============
	private ArrayList<Player> playerList;
	private JLayeredPane layeredPane = new JLayeredPane();
	
	private JLabel map = new JLabel();
	private ArrayList<Property> propertyList;
	private ArrayList<PlayerInfo> playerInfoList;
	private Message message = new Message();
	
	private Dice dice = new Dice();
	private ChanceNFate cnf = new ChanceNFate();
	private Queue<Event> eventList = new LinkedList<Event>();	
	
	private int nPlayer = 0, nBankruptedPlayer = 0, turn = 0;
	// =========================================
	
	/**
	 * Initialize properties.
	 */
	private void initPropertyList() {
		final int nProperty = 20;
		//					 0		  1		  2		  3		   4		5		6		7		 8		  9		   10		11		 12		  13	   14		  15	   16		17		18	     19
		String[] nameList = {"START", "操 場", "新 體", "舊 體", "醉月湖", "新 生", "計 中", "心理系", "應力所", "資工系", "電機系", "獸醫系", "工海系", "圖書館", "健康中心", "森林系", "園藝系", "傅 鐘", "物理系", "戲劇系"};
		String[] linkList = {"https://www.ntu.edu.tw/",
								"https://pe.ntu.edu.tw/",
								"https://pe.ntu.edu.tw/",
								"https://ntusportscenter.ntu.edu.tw/",
								"https://www.ntu.edu.tw/12scence/02.html",
								"https://zh.wikipedia.org/wiki/%E5%9C%8B%E7%AB%8B%E8%87%BA%E7%81%A3%E5%A4%A7%E5%AD%B8%E6%96%B0%E7%94%9F%E6%95%99%E5%AD%B8%E9%A4%A8",
								"https://www.cc.ntu.edu.tw/",
								"https://www.psy.ntu.edu.tw/",
								"https://www.iam.ntu.edu.tw/zh/",
								"https://www.csie.ntu.edu.tw/",
								"https://www.ee.ntu.edu.tw/",
								"https://www.vm.ntu.edu.tw/DVM/",
								"http://www.esoe.ntu.edu.tw/",
								"https://www.lib.ntu.edu.tw/",
								"http://shmc.osa.ntu.edu.tw/",
								"https://www.fo.ntu.edu.tw/",
								"http://www.hort.ntu.edu.tw/web/index/index.jsp",
								"https://zh.wikipedia.org/wiki/%E5%82%85%E9%90%98_(%E5%9C%8B%E7%AB%8B%E8%87%BA%E7%81%A3%E5%A4%A7%E5%AD%B8)",
								"https://www.phys.ntu.edu.tw/",
								"http://homepage.ntu.edu.tw/~theatre/admission-02.htm"};
		int[][] posList = {{180, 250}, {180, 150}, {180, 20}, {375, 20}, {500, 20}, {600, 20}, {700, 20}, {800, 20}, {900, 20}, {1000, 20}, {1000, 120}, {1000, 210}, {1000, 340}, {900, 340}, {760, 340}, {660, 340}, {505, 340}, {405, 340}, {300, 340}, {180, 340}};
		int[] valueList = {0, 500, -200, 300, 0, 800, 1000, 1200, 1000, 2500, 2000, 1200, 3000, 0, -500, 900, 1500, 0, 1200, 800};

		propertyList = new ArrayList<Property>();
		for (int i = 0; i < nProperty; i++) {
			if (i == 0) { // START
				propertyList.add(new Property(linkList[i], nameList[i], new Color(16 ,78 ,139), new Color(244, 255 ,255), new Color(16 ,78, 139), posList[i][0], posList[i][1]));
			} else if (i == 4 || i == 17) { // CHANCE
				propertyList.add(new Property(linkList[i], nameList[i], new Color(115, 74, 18), new Color(244, 255 ,255), new Color(115, 74, 18), "CHANCE", posList[i][0], posList[i][1]));
			} else if (i == 13) { // LIBRARY
				propertyList.add(new Property(linkList[i], nameList[i], new Color(70, 64, 64), new Color(244, 255 ,255), new Color(70, 64, 64), "LIBRARY", posList[i][0], posList[i][1]));
			} else {
				propertyList.add(new Property(linkList[i], nameList[i], new Color(115, 74, 18), new Color(250, 235, 215), new Color(115, 74, 18), valueList[i], posList[i][0], posList[i][1]));
			}
		}
		
	}
	
	/**
	 * Initialize players' information.
	 */
	private void initPlayerInfoList() {
		playerInfoList = new ArrayList<PlayerInfo>();
		for (int i = 0; i < nPlayer; i++) {
			playerInfoList.add(new PlayerInfo(playerList.get(i)));
			playerInfoList.get(i).setBorder(new MatteBorder(5, 5, 5, 5, new Color(115, 74, 18)));
		}
		playerInfoList.get(0).setBorder(new MatteBorder(5, 5, 5, 5, Color.RED));
	}
	
	/**
	 * Initialize the layeredPane, add objects to the frame.
	 */
	private void initLayeredPane() {
		layeredPane.setPreferredSize(new Dimension(ENV.WIDTH, ENV.HEIGHT));
		layeredPane.setOpaque(true);;
		// map 
		map.setIcon(new ImageIcon("images/Game/map.png"));
		map.setBounds(0, 0, ENV.WIDTH, 433);
		layeredPane.add(map, new Integer(0));
		
		// Properties
		for (int i = 0; i < propertyList.size(); i++) {
			Property tmp = propertyList.get(i);
			tmp.setBounds(tmp.getX(), tmp.getY(), Property.WIDTH, Property.HEIGHT);
			layeredPane.add(propertyList.get(i), new Integer(1));
		}
		
		// player info
		int[][] PIpos = {{0, 433}, {340, 433}, {0, 563}, {340, 563}};
		for (int i = 0; i < nPlayer; i++) {
			PlayerInfo tmp = playerInfoList.get(i);
			tmp.setBounds(PIpos[i][0], PIpos[i][1], PlayerInfo.WIDTH, PlayerInfo.HEIGHT);
			layeredPane.add(tmp, new Integer(1));
		}
		for (int i = nPlayer; i < 4; i++) {
			PlayerInfo blank = new PlayerInfo();
			blank.setBounds(PIpos[i][0], PIpos[i][1], PlayerInfo.WIDTH, PlayerInfo.HEIGHT);
			layeredPane.add(blank, new Integer(1));
		}
		
		// message
		message.setBounds(680, 433, Message.WIDTH, Message.HEIGHT);
		layeredPane.add(message, new Integer(1));
		
		// dice
		dice.setBounds(570, 200, Dice.WIDTH, Dice.HEIGHT);
		layeredPane.add(dice, new Integer(2));
		
		// figures
		for (int i = 0; i < nPlayer; i++) {
			playerList.get(i).setLocat(0, propertyList.get(0));
			layeredPane.add(playerList.get(i), new Integer(2));
		}

	}
	
	/**
	 * Initialize the dice handler.
	 */
	private void initDiceHandler() {
		dice.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Player curPlayer = playerList.get(turn);
					if (curPlayer.isStop()) {
						dice.lock();
						eventList.offer(new Event(curPlayer, 0, 0));
						message.setMessage("<html><body><p align=center>Keep studying~~<br><font size=5>You are able to move in the next round</p></body></html>");
						message.setButton(1);
					} else if (dice.canRoll()) {
						dice.nextNum();
						// dice should be locked until the player click OK/YES/NO
						dice.lock();
						
						int newLocat = (curPlayer.getLocat() + dice.getNum()) % 20;						
						if (newLocat != 0 && newLocat < curPlayer.getLocat()) {
							curPlayer.addCash(1000);
							playerInfoList.get(curPlayer.getID()).updateMoney();
						}
						curPlayer.setLocat(newLocat, propertyList.get(newLocat));
												
						switch (curPlayer.getLocat()) {
							// START
							case 0:
								message.setMessage("<html><body><p align=center>Welcome Back!</p></body></html>");
								message.setButton(1);
								break;
							
							// CHANCE
							case 4:
							case 17:
								cnf.nextCnF(curPlayer);
								ArrayList<Event> cnfEventList = cnf.getEventList();
								for (int i = 0; i < cnfEventList.size(); i++)
									eventList.offer(cnfEventList.get(i));
								message.setMessage(cnf.getText());
								message.setButton(1);
								break;
							
							// LIBRARY
							case 13:
								eventList.offer(new Event(curPlayer, 0, 1));
								message.setMessage("<html><body><p align=center>Study!!<br><font size=5>Stop in the next round</p></body></html>");
								message.setButton(1);
								break;
							
							default:
								if (propertyList.get(newLocat).getValue() < 0) { // punishment grid
									eventList.offer(new Event(curPlayer, 1, propertyList.get(newLocat).getValue()));
									message.setMessage("<html><body><p align=center>Lose " + -propertyList.get(newLocat).getValue() + "</p></body></html>");
									message.setButton(1);
								} else if (propertyList.get(newLocat).getOwner() == null) { // the property has no owner
									if (curPlayer.getCash() >= propertyList.get(newLocat).getValue()) {
										eventList.offer(new Event(curPlayer, 1, -propertyList.get(newLocat).getValue()));
										message.setMessage("<html><body><p align=center>Pay $"+ propertyList.get(newLocat).getValue() +" to buy<br>" + propertyList.get(newLocat).getName() + " ?</p></body></html>");
										message.setButton(0);
									} else { // player dosen't have enough money
										message.setMessage("<html><body><p align=center>You don't have enough money to buy it</p></body></html>");
										message.setButton(1);
									}
								} else if (propertyList.get(newLocat).getOwner() != curPlayer) {
									eventList.offer(new Event(curPlayer, 1, -propertyList.get(newLocat).getValue()/2));
									eventList.offer(new Event(propertyList.get(newLocat).getOwner(), 1, propertyList.get(newLocat).getValue()/2));
									message.setMessage("<html><body><p align=center>The owner is " + propertyList.get(newLocat).getOwner().getName() + "<br>Pay $" + propertyList.get(newLocat).getValue()/2 + " to him</p></body></html>");
									message.setButton(1);
								} else { // the player has got to his property
									message.setMessage("<html><body><p align=center>Welcome Back!</p></body></html>");
									message.setButton(1);
								}
								break;
						}
					}
				}	
			}
		);
	}
	
	
	/**
	 * constructor of Game class.
	 * @param _playerList players of the game.
	 */
	public Game(ArrayList<Player> _playerList) {
		this.nPlayer = _playerList.size();
		this.playerList = _playerList; // shallow copy is fine
		
		initPropertyList();
		initPlayerInfoList();
		initLayeredPane();
		initDiceHandler();
				
 		add(layeredPane);
	}
		
	/**
	 * This is an inner class which handle the message area and buttons(YES/NO/OK). 
	 */
	class Message extends JPanel {
		private static final long serialVersionUID = 1L;
		
		// =============== VARIABLES ===============
		public  static final int WIDTH = 472, HEIGHT = 260;
		private int buttonSetType = 0; 
		private JPanel[] buttonSet = {new JPanel(), new JPanel()};
		private JLabel defaultMessage = new JLabel("<html><body><p align=center>NTU<br>MONOPOLY</p></body></html>", JLabel.CENTER);
		private JLabel content = new JLabel("", JLabel.CENTER);
		private JLabel yesText = new JLabel("YES", JLabel.CENTER);
		private JLabel noText = new JLabel("NO", JLabel.CENTER);
		private JLabel okText = new JLabel("OK", JLabel.CENTER);
		// =========================================
		
		/**
		 * Initialize buttons.
		 */
		private void initButtons() {
			yesText.setFont(ENV.font.deriveFont(Font.BOLD, 30f));
			noText.setFont(ENV.font.deriveFont(Font.BOLD, 30f));
			okText.setFont(ENV.font.deriveFont(Font.BOLD, 30f));
			yesText.addMouseListener(buttonListener);
			noText.addMouseListener(buttonListener);
			okText.addMouseListener(buttonListener);
			
			buttonSet[0].setBackground(new Color(245, 222, 179));
			buttonSet[0].setLayout(new GridLayout(1, 5));
			buttonSet[0].setPreferredSize(new Dimension(Message.WIDTH, 90));
			buttonSet[0].add(new JLabel());
			buttonSet[0].add(yesText, JPanel.RIGHT_ALIGNMENT);
			buttonSet[0].add(new JLabel());
			buttonSet[0].add(noText, JPanel.LEFT_ALIGNMENT);
			buttonSet[0].add(new JLabel());
			
			buttonSet[1].setBackground(new Color(245, 222, 179));
			buttonSet[1].setPreferredSize(new Dimension(Message.WIDTH, 90));
			buttonSet[1].add(okText);
		}
		
		/**
		 * constructor of Message class.
		 */
		public Message() {
			setBackground(new Color(245, 222, 179));
			setBorder(new MatteBorder(5, 5, 5, 5, new Color(115, 74, 18)));
			setLayout(new BorderLayout());

			defaultMessage.setFont(ENV.font.deriveFont(50f));
			content.setFont(new Font("Arial Black", Font.BOLD, 36));
			initButtons();		

			add(defaultMessage, BorderLayout.CENTER);
		}
		
		/**
		 * Set the default message.
		 */
		public void setDefaultMessage() {
			remove(content);
			add(defaultMessage, BorderLayout.CENTER);
			validate();
			repaint();
		}
		
		/**
		 * Set the message.
		 * @param newContent message in HTML format.
		 */
		public void setMessage(String newContent) {
			content.setText(newContent);
			removeAll();
			add(content, BorderLayout.CENTER);
			validate();
			repaint();
		}
		
		/**
		 * Set the buttons.
		 * @param type 0 for YES/NO button set, 1 for OK.
		 */
		public void setButton(int type) {
			buttonSetType = type;
			add(buttonSet[type], BorderLayout.SOUTH);
			validate();
			repaint();
		}
		
		/**
		 * Remove current buttons.
		 */
		public void removeButton() {
			remove(buttonSet[buttonSetType]);
			validate();
			repaint();
		}
		
		/**
		 * A mouse listener which control buttons' action.
		 */
		MouseListener buttonListener = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JLabel l = (JLabel)e.getSource();
				l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				l.setForeground(Color.red);
			}
			
			public void mouseExited(MouseEvent e) {
				JLabel l = (JLabel)e.getSource();
				l.setForeground(Color.black);
			}
			
			public void mouseClicked(MouseEvent e) {
				boolean nextRound = true;
				if (e.getSource() == yesText) { // only used when purchasing a property
					while (!eventList.isEmpty()) {
						Event event = eventList.poll();
						if (event.getType() == 1) { 
							event.getPlayer().addCash(event.getValue());	
							Property curProp = propertyList.get(event.getPlayer().getLocat());
							event.getPlayer().addProperty(curProp);
							curProp.setOwner(event.getPlayer());
						} else {
							System.out.println("Event type error!");
							System.exit(1);
						}
					}
					
					yesText.setForeground(Color.BLACK);
					message.removeButton();
				} else if(e.getSource() == noText) {
					eventList.clear();
					
					noText.setForeground(Color.BLACK);
					message.removeButton();
				} else if(e.getSource() == okText) {
					while (!eventList.isEmpty()) {
						Event event = eventList.poll();
						if (event.getType() == 0) {
							event.getPlayer().setStop(event.getValue() == 1 ? true : false);
						} else if (event.getType() == 1) {
							event.getPlayer().addCash(event.getValue());
						} else {
							System.out.println("Event type error!");
							System.exit(1);
						}
					}
					
					okText.setForeground(Color.BLACK);
					message.removeButton();
				}
				
				// To check if there has player gone bankrupt.
				for (int i = 0; i < playerInfoList.size(); i++) {
					playerInfoList.get(i).updateMoney();
					if (playerList.get(i).getCash() < 0 && !playerList.get(i).isBankrupted()) {
						playerList.get(i).setBankruptcy();
						nBankruptedPlayer++;
						
						// release properties
						for (int j = 0; j < playerList.get(i).getPropertyList().size(); j++) {
							playerList.get(i).getPropertyList().get(j).setOwner(null);
						}
						
						// add gray mask to figure and info
						JLabel mask1 = new JLabel(new ImageIcon("images/Game/playerIcon/bankrupted.png"));
						JLabel mask2 = new JLabel(new ImageIcon("images/Game/playerIcon/bankrupted.png"));
						mask1.setBounds(playerInfoList.get(i).getBounds());
						mask2.setBounds(playerList.get(i).getBounds());
						layeredPane.add(mask1, new Integer(3));
						layeredPane.add(mask2, new Integer(3));
						
						message.setMessage("<html><body><p align=center>" + playerList.get(i).getName() + " is bankrupted QQ</p></body></html>");
						message.setButton(1);
						nextRound = false;
					}
				}
				
				if (nextRound) {
					content.removeAll();
					message.remove(content);
					message.add(defaultMessage, BorderLayout.CENTER);
					
					// To check if the game should be finished
					if (nBankruptedPlayer == nPlayer-1) {
						Player winner = null;
						for (int i = 0; i < nPlayer; i++) {
							if (!playerList.get(i).isBankrupted()) {
								winner = playerList.get(i);
								break;
							}
						}
						
						Game.this.setVisible(false);
						JFrame root = (JFrame)SwingUtilities.getRoot(Game.this);
						root.setContentPane(new EndPage(winner));
					} else {
						playerInfoList.get(turn).setBorder(new MatteBorder(5, 5, 5, 5, new Color(115, 74, 18)));
						do {
							turn = (turn+1) % nPlayer;
						} while(playerList.get(turn).isBankrupted());
						playerInfoList.get(turn).setBorder(new MatteBorder(5, 5, 5, 5, Color.RED));
						validate();
						repaint();
						dice.unlock();
					}
					
				}
			}					
		};
	}

	
	/**
	 * driver method
	 */
	public static void main(String[] args) {
		ENV.installFont();
		JFrame f = new JFrame();
		f.setTitle("NTU Monopoly Game (Game test)");
 		f.setSize(ENV.WIDTH, ENV.HEIGHT);
 		f.setResizable(false);
 		f.setLocationRelativeTo(null);
 		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        ArrayList<Player> list = new ArrayList<Player>();
        for (int i = 0; i < 4; i++) {
        	list.add(new Player(i, "Player"+(i+1)));
        }
		f.add(new Game(list));
		f.setVisible(true);
	}
}