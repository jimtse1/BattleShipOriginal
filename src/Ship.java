/** The interface for the objects to be placed on the Board
 * 
 * A ship must be able to rotate and must be able to be destroyed. 
 * Additionally, a ship must be able to return the coordinates in which it is hidden in the board, 
 * its length, its name, and whether it is rotated horizontally or vertically.
 * 
 */

public interface Ship {
	
	/**
	 * Rotate the ship from horizontal to vertical or vertical to horizontal. Changes the boolean
	 * value of whether the ship is rotated.
	 * NOTE: If the ship isRotated (true), then the ship is oriented vertical. Otherwise, if the 
	 * ship is not isRotated (false), then the ship is oriented horizontally
	 */
	public void rotate();
	
	/**
	 * Returns whether the ship has been destroyed or not on the board
	 * @return ship has  been destroyed or not
	 */
	public boolean Destroyed();
	
	/**
	 * Prints a message if the ship is destroyed
	 */
	public void DestroyedMessage();
	
	/**
	 * Returns the points in which hide the ship in the board
	 * @return an array of points that hide the ship
	 */
	public Points[] getPoints();

	/**
	 * Returns the length of the ship
	 * @return the length of the ship
	 */
	public int getLength();
	
	/**
	 * Returns the name of the ship
	 * @return the name of the ship
	 */
	public String getName();
	
	/**
	 * Returns whether the ship is rotated or not.
	 * NOTE: If the ship isRotated (true), then the ship is oriented vertical. Otherwise, if the 
	 * ship is not isRotated (false), then the ship is oriented horizontally
	 * @return
	 */
	public boolean isItRotated();
	
	/**
	 * Resets the points contained in the ship
	 */
	public void reset();
	
	/**
	 * Return whether the ship has sunk for the first time or not. Purpose: to be able to 
	 * send a message that a ship has just been sunk.
	 */
	public boolean getDestroyedMessage();
	
	/**
	 * Indicates that a ship has just been sunk
	 */
	public void hasSunk();
	
	public boolean same(Ship otherShip);
}