/**
 * The AircraftCarrier Ship is a ship of length five. The name of the AircraftCarrier is 
 * "Aircraft-Carrier"
 *
 */
public class AircraftCarrier implements Ship {
	
	private int length;
	private String name;
	private boolean isRotated;
	private boolean destroyedMessage;
	private Points[] pointsInShip;

	public AircraftCarrier(boolean rotate){
		this.length = 5;
		this.isRotated = rotate;
		this.name = "Aircraft-Carrier";
		this.pointsInShip = new Points[5];
		this.destroyedMessage = false;
	}
	
	public int getLength(){
		int aircraftLength = this.length;
		return aircraftLength;
	}

	public String getName(){
		String aircraftName = this.name;
		return aircraftName;
	}
	
	public boolean isItRotated(){
		boolean aircraftRotated = this.isRotated;
		return aircraftRotated;
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
		Points[] aircraftPoints = this.pointsInShip;
		return aircraftPoints;
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
			System.out.println("Have Destroyed Aircraft Carrier");
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
