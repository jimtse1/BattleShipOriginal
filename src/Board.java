import java.awt.*;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * A board is a matrix in which the objects (ships) are hidden.
 * The user interacts with the board by targeting certain point locations. Once a user
 * targets a point location, the location should not be targeted again. The location should
 * indicate whether the user has sunk a ship or not. A player wins when he has sunk all ships on his
 * board.
 * 
 * NOTE: The Ships in the array of ships are the ship objects placed on the board. The gameBoard
 * contains the points that are on the board.
 * 
 * The name of the board is the board that is played on. That means that the board the computer
 * sets up, and the board that the player sets up, is not their own boards; in fact, it is
 * the boards of the opponent. 
 */
public class Board extends JPanel{
	private int height;
	private int length;
	private String playerName;
	private Points[][] gameBoard;
	private Ship[] arrayOfShips;
	private boolean firstStep;
	private String shipThatIsSunk;
	private int highScore;
	
	//Private variables mainly for the AI algorithm
	private boolean hotMode;
	private boolean foundDirection;
	private int hotModeX;
	private int hotModeY;
	private int placeHolderX;
	private int placeHolderY;
	private int randomDirection;
	
	/**
	 * Creates an empty board. An empty board should have all its points non-targeted and not 
	 * containing any current ship.
	 * @param x the length of the board
	 * @param y the height of the board
	 * @param numOfShips the number of ships to be on the board
	 * @param name the name of the player
	 * @param whether the ship is in step one (placing other ships) or not.
	 */
	
	public Board(int x, int y, int numOfShips, String name, boolean step){
		this.height = y;
		this.length = x;
		this.playerName = name;
		this.gameBoard = new Points[height][length];
		this.arrayOfShips = new Ship[numOfShips];
		this.firstStep = step;
		shipThatIsSunk = "";
		this.highScore = 100000;
		
		//Private variables mainly for the AI algorithm
		this.hotMode = false;
		this.foundDirection = false;
		this.hotModeX = 0;
		this.hotModeY = 0;
		this.placeHolderX = 0;
		this.placeHolderY = 0;
		this.randomDirection = 0;
		
		for (int i = 0; i < height; i++){
			for (int j = 0; j < length; j++){
				gameBoard[i][j] = new Points(i, j, false, -1, null);
			}
		}
	}
	
	
	/**
	 * Draw a board
	 */
	@Override
	public void paintComponent(Graphics g){
		for (int i = 0; i < this.height; i++){
			for (int j = 0; j < this.length; j++){
				g.setColor(Color.black);
				g.drawRect(50 * j, 50 * i, 50, 50);
				
				if (this.firstStep && !this.gameBoard[i][j].isEmpty()){
					g.setColor(Color.green);
				}
				
				else if (this.gameBoard[i][j].hasShipSunk()){
					g.setColor(Color.blue);
				}
				
				else if (!this.gameBoard[i][j].hasbeenAttacked()){
					g.setColor(Color.white);
				}
				
				else if (this.gameBoard[i][j].hasbeenAttacked() && this.gameBoard[i][j].isEmpty()){
					g.setColor(Color.red);
				}
				
				else {
					g.setColor(Color.GREEN);
				}
				
				g.fillRect(50 * j + 1, 50 * i + 1, 49, 49);
			}
		}
	}
	
