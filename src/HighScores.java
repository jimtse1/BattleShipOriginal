import java.util.*;
import java.io.*;

public class HighScores {
	
	private static final String BATTLESHIP_HIGHSCORE_FILE = "scores";
	
	private ObjectOutputStream outputStream; 
	private ObjectInputStream inputStream;
	
	private ArrayList<Scores> arrayOfScores; //the array of scores sorted
	
	public HighScores(){
		arrayOfScores = new ArrayList<Scores>();
		outputStream = null;
		inputStream = null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Scores> getMyScores() 
			throws FileNotFoundException, IOException, ClassNotFoundException{
		
		//update the array based on the file
		try {
		this.inputStream = new ObjectInputStream(new FileInputStream(BATTLESHIP_HIGHSCORE_FILE));
		this.arrayOfScores = (ArrayList<Scores>) this.inputStream.readObject();
		}
		catch (FileNotFoundException e){
			System.out.println("File not found -- Try again m8");
		}
		catch (IOException e){
			System.out.println("Error: Try again m8");
		}
		catch(ClassNotFoundException e){
			System.out.println("Error: Try again m8");
		}
		finally{
			outputStream.flush();
			outputStream.close();
		}
		return this.arrayOfScores;
	}
	
	public void addScore(int score, String name) 
			throws FileNotFoundException, IOException{		
		//update the array based on the file
		try {
		this.inputStream = new ObjectInputStream(new FileInputStream(BATTLESHIP_HIGHSCORE_FILE));
		this.arrayOfScores = (ArrayList<Scores>) this.inputStream.readObject();
		}
		catch (FileNotFoundException e){
			System.out.println("File not found -- Try again m8");
		}
		catch (IOException e){
			System.out.println("Error: Try again m8");
		}
		catch(ClassNotFoundException e){
			System.out.println("Error: Try again m8");
		}
		finally{
			outputStream.flush();
			outputStream.close();
		}
		
		//add the score to the arrayList of scores
		this.arrayOfScores.add(new Scores(score, name));
		
		//update the file to add the new score
		try {
		this.outputStream = new ObjectOutputStream(new FileOutputStream(BATTLESHIP_HIGHSCORE_FILE));
		this.outputStream.writeObject(this.arrayOfScores);
		}
		catch (FileNotFoundException e){
			System.out.println("File not found -- Try again m8");
		}
		catch (IOException e){
			System.out.println("Error: Try again m8");
		}
		finally{
			outputStream.flush();
			outputStream.close();
		}
	}
	
	//Get The High Score
	public String getHighScore(){
		//Sort the array based on the high score
		ScoresComparator comparator = new ScoresComparator();
		Collections.sort(arrayOfScores, comparator);
		
		Scores highScore = this.arrayOfScores.get(0);
		return highScore.getName() + "---->" + highScore.getScore();
	}
	
	//Get A number of the first high scores
	public String getHighScores(int number){
		//Sort the array based on the high score
		ScoresComparator comparator = new ScoresComparator();
		Collections.sort(arrayOfScores, comparator);
		
		String toReturn= "";
		if (number > this.arrayOfScores.size()){
			System.out.println("Not enough high scores");
			return toReturn;
		}
		else{
			for (int i = 0; i < number; i++){
				Scores highScore = this.arrayOfScores.get(i);
				toReturn += "\n" + highScore.getName() + "------>" + highScore.getScore();
			}
		}
		return toReturn;
	}
}
