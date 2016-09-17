import java.io.*;
import java.util.*;

/**
 * Reads two text documents, one is the stop words (common words)
 * and the other is the interested document. Creates and sorts a map
 * with words from the interested document according to the number of
 * occurrences of each word.
 * @author  :   Andrew Dichabeng
 * @version :   1.0
 */

public class DataMap {

    /* instance variables */
	private Set<String> stopWords;
	private TreeMap<String, Integer> sortedValueMap;
    private TreeMap<String, Integer> sortedKeyMap;

    /* constructor */
	public DataMap() {

		this.stopWords = new HashSet<>();
		this.sortedKeyMap = new TreeMap<>();
		this.sortedValueMap = new TreeMap<>();
		this.makeSortedByValueMap();
	}

    /**
     * Method to read in all of the stop words from a file and stores them in a set.
     */
	@SuppressWarnings("resource")
	private void stopWords() {
		
		try {

			Scanner sc = new Scanner(System.in);
			System.out.println("Enter stopWords file name: ");

			File fileName = new File(sc.next());
			Scanner input = new Scanner(fileName);      // for file I/O.

			while(input.hasNext()) {

				String stopWord = input.next().toLowerCase();
				if (!this.stopWords.contains(stopWord)) {
                    this.stopWords.add(stopWord);
				}
		   }
		}
		catch (FileNotFoundException e) {
			System.out.println("Stop words file not found");
			System.exit(0);
		} 
	}

    /**
     * Method that creates a TreeMap object with (key, value) where the key is a string representing
     * the individual words in the document and the value is an integer representing the amount of
     * times that a particular word appears in the document.
     * @return  -   Returns the TreeMap object that is sorted alphabetically.
     */
	@SuppressWarnings("resource")
	private TreeMap<String, Integer> makeSortedByKeyMap() {

		this.stopWords();
		
		try {

			Scanner sc = new Scanner(System.in);
			System.out.println("\nEnter document file name: ");

		    File fileName = new File(sc.next());
			Scanner input = new Scanner(fileName).useDelimiter("[ ,.:;\t\n\r()1-9<>/?{}|~`!@#^&\"���]");
			
			// adds the lowercase version of the document words to the map as long as they are
			// not stop words and have a length greater than 2.
			
			while(input.hasNext()) {

				String word = input.next().toLowerCase();

			    if (!this.stopWords.contains(word) && word.length() > 2) {
						
			    	// if map contains the key then update the value
					if (this.sortedKeyMap.containsKey(word)) {

						int freq = this.sortedKeyMap.get(word);
						freq ++;
						this.sortedKeyMap.put(word, freq);
					}
						
					// otherwise put the key in the map and set value to 1
					else {
                        this.sortedKeyMap.put(word, 1);
					}
                }
		   }
		   sc.close();
		   input.close();       // close I/O resources.
		}
		catch (FileNotFoundException e) {
			System.out.println("Document file not found");
			System.exit(0);
		}
		return this.sortedKeyMap;
	}

    /**
     * Method to compare the TreeMap objects by value and sort them.
     * @param treeMaps  -   The TreeMap object to be sorted.
     * @return  -   Returns the TreeMap that is sorted in descending order by value.
     */
	private static <K, V extends Comparable<V>> TreeMap<K, V> sortByTreeValue(final TreeMap<K, V> treeMaps) {

	    Comparator<K> valueComparator =  new Comparator<K>() {

	        public int compare(K key1, K key2) {

	            int compareVal = treeMaps.get(key2).compareTo(treeMaps.get(key1));

	            if (compareVal == 0) {      // if the values are the same, then return 1 to ensure keys with same values still appear in the map
                    return 1;
	            }
	            else {
                    return compareVal;      // return the value instead.
	            }
	        }
	    };

	    TreeMap<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
	    sortedByValues.putAll(treeMaps);
	    return sortedByValues;
	}

    /**
     * Method that creates a treeMap object with (key, value) where the key is a string representing
     * the individual words in the document and the value is an integer representing the amount of
     * times that particular word appears in the document.
     */
	private void makeSortedByValueMap() {
		this.sortedValueMap = sortByTreeValue(this.makeSortedByKeyMap());
	}

    /**
     * Accessor method for the Value Map sorted by values.
     * @return  -   Returns the sorted value map.
     */
	public TreeMap<String, Integer> getSortedByValueMap() {
        return this.sortedValueMap;
	}

    /**
     * Accessor method for the Key Map sorted by key.
     * @return  -   Returns the sorted key map.
     */
	public TreeMap<String, Integer> getSortedByKeyMap() {
		return this.sortedKeyMap;
	}
}