	/**
	 * Get the size of the board
	 */
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(500,500);
	}
	
	/**
	 * Return the gameboard
	 * @return the gameboard
	 */
	public Points[][] getGameBoard(){
		return this.gameBoard;
	}
	
	/**
	 * Get the height of the Board
	 * @return the height of the Board
	 */
	public int getHeight(){
		int boardHeight = this.height;
		return boardHeight;
	}
	
	/**
	 * Get the length of the Board
	 * @return the length of the board
	 */
	public int getLength(){
		int boardLength = this.length;
		return boardLength;
	}
	
	/**
	 * Get the Array of Ships in the Board
	 * @return Array of Ships in the Board
	 */
	public Ship[] getArrayShips(){
		Ship[] resultArray = this.arrayOfShips;
		return resultArray;
	}
	
	/**
	 * Get the Name of the player using the board
	 * @return Name of player
	 */
	public String getName(){
		return this.playerName;
	}
	
	/**
	 * Set the name of the player using the board
	 */
	public void changeName(String newName){
		this.playerName = newName;
	}
	
	/**
	 * Returns whether board is in first step or not
	 */
	public boolean getStep(){
		return this.firstStep;
	}
	
	/**
	 * Indicates at what step the player is at (placing ships, or attacking ships)
	 */
	public void setStep(boolean step){
		this.firstStep = step;
	}
	
	/**
	 * Reset the Board
	 */
	public void reset(){
		for (int i = 0; i < this.height; i++){
			for (int j = 0; j < this.length; j++){
				Points reducePoint = this.gameBoard[i][j];
				Ship reduceShip = reducePoint.getShip();
				if (!reducePoint.isEmpty()){
					reduceShip.reset();
					reducePoint.setShip(null);
					reducePoint.setShipIndex(-1);
				}
			}
		}
	}
	/**
	 * Put a boat on the Board. The coordinates of the argument is the leftmost
	 * or topmost point of the ship.
	 * @param x the x coordinate of the leftmost point (Column Value): zero-based
	 * @param y the y coordinate of the leftmost point (Row Value): zero-based
	 * @param AddedShip the Ship to be added
	 */
	public void putBoat(int x, int y, Ship AddedShip){
		
		    int length = AddedShip.getLength();
		    Points[] shipPoints = AddedShip.getPoints();
		    
		    if (!this.notOutOfBounds(x, y, length, AddedShip.isItRotated())){
		    	throw new IllegalArgumentException();
		    }
		    
		    if (!this.checkNotOverlapping(AddedShip.isItRotated(), length, x, y)){
		    	throw new IllegalArgumentException();
		    }
		    
		    if (!AddedShip.isItRotated()){
			    for (int j = x; j < x + length; j++){
				    Points setPoint = this.gameBoard[y][j];
				    setPoint.setCoordinates(j, y);
				    setPoint.setShip(AddedShip);
				    setPoint.setShipIndex(j-x);
				    shipPoints[j-x] = setPoint;
			    }
		    }
		
		    else if (AddedShip.isItRotated()){
			    for (int i = y; i < y + length; i++){
				    Points setPoint = this.gameBoard[i][x];
				    setPoint.setCoordinates(x, i);
				    setPoint.setShip(AddedShip);
				    setPoint.setShipIndex(i-y);
				    shipPoints[i-y] = setPoint;
			    }
		    }
		    
		this.arrayOfShips[length - 2] = AddedShip;
	}
	
	/**
	 * Checks to see whether the ship placed is out of bounds
	 * @param x the x coordinate of leftmost or topmost point: zero-based
	 * @param y the y coordinate of rightmost or bottommost point: zero-based
	 * @param length the length of the ship
	 * @param isRotated whether the ship is rotated or not
	 * @return whether the ship placed is out of bounds
	 */
	public boolean notOutOfBounds (int x, int y, int shipLength, boolean isRotated){
		if (!isRotated){
			return (this.length - (x + shipLength)) >= 0;
		}
		else {
			return (this.height - (y + shipLength)) >= 0;
		}
	}
	
	/**
	 * Method to check whether adding a ship overlaps
	 * @param length the length of the ship
	 * @param x coordinate of leftmost piece of ship
	 * @param y coordinate of leftmost piece of ship
	 * @return boolean value whether the ship overlap
	 */
	public boolean checkNotOverlapping(boolean rotate, int length, int x, int y){
		if (!rotate){
		    for (int i = x; i < x + length; i++){
			    Points toCheck = this.getGameBoard()[y][i];
			    
			    if (!toCheck.isEmpty()){
				    return false;
			    }
			    
		    }
		    return true;
	    }
		
		else {
			for (int i = y; i < y + length; i++){
				Points toCheck = this.getGameBoard()[i][x];
				
			    if (!toCheck.isEmpty()){
				    return false;
			    }
			}
			return true;
		}
	}
	
	/**
	 * When a user targets a specified location.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void targetLocation(int x, int y){
		Points targetedPoint = this.gameBoard[y][x];
		
		if (targetedPoint.hasbeenAttacked()){
			throw new IllegalArgumentException();
		}
		targetedPoint.Target();
		this.highScore = this.highScore - 1000;
	}
	
	/**
	 * checks whether the player is the winner
	 * @return whether the player is the winner or not.
	 */
	public boolean hasWon(){
		for (Ship individualShip : this.arrayOfShips){
			if (!individualShip.Destroyed()){
				return false;
			}
		}
		this.highScore += 100000;
		return true;
	}
	
	/**
	 * Prints out the name of the player who has won
	 */
	public void hasWonMessage(){
		if(this.hasWon()){
			System.out.println(this.playerName + " has won");
		}
		else{
			System.out.println(this.playerName + " has not won yet");
		}
	}
	
	/**
	 * Returns whether a ship has been recently sunk due to a targeted point
	 * NOTE: cannot sink two ships at a time.
	 * @return whether a ship has just been recently sunk
	 */
	public boolean hasAShipSunk(){
		for (int i = 0; i < this.arrayOfShips.length; i++){
			Ship shipToCheck = this.arrayOfShips[i];
			if (shipToCheck.Destroyed() && !shipToCheck.getDestroyedMessage()){
				Points[] shipPoints = shipToCheck.getPoints();
				shipToCheck.hasSunk();
				
				for (int j = 0; j < shipPoints.length; j++){
					shipPoints[j].sinkShip();
				}
				this.shipThatIsSunk = shipToCheck.getName();
				return true;
			}
		}
		return false;
	}
	
	public String returnSunkShipName(){
		return this.shipThatIsSunk;
	}
	
	/**
	 * Checks whether there exists another point that would be the "hot mode" for the AI
	 * @return whether there exists another point or not
	 */
	public boolean existsAnotherPoint(){
		for (int i = 0; i < this.height; i++){
			for (int j = 0; j < this.length; j++){
				Points pointToCheck = this.gameBoard[i][j];
				if (pointToCheck.hasbeenAttacked() && !pointToCheck.isEmpty()){
					
					if (!pointToCheck.getShip().Destroyed()){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Get the exact point so that the AI may use that point for "hot mode"
	 * @return the actual point that could be considered as "hot mode"
	 * NOTE: Only use if existsAnotherPoint is true. Check existsAnotherPoint first.
	 */
	public Points getAnotherPoint(){
		for (int i = 0; i < this.height; i++){
			for (int j = 0; j < this.length; j++){
				Points pointToCheck = this.gameBoard[i][j];
				if (pointToCheck.hasbeenAttacked() && !pointToCheck.isEmpty()){
					
					if (!pointToCheck.getShip().Destroyed()){
						return pointToCheck;
					}
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Return the high score of the particular board
	 * @return the high score
	 */
	public int returnScore(){
		return this.highScore;
	}

	
	/**
	 * ---------------------------------------Random "Fair" Algorithm ------------------------
	 */
	
	/**
	 * When the computer picks a point out of random. Easy Mode of the Game.
	 * @param ComputerBoard the board of the computer
	 */
	public void ComputerAIPickBoatRandom(){
		boolean notIntersect = false;
		
		while (!notIntersect){
			int x = (int) (Math.random() * this.length);
			int y = (int) (Math.random() * this.height);
			
			if (!this.gameBoard[y][x].hasbeenAttacked()){
				notIntersect = true;
				this.targetLocation(x, y);
			    }
			}
		}
	
	/**
	 * Truly random algorithm for the AI Program -- Random Mode
	 */
	public void ComputerAttackRandom(){
		if (!this.hotMode){
			this.ComputerAIPickRandom();
		}
		else{
			this.computerAIHotModeRandom();
		}
	}
	
	/**
	 * Computer should pick a point at random
	 */
	public void ComputerAIPickRandom(){
		boolean notIntersect = false;
		
		while (!notIntersect){
			int x = (int) (Math.random() * this.length);
			int y = (int) (Math.random() * this.height);
			
			if (!this.gameBoard[y][x].hasbeenAttacked()){
				notIntersect = true;
				this.targetLocation(x, y);
				
				if (!this.gameBoard[y][x].isEmpty()) {
					this.hotModeX = x;
					this.hotModeY = y;
					this.placeHolderX = x;
					this.placeHolderY = y;
					this.hotMode = true;
					this.randomDirection = (int) (Math.random() * 4);
				    }
			    }
			}
		}
	

	/**
	 * When the computer is in hot mode and in random mode.
	 */
	public void computerAIHotModeRandom(){
		Points originalPoint = this.gameBoard[this.hotModeY][this.hotModeX];
		this.computerAIHotModeSeeking();
		
		if (originalPoint.getShip().Destroyed()){
			
			if (this.existsAnotherPoint()){
				Points newHotMode = this.getAnotherPoint();
				this.hotModeX = newHotMode.getXCoordinate();
				this.hotModeY = newHotMode.getYCoordinate();
				this.placeHolderX = newHotMode.getXCoordinate();
				this.placeHolderY = newHotMode.getYCoordinate();
				this.randomDirection = (int) (Math.random() * 4);
			}
			
			else{
			this.hotMode = false;
			this.hotModeX = 0;
			this.hotModeY = 0;
			this.placeHolderX = 0;
			this.placeHolderY = 0;
			this.randomDirection = 0;
			}
		}
	}
	
	/**
	 * The computer seeks a location based on being in the random mode.
	 * Algorithm Introduces Recursion. Recursion is necessary because the function must call itself
	 * when it does not satisfy the conditions.
	 */
	public void computerAIHotModeSeeking(){
		System.out.println("Tracking");

		if (this.randomDirection == 0){
			int hotModeNext0 = this.placeHolderX + 1;
			
			if (hotModeNext0 < this.length){
				Points hotNext0 = this.gameBoard[this.hotModeY][hotModeNext0];
				
				//For a special case
				if(!hotNext0.isEmpty() && hotNext0.hasbeenAttacked()){
					if (!hotNext0.getShip().Destroyed()){
					boolean check = false;
					for (int i = hotModeNext0 + 1; i < this.length; i++){
						Points trackNext0 = this.gameBoard[this.hotModeY][i];
						
						if (trackNext0.hasbeenAttacked() && trackNext0.isEmpty()){
							this.placeHolderX = this.hotModeX;
							this.randomDirection = (int) (Math.random() * 4);
							check = true;
							this.computerAIHotModeSeeking();
							i = this.length;
						}
						else if(!trackNext0.hasbeenAttacked()){
							check = true;
							this.targetLocation(i, this.hotModeY);
							
							if (!trackNext0.isEmpty()){
								this.placeHolderX = i;
							}
							else{
								this.placeHolderX = this.hotModeX;
								this.randomDirection = (int) (Math.random() * 4);
							}
							i = this.length;
						}
					}
					if (!check){
						this.placeHolderX = this.hotModeX;
						this.randomDirection = (int) (Math.random() * 4);
						this.computerAIHotModeSeeking();
					}
				}
				else{
						this.placeHolderX = this.hotModeX;
						this.randomDirection = (int) (Math.random() * 4);
						this.computerAIHotModeSeeking();
				}
				}
				else if(!hotNext0.hasbeenAttacked()){
					targetLocation(hotModeNext0, this.hotModeY);
					
					if (!hotNext0.isEmpty()){
						this.placeHolderX = hotModeNext0;
					}
					
					else{
						this.placeHolderX = this.hotModeX;
						this.randomDirection = (int) (Math.random() * 4);
					}
				}
				
				else{
					this.placeHolderX = this.hotModeX;
					this.randomDirection = (int) (Math.random() * 4);
					this.computerAIHotModeSeeking();
				}
			}
			
			else{
				this.placeHolderX = this.hotModeX;
				this.randomDirection = (int) (Math.random() * 4);
				this.computerAIHotModeSeeking();
			}
		}
		
		else if (this.randomDirection == 1){
			int hotModeNext1 = this.placeHolderX - 1;

			if (hotModeNext1 >= 0){
				Points hotNext1 = this.gameBoard[this.hotModeY][hotModeNext1];
				
				//Special Case
				if(!hotNext1.isEmpty() && hotNext1.hasbeenAttacked()){
					if(!hotNext1.getShip().Destroyed()){
					
					boolean check = false;
					for (int i = hotModeNext1 - 1; i >= 0; i--){
						Points trackNext1 = this.gameBoard[this.hotModeY][i];
						
						if (trackNext1.hasbeenAttacked() && trackNext1.isEmpty()){
							this.placeHolderX = this.hotModeX;
							this.randomDirection = (int) (Math.random() * 4);
							check = true;
							this.computerAIHotModeSeeking();
							i = -1;
						}
						else if(!trackNext1.hasbeenAttacked()){
							check = true;
							this.targetLocation(i, this.hotModeY);
							
							if (!trackNext1.isEmpty()){
								this.placeHolderX = i;
							}
							else{
								this.placeHolderX = this.hotModeX;
								this.randomDirection = (int) (Math.random() * 4);
							}
							i = -1;
						}
					}
					if (!check){
						this.placeHolderX = this.hotModeX;
						this.randomDirection = (int) (Math.random() * 4);
						this.computerAIHotModeSeeking();
					}					
				}
					else{
						this.placeHolderX = this.hotModeX;
						this.randomDirection = (int) (Math.random() * 4);
						this.computerAIHotModeSeeking();	
					}
				}
				else if (!hotNext1.hasbeenAttacked()){
					targetLocation(hotModeNext1, this.hotModeY);

					
					if (!hotNext1.isEmpty()){
						this.placeHolderX = hotModeNext1;
					}
					else{
						this.placeHolderX = this.hotModeX;
						this.randomDirection = (int)(Math.random() * 4);
					}
				}
			else{
					this.placeHolderX = this.hotModeX;
					this.randomDirection = (int) (Math.random() * 4);
					this.computerAIHotModeSeeking();
			}
			}
			else{
				this.placeHolderX = this.hotModeX;
				this.randomDirection = (int) (Math.random() * 4);
				this.computerAIHotModeSeeking();
			}
		}
		
		else if (this.randomDirection == 2){
			int hotModeNext2 = this.placeHolderY + 1;
			
			if (hotModeNext2 < this.height){
				Points hotNext2 = this.gameBoard[hotModeNext2][this.hotModeX];
				
				//special case
				if(!hotNext2.isEmpty() && hotNext2.hasbeenAttacked()){
					if (!hotNext2.getShip().Destroyed()){
					boolean check = false;
					for (int i = hotModeNext2 + 1; i < this.height; i++){
						Points trackNext2 = this.gameBoard[i][this.hotModeX];
						
						if (trackNext2.hasbeenAttacked() && trackNext2.isEmpty()){
							this.placeHolderY = this.hotModeY;
							this.randomDirection = (int) (Math.random() * 4);
							check = true;
							this.computerAIHotModeSeeking();
							i = this.length;
						}
						else if(!trackNext2.hasbeenAttacked()){
							check = true;
							this.targetLocation(this.hotModeX, i);
							
							if (!trackNext2.isEmpty()){
								this.placeHolderY = i;
							}
							else{
								this.placeHolderY = this.hotModeY;
								this.randomDirection = (int) (Math.random() * 4);
							}
							i = this.length;
						}
					}
					if (!check){
						this.placeHolderY = this.hotModeY;
						this.randomDirection = (int) (Math.random() * 4);
						this.computerAIHotModeSeeking();
					}
				}
				else{
						this.placeHolderY = this.hotModeY;
						this.randomDirection = (int) (Math.random() * 4);
						this.computerAIHotModeSeeking();		
				}
				}

				else if (!hotNext2.hasbeenAttacked()){
					this.targetLocation(this.hotModeX, hotModeNext2);

					
					if (!hotNext2.isEmpty()){
						this.placeHolderY = hotModeNext2;
					}
					else{
						this.placeHolderY = this.hotModeY;
						this.randomDirection = (int)(Math.random() * 4);
					}
				}
				else{
					this.placeHolderY = this.hotModeY;
					this.randomDirection = (int) (Math.random() * 4);
					this.computerAIHotModeSeeking();
				}
			}
			else{
				this.placeHolderY = this.hotModeY;
				this.randomDirection = (int) (Math.random() * 4);
				this.computerAIHotModeSeeking();
			}
		}
		
		else {
			int hotModeNext3 = this.placeHolderY - 1;
			
			if (hotModeNext3 >= 0){
				Points hotNext3 = this.gameBoard[hotModeNext3][this.hotModeX];
				
				//special case
				if(!hotNext3.isEmpty() && hotNext3.hasbeenAttacked()){
					if (!hotNext3.getShip().Destroyed()){
					boolean check = false;
					for (int i = hotModeNext3 - 1; i >= 0; i--){
						Points trackNext3 = this.gameBoard[i][this.hotModeX];
						
						if (trackNext3.hasbeenAttacked() && trackNext3.isEmpty()){
							this.placeHolderY = this.hotModeY;
							this.randomDirection = (int) (Math.random() * 4);
							check = true;
							this.computerAIHotModeSeeking();
							i = 0;
						}
						else if(!trackNext3.hasbeenAttacked()){
							check = true;
							this.targetLocation(this.hotModeX, i);
							
							if (!trackNext3.isEmpty()){
								this.placeHolderY = i;
							}
							else{
								this.placeHolderY = this.hotModeY;
								this.randomDirection = (int) (Math.random() * 4);
							}
							i = 0;
						}
					}
					if (!check){
						this.placeHolderY = this.hotModeY;
						this.randomDirection = (int) (Math.random() * 4);
						this.computerAIHotModeSeeking();
					}
				}
					
				else{
						this.placeHolderY = this.hotModeY;
						this.randomDirection = (int) (Math.random() * 4);
						this.computerAIHotModeSeeking();
				}
				}

				else if (!hotNext3.hasbeenAttacked()){
					this.targetLocation(this.hotModeX, hotModeNext3);
					
					if(!hotNext3.isEmpty()){
						this.placeHolderY = hotModeNext3;
					}
					else{
						this.placeHolderY = this.hotModeY;
						this.randomDirection = (int)(Math.random() * 4);
					}
				}
				else{
					this.placeHolderY = this.hotModeY;
					this.randomDirection = (int) (Math.random() * 4);
					this.computerAIHotModeSeeking();
				}
			}
			else{
				this.placeHolderY = this.hotModeY;
				this.randomDirection = (int) (Math.random() * 4);
				this.computerAIHotModeSeeking();
			}
		}
	}
	
}