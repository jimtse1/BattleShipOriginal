import java.util.Set;
import java.util.TreeSet;

/**
 * Creates a Board with the PatrolBoat, Destroyer, BattleShip, and AircraftCarrier randomly
 * placed. This is the AI-placed board that the user plays with.
 *
 */
public class RandomBoard extends Board {
	
	public RandomBoard(int y, int x, String name, boolean step){
		
		super(y, x, 4, name, step);
		
		Ship[] myShipArray = this.getArrayShips();
		int width = this.getLength();
		int height = this.getHeight();
		Points[][] gameBoard = this.getGameBoard();
		
		boolean RotatePatrolBoat = RandomBoard.Converter((int) (Math.random() * 2));
		boolean RotateDestroyer = RandomBoard.Converter((int) (Math.random() * 2));
		boolean RotateBattleship = RandomBoard.Converter((int) (Math.random() * 2));
		boolean RotateAircraftCarrier = RandomBoard.Converter((int) (Math.random() * 2));

		boolean [] arrIsRotate = 
			{RotatePatrolBoat, RotateDestroyer, RotateBattleship, RotateAircraftCarrier};
		
		AircraftCarrier randomAircraftCarrier = new AircraftCarrier(arrIsRotate[3]);
		BattleShip randomBattleShip = new BattleShip(arrIsRotate[2]);
		Destroyer randomDestroyer = new Destroyer(arrIsRotate[1]);
		PatrolBoat randomPatrolBoat = new PatrolBoat(arrIsRotate[0]);
		
		myShipArray[0] = randomPatrolBoat;
		myShipArray[1] = randomDestroyer;
		myShipArray[2] = randomBattleShip;
		myShipArray[3] = randomAircraftCarrier;
		
		for (int length = 2; length < myShipArray.length + 2; length++){
			
			if (!arrIsRotate[length - 2]) {
				
				int counter = 0;
				boolean doesNotIntersect = false;
				
				while (!doesNotIntersect && counter <= 100000){
					int guessShipX = (int) (Math.random() * (width - length + 1));
					int guessShipY = (int) (Math.random() * (height));
					
					if (this.checkNotOverlapping(false, length, guessShipX, guessShipY)){
						doesNotIntersect = true;
						
					    for (int i = guessShipX; i < guessShipX + length; i++){
						    Points toCheck = gameBoard[guessShipY][i];
						    toCheck.setCoordinates(i, guessShipY);
						    toCheck.setShipIndex(i - guessShipX);
						    toCheck.setShip(myShipArray[length - 2]);
						    Ship thisShip = myShipArray[length - 2];
						    thisShip.getPoints()[i - guessShipX] = toCheck;
					    }
					}
					counter++;
				}
				if (counter > 100000){
					System.out.println("Try again!");
				}
			}
			
			else {
				
				int counter = 0;
				boolean doesNotIntersect = false;
				
				while (!doesNotIntersect && counter < 100000){
					int guessShipX = (int) (Math.random() * (width));
					int guessShipY = (int) (Math.random() * (height - length + 1));
					
					if (this.checkNotOverlapping(true, length, guessShipX, guessShipY)){
						doesNotIntersect = true;
						
					    for (int i = guessShipY; i < guessShipY + length; i++){
						    Points toCheck = gameBoard[i][guessShipX];
						    toCheck.setCoordinates(guessShipX, i);
						    toCheck.setShipIndex(i - guessShipY);
						    toCheck.setShip(myShipArray[length - 2]);
						    Ship thisShip = myShipArray[length - 2];
						    thisShip.getPoints()[i - guessShipY] = toCheck;						
					    }
					}
					counter ++;
				}
				if (counter > 100000){
					System.out.println("Try again!");
				}
			}
		}
	}
					    
	/**
	 * Static method to convert a number computed from the random generator into a boolean value
	 * @param number the number from 0 to 1
	 * @return the boolean value associated with the number
	 */
	public static boolean Converter(int number){
		return number == 1;
	}
	
	/**
	 * Static method to convert a length to a name
	 * @param length the length of the ship
	 * @return name of the ship
	 */
	public static String ConverterShip(int length){
		if (length == 2){
			return "PatrolBoat";
		}
		else if (length == 3){
			return "Destroyer";
		}
		else if (length == 4){
			return "BattleShip";
		}
		else{
			return "Aircraft-Carrier";
		}
	}
}