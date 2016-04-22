/**
 * The PatrolBoat Ship is a ship of length two. The name of the ship is "Patrol Boat"
 *
 */
public class PatrolBoat implements Ship {
	
	private int length;
	private String name;
	private boolean isRotated;
	private boolean destroyedMessage;
	private Points[] pointsInShip;
	
	public PatrolBoat(boolean rotate){
		this.length = 2;
		this.name = "PatrolBoat";
		this.isRotated = rotate;
		this.pointsInShip = new Points[2];
		this.destroyedMessage = false;
	}

	public int getLength(){
		int PatrolBoatLength = this.length;
		return PatrolBoatLength;
	}

	public String getName(){
		String PatrolBoatName = this.name;
		return PatrolBoatName;
	}
	
	public boolean isItRotated(){
		boolean PatrolBoatRotated = this.isRotated;
		return PatrolBoatRotated;
	}
	
	public void rotate(){
		this.isRotated = !this.isRotated;
	}
	
	public void reset(){
		for (int i = 0; i < this.pointsInShip.length; i++){
			this.pointsInShip[i] = null;
		}
	}

	public Points[] getPoints(){
		Points[] PatrolBoatPoints = this.pointsInShip;
		return PatrolBoatPoints;
	}
	
	public boolean Destroyed(){
		for (Points checkPoints : this.pointsInShip){
			if (!checkPoints.hasbeenAttacked()){
				return false;
			}
		}
		return true;
	}
	
	public void DestroyedMessage(){
		if (this.Destroyed()){
			System.out.println("Have Destroyed PatrolBoat");
		}
	}
	
	public boolean getDestroyedMessage(){
		boolean message = this.destroyedMessage;
		return message;
	}
	
	public void hasSunk(){
		this.destroyedMessage = true;
	}
	
	public boolean same (Ship otherShip){
		return this.name.equals(otherShip.getName());
	}
}
