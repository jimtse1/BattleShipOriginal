/**
 * The Destroyer ship is a ship of length three. The name of Destroyer is "Destroyer"
 *
 */
public class Destroyer implements Ship {
	
	private int length;
	public String name;
	private boolean isRotated;
	private boolean destroyedMessage;
	private Points[] pointsInShip;
	
	public Destroyer(boolean rotate){
		this.name = "Destroyer";
		this.length = 3;
		this.isRotated = rotate;
		this.pointsInShip = new Points[3];
		this.destroyedMessage = false;
	}
	
	public int getLength(){
		int DestroyerLength = this.length;
		return DestroyerLength;
	}

	public String getName(){
		String DestroyerName = this.name;
		return DestroyerName;
	}
	
	public boolean isItRotated(){
		boolean DestroyerRotated = this.isRotated;
		return DestroyerRotated;
	}
	
	public void rotate(){
		this.isRotated = !this.isRotated;
	}

	public Points[] getPoints(){
		Points[] DestroyerPoints = this.pointsInShip;
		return DestroyerPoints;
	}
	
	public void reset(){
		for (int i = 0; i < this.pointsInShip.length; i++){
			this.pointsInShip[i] = null;
		}
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
			System.out.println("Have Destroyed Destroyer");
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
