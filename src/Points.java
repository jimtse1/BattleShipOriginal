
/**
 * 
 * A unit for the Board. Each point represents a specified coordinate. The Points class also tracks
 * whether the coordinate has already been targeted by the user. Additionally, the points class
 * checks whether the coordinates hides a certain ship. 
 * 
 * NOTE: The x coordinate represents the Column Value of the board. Additionally, the y coordinate
 * represents the Row Value of the board. It is zero based.
 * 
 * NOTE: the Ship Index represents what index the point is in for the specific ship. In other words,
 * it indicates what exact part of the ship does the point hold (1 is leftmost or topmost, the last
 * index is the rightmost or bottommost). If a point does not contain a ship, the shipIndex is -1
 * 
 * 
 */
public class Points {
	
	private int x;
	private int y;
	private boolean isTargeted;
	private int shipIndex;
	private Ship partOfShip;
	private boolean shipHasSunk;
	
	public Points(int xCoordinate, int yCoordinate, 
			boolean targeted, int shipIndexValue, Ship myShip){
		this.x = xCoordinate;
		this.y = yCoordinate;
		this.isTargeted = targeted;
		this.shipIndex = shipIndexValue;
		this.partOfShip = myShip;
		this.shipHasSunk = false;
	}
	
	/**
	 * Returns whether the Point is currently empty or not. In other words, the method checks 
	 * whether the coordinate hides a boat or not.
	 * @return whether the ship holds a boat or not.
	 */
	public boolean isEmpty(){
		return (this.shipIndex == -1 && this.partOfShip == null);
	}
	
	public int getXCoordinate() {
		int resultXCoordinate = this.x;
		return resultXCoordinate;
	}
	
	public int getYCoordinate() {
		int resultYCoordinate = this.y;
		return resultYCoordinate;
	}
	
	public int getShipIndex(){
		int shipIndexValue = this.shipIndex;
		return shipIndexValue;
	}
	
	public boolean hasbeenAttacked(){
		boolean resultTargeted = this.isTargeted;
		return resultTargeted;
	}
	
	/**
	 * Returns the ship that the point is a part of
	 * NOTE: MAY BE NULL IF IT DOES NOT CONTAIN A SHIP
	 * @return the ship the point is a part of
	 */
	public Ship getShip(){
		return this.partOfShip;
	}
	
	/**
	 * Sets the ship that the point is a part of
	 */
	public void setShip(Ship newShip){
		this.partOfShip = newShip;
	}
	
	/**
	 * Set new coordinates for the point
	 * @param newXCoordinate the new x coordinate of the point
	 * @param newYCoordinate the new y coordinate of the point
	 */
	public void setCoordinates(int newXCoordinate, int newYCoordinate){
		this.x = newXCoordinate;
		this.y = newYCoordinate;
	}
	
	/**
	 * Set the Ship Index of the point
	 * @param shipIndexValue new ship index of the point
	 */
	public void setShipIndex(int shipIndexValue){
		this.shipIndex = shipIndexValue;
	}
	
	/**
	 * Target the point. The point as a result should now be indicated as targeted.
	 */
	public void Target(){
		this.isTargeted = true;
	}
	
	/**
	 * Return whether the ship the point contains has sunk yet or not
	 * @return whether the ship the point contains has sunk yet or not
	 */
	public boolean hasShipSunk(){
		return this.shipHasSunk;
	}
	
	/**
	 * Sink the ship. The points as a result should show that the ship has been sunk.
	 */
	public void sinkShip(){
		this.shipHasSunk = true;
	}
	
}