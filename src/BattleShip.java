/**
 * The BattleShip Ship is a ship of length five. The name of the BattleShip is "BattleShip"
 *
 */
public class BattleShip implements Ship {
	
	private int length;
	private String name;
	private boolean isRotated;
	private boolean destroyedMessage;
	private Points[] pointsInShip;

	public BattleShip(boolean rotate){
		this.length = 4;
		this.isRotated = rotate;
		this.name = "BattleShip";
		pointsInShip = new Points[4];
		this.destroyedMessage = false;
	}
	
	public int getLength(){
		int BattleShipLength = this.length;
		return BattleShipLength;
	}

	public String getName(){
		String BattleShipName = this.name;
		return BattleShipName;
	}
	
	public boolean isItRotated(){
		boolean BattleShipRotated = this.isRotated;
		return BattleShipRotated;
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
		Points[] BattleShipPoints = this.pointsInShip;
		return BattleShipPoints;
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
			System.out.println("Have Destroyed BattleShip");
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
