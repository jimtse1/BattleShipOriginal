public class Scores implements Comparable<Scores>{
	
	private int score;
	private String name;
	
	public Scores(int myScore, String myName){
		this.score = myScore;
		this.name = myName;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public String getName(){
		return this.name;
	}

	@Override
	public int compareTo(Scores otherScore) {
		int score2 = otherScore.getScore();
		if(this.score > score2){
			return 1;
		}
		else if(this.score == score2){
			return 0;
		}
		else{
			return -1;
		}
	}
	
}