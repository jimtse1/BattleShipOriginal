
import java.util.Comparator;

/**
 * 
 * The Comparator to sort scores in the arrayList of scores, retrieved by the file.
 *
 */
public class ScoresComparator implements Comparator<Scores>{

	@Override
	public int compare(Scores score1, Scores score2) {
		return score1.compareTo(score2);
	}
}