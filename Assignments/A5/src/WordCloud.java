import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Reads a text document and counts the number of occurrences of words and determines the ten most frequently used
 * words in the document.
 * @author  :   Andrew Dichabeng
 * @version :   1.0
 */

public class WordCloud {

    /**
     * The main method.
     * @param args  -   The command line arguments.
     */
	public static void main(String[] args) {

        DataMap dataMap = new DataMap();
        TreeMap<String, Integer> treeMap = dataMap.getSortedByValueMap();
		Set<Entry<String, Integer>> setMap = treeMap.entrySet();
		Object[] setMapArray = setMap.toArray();

		System.out.println("\nThe top 10 most frequent words in the document are: \n");
		for (int i = 0; i < 10;i++) {
            System.out.println(setMapArray[i]);
        }
	}
}
