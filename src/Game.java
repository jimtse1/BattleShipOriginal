import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Main class of the GUI. This is the original code and with a slight bug in Swing Graphics.
 *
 */
public class Game implements Runnable{
	
	private boolean button1Place = false;
	private boolean button1Complete = false;
	private boolean button2Place = false;
	private boolean button2Complete = false;
	private boolean button3Place = false;
	private boolean button3Complete = false;
	private boolean button4Place = false;
	private boolean button4Complete = false;
	private boolean easyMode = false;
	private boolean regularMode = false;
	private boolean rotations = false;
	private String name = "Player";
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Game());
	}
	
	@Override
	public void run(){
		
		Board PlayerPicksComputerPlaysBoard = new Board(10, 10, 4, "Computer Steve", true);

		JFrame frame = new JFrame("Battle Ship");
				
		JButton b2 = new JButton("Patrol Boat");
		JButton b3 = new JButton("Destroyer");
		JButton b4 = new JButton("Battleship");
		JButton b5 = new JButton("Aircraft-Carrier");
		JButton b6 = new JButton("Complete Building");
		JButton b7 = new JButton("Rotation");
		JButton b8 = new JButton ("Reset");
		
		JPanel northPanel = new JPanel();
		JPanel eastPanel = new JPanel();
		northPanel.setBorder(BorderFactory.createTitledBorder("Battle Ships"));
		northPanel.setLayout(new GridLayout(1,6));
		eastPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
		eastPanel.setLayout(new GridLayout(3,1));
		northPanel.add(b2);
		northPanel.add(b3);
		northPanel.add(b4);
		northPanel.add(b5);
		eastPanel.add(b7);
		eastPanel.add(b8);
		eastPanel.add(b6);
				
		JOptionPane.showMessageDialog(frame, "Battleship is a game in which two players hide ships"
				+ " on their respetive boards and then target those ships. \n Battleship involves two"
				+ " steps: Placing the ships on the board, and then trying to sink your opponet's ships"
				+ " in the opponet's board. \nThis game of Battleship is a single-user game in which"
				+ " you will play against the computer.");
						
		String playerName = JOptionPane.showInputDialog(frame, "Enter Player name");
		
		name = playerName;
		
		frame.setLayout(new BorderLayout());
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(eastPanel, BorderLayout.EAST);
		frame.add(PlayerPicksComputerPlaysBoard, BorderLayout.CENTER);

		
		/**
		 * Add each function of ship buttons
		 */
		b2.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				if (button2Place || button3Place || button4Place){
					JOptionPane.showMessageDialog(frame, "Place one ship at a time");
				}
				else{
				b2.setEnabled(false);
				button1Place = true;
				}
			}
		});
		
		b3.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				if (button1Place || button3Place || button4Place){
					JOptionPane.showMessageDialog(frame, "Place one ship at a time");
				}
				else{
				b3.setEnabled(false);
				button2Place = true;
				}
			}
		});
		
		b4.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				if (button1Place || button2Place || button4Place){
					JOptionPane.showMessageDialog(frame, "Place one ship at a time");
				}
				else{
				b4.setEnabled(false);
				button3Place = true;
				}
			}
		});
		
		b5.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				if (button1Place || button2Place || button3Place){
					JOptionPane.showMessageDialog(frame, "Place one ship at a time");
				}
				else{
				b5.setEnabled(false);
				button4Place = true;
				}
			}
		});
		
		/**
		 * Let rotations -- horizontal or vertical -- occur
		 */
		b7.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				rotations = !rotations;
			}
		});
		
		/**
		 * Reset button. Resets both buttons and ships on the board.
		 */
		b8.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				PlayerPicksComputerPlaysBoard.reset();
				b2.setEnabled(true);
				b3.setEnabled(true);
				b4.setEnabled(true);
				b5.setEnabled(true);
				button1Place = false;
				button1Complete = false;
				button2Place = false;
				button2Complete = false;
				button3Place = false;
				button3Complete = false;
				button4Place = false;
				button4Complete = false;
				
				PlayerPicksComputerPlaysBoard.repaint();
				frame.revalidate();
			}
		});
		
		/**
		 * A player should be able to press a coordinate on the grid after pressing on a button 
		 * for a ship. That coordinate will dictate whether the location is suitable for placing
		 * the particular ship. If the coordinate is suitable, then it will place the ship there. 
		 * The user will be notified of such.
		 */
		PlayerPicksComputerPlaysBoard.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				
				if (button1Place){
					int xCoordinates = (int) (e.getX()/50);
					int yCoordinates = (int) (e.getY()/50);
					
					if (!PlayerPicksComputerPlaysBoard.notOutOfBounds
							(xCoordinates, yCoordinates, 2, rotations)){
						JOptionPane.showMessageDialog
						(frame, "Cannot place ship out of bounds");
					}
					
					else if (!PlayerPicksComputerPlaysBoard.checkNotOverlapping
							(rotations, 2, xCoordinates, yCoordinates)){
						JOptionPane.showMessageDialog
						(frame, "Cannot place ship in overlapping positions");
					}
					
					else {
						PlayerPicksComputerPlaysBoard.putBoat
						(xCoordinates, yCoordinates, new PatrolBoat(rotations));
						button1Place = false;
						button1Complete = true;
						PlayerPicksComputerPlaysBoard.repaint();
						frame.revalidate();
					}
				}
				
				else if (button2Place){
					int xCoordinates = (int) (e.getX()/50);
					int yCoordinates = (int) (e.getY()/50);
					
					if (!PlayerPicksComputerPlaysBoard.notOutOfBounds
							(xCoordinates, yCoordinates, 3, rotations)){
						JOptionPane.showMessageDialog
						(frame, "Cannot place ship our of bounds");
					}
					
					else if (!PlayerPicksComputerPlaysBoard.checkNotOverlapping
							(rotations, 3, xCoordinates, yCoordinates)){
						JOptionPane.showMessageDialog
						(frame, "Cannot place ships in overlapping positions");
					}
					
					else {
						PlayerPicksComputerPlaysBoard.putBoat
						(xCoordinates, yCoordinates, new Destroyer(rotations));
						button2Place = false;
						button2Complete = true;
						PlayerPicksComputerPlaysBoard.repaint();
						frame.revalidate();
					}
				}
				
				else if (button3Place){
					int xCoordinates = (int) (e.getX()/50);
					int yCoordinates = (int) (e.getY()/50);
					
					if (!PlayerPicksComputerPlaysBoard.notOutOfBounds
							(xCoordinates, yCoordinates, 4, rotations)){
						JOptionPane.showMessageDialog
						(frame, "Cannot place ship our of bounds");
					}
					
					else if (!PlayerPicksComputerPlaysBoard.checkNotOverlapping
							(rotations, 4, xCoordinates, yCoordinates)){
						JOptionPane.showMessageDialog
						(frame, "Cannot place ships in overlapping positions");
					}
					
					else {
						PlayerPicksComputerPlaysBoard.putBoat
						(xCoordinates, yCoordinates, new BattleShip(rotations));
						button3Place = false;
						button3Complete = true;
						PlayerPicksComputerPlaysBoard.repaint();
						frame.revalidate();
					}
				}
				
				else if (button4Place){
					int xCoordinates = (int) (e.getX()/50);
					int yCoordinates = (int) (e.getY()/50);
					
					if (!PlayerPicksComputerPlaysBoard.notOutOfBounds
							(xCoordinates, yCoordinates, 5, rotations)){
						JOptionPane.showMessageDialog
						(frame, "Cannot place ship our of bounds");
					}
					
					else if (!PlayerPicksComputerPlaysBoard.checkNotOverlapping
							(rotations, 5, xCoordinates, yCoordinates)){
						JOptionPane.showMessageDialog
						(frame, "Cannot place ships in overlapping positions");
					}
					
					else {
						PlayerPicksComputerPlaysBoard.putBoat
						(xCoordinates, yCoordinates, new AircraftCarrier(rotations));
						button4Place = false;
						button4Complete = true;
						PlayerPicksComputerPlaysBoard.repaint();
						frame.revalidate();
					}
				}
				
				else{
					JOptionPane.showMessageDialog(frame, "Press on a button first to place a ship");
				}
			}
		});
		
		/**
		 * The Complete button. Once a user has completed placing ships, the game moves to
		 * step two: the player will play against the computer's randomly generated board
		 */
		b6.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				if (!(button1Complete && button2Complete && button3Complete && button4Complete)){
					JOptionPane.showMessageDialog(frame, "Must place all boats first");			
				}
				
				else {
					
					//Start the second step of the game.
					
					JOptionPane.showMessageDialog(frame, "Let's Continue");				
					
					String[] modes = {"Easy", "Regular"};
					JComboBox modesBox = new JComboBox(modes);
					JPanel modePanel = new JPanel(new GridLayout(0,1));
					modePanel.add(new JLabel("Choose your level of difficulty"));
					modePanel.add(modesBox);
					int result = JOptionPane.showConfirmDialog(null, modePanel, "Difficulty",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					
					if (result == JOptionPane.OK_OPTION){
						if (modesBox.getSelectedItem().equals("Easy")){
							easyMode = true;
							regularMode = false;
						}
						else{
							easyMode = false;
							regularMode = true;
						}
					}
					
					frame.setVisible(false);
					PlayerPicksComputerPlaysBoard.setStep(false);
					RandomBoard PlayerPlaysBoard = new RandomBoard(10,10, name, false);
					
					JFrame frameStepTwo = new JFrame(name + " against Computer -- Battle Ship!!!");
					frameStepTwo.setSize(1600, 1200);
					
					JPanel panelVsButton = new JPanel();
					JButton buttonVs = new JButton("Vs");
					buttonVs.setEnabled(false);
					panelVsButton.add(buttonVs);
					
					frameStepTwo.setLayout(new BorderLayout());
					frameStepTwo.add(PlayerPlaysBoard, BorderLayout.WEST);
					frameStepTwo.add(PlayerPicksComputerPlaysBoard, BorderLayout.EAST);
					frameStepTwo.add(panelVsButton, BorderLayout.CENTER);
					
					PlayerPlaysBoard.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e){
							
							int xCoordinates = (int) (e.getX()/50);
							int yCoordinates = (int) (e.getY()/50);
							Points[][] gameBoard = PlayerPlaysBoard.getGameBoard();
							
							if (gameBoard[yCoordinates][xCoordinates].hasbeenAttacked()){
								JOptionPane.showMessageDialog(frameStepTwo, 
										"Don't Press on Something Already Targerted!!"
										+ "  \n Check Yourself Before you Rekt Yourself");
							}
							else{
							PlayerPlaysBoard.targetLocation(xCoordinates, yCoordinates);

							if (PlayerPlaysBoard.hasAShipSunk()){
								JOptionPane.showMessageDialog(frameStepTwo, 
										PlayerPlaysBoard.getName() + " " +
								PlayerPlaysBoard.returnSunkShipName() + " has been sunk!");
							}
							
							if (easyMode){
								PlayerPicksComputerPlaysBoard.ComputerAIPickBoatRandom();
							}
							else{
								PlayerPicksComputerPlaysBoard.ComputerAttackRandom();
							}
							
							if (PlayerPicksComputerPlaysBoard.hasAShipSunk()){
								JOptionPane.showMessageDialog(frameStepTwo,	
										PlayerPicksComputerPlaysBoard.getName() + " " +
										PlayerPicksComputerPlaysBoard.returnSunkShipName() 
										+ "has been sunk!");
							}
							
							if (PlayerPlaysBoard.hasWon()){
								JOptionPane.showMessageDialog(frameStepTwo, 
										PlayerPlaysBoard.getName() + " Has Won!!!");
								HighScores getHighScore = new HighScores();
								int score = PlayerPlaysBoard.returnScore();
								String name = PlayerPlaysBoard.getName();
								JOptionPane.showMessageDialog(frameStepTwo, name + " ---->" + score);
								Scores addScore = new Scores(score, name);
								JOptionPane.showMessageDialog(frameStepTwo, 
										"Current High Score: " + getHighScore.getHighScore());
							}
							
							if (PlayerPicksComputerPlaysBoard.hasWon()){
								JOptionPane.showMessageDialog(frameStepTwo,
										PlayerPicksComputerPlaysBoard.getName() + "Has Won!!!");
							}
							
							PlayerPicksComputerPlaysBoard.repaint();
							PlayerPlaysBoard.repaint();
							frameStepTwo.revalidate();
						}
						}
					});
					
					frameStepTwo.setVisible(true);
					frameStepTwo.pack();
					frameStepTwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		});

		frame.pack();
		
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
