import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.Set;
import java.util.TreeMap;

/**
 * Reads a text document and counts the number of occurrences of words and displays them in html format with different
 * sizes based on the frequency of the words.
 * @author  :   Andrew Dichabeng
 * @version :   1.0
 */

public class HTMLWriter {

    /* constant*/
	private static final int NUM_OF_TAGS = 5;

    /* instance variables */
	private TreeMap<String, Integer> alphaSortedMap;
	private int smallestTagLimit;
    private int smallTagLimit;
    private int mediumTagLimit;
    private int largeTagLimit;

    /* constructor */
	public HTMLWriter() {

		DataMap dataMap = new DataMap();
		this.alphaSortedMap = dataMap.getSortedByKeyMap();
		this.smallestTagLimit = dataMap.getSortedByValueMap().pollFirstEntry().getValue() / NUM_OF_TAGS;
		this.smallTagLimit = this.smallestTagLimit * 2;
		this.mediumTagLimit = this.smallestTagLimit * 3;
		this.largeTagLimit = this.smallestTagLimit * 4;
	}

    /**
     * Writes to a html file the data from the TreeMap and finds appropriate font sizes depending on the frequency
     * of the words.
     */
	public void writeToFile() {

		int freq;
		int number;
		
		Set<String> setKey = this.alphaSortedMap.keySet();
		
		String [] setKeyArray = (String[]) Array.newInstance(setKey.toArray()[0].getClass(), setKey.size());
		
		for (int i = 0; i < setKey.size(); i++) {
            setKeyArray[i] = (String) setKey.toArray()[i];
        }

		try {

			FileWriter fStream = new FileWriter("htmlOutput.html");
			BufferedWriter out = new BufferedWriter(fStream);

			// write html headers and body tags
			out.write("<!DOCTYPE html>\n<html>\n<head>\n<meta charset=\"UTF-8\">\n<title>html_output</title>\n</head>\n<body>");
			  
			// write CSS style tags
			out.write("<style type=\"text/css\">\n.smallestTag {font-size: xx-small;}\n.smallTag {font-size: small;}\n.mediumTag {font-size: medium;}\n.largeTag {font-size: large;}\n.largestTag {font-size: xx-large;}\n</style>");
			  
			number = 0;
			
			// for each word find corresponding tag and write to file
			for (String str: setKeyArray) {

				number ++;
				freq = this.alphaSortedMap.get(str);
				
				if (freq <= this.smallestTagLimit) {
                    out.write("<span class=\"smallestTag\"> " + str + " </span>");
                }

				else if (freq > this.smallestTagLimit && freq <= this.smallTagLimit) {
                    out.write("<span class=\"smallTag\"> " + str + " </span>");
                }

				else if (freq > this.smallTagLimit && freq <= this.mediumTagLimit) {
                    out.write("<span class=\"mediumTag\"> " + str + " </span>");
                }

				else if (freq > this.mediumTagLimit && freq <= this.largeTagLimit) {
                    out.write("<span class=\"largeTag\"> " + str + " </span>");
                }

				else {
                    out.write("<span class=\"largestTag\"> " + str + " </span>");
				}

				if (number == 54) {
					number = 0;
					out.write("\n");
				}
			}
			out.write("</body>\n</html>");      // write closing html tags and body
			out.close();                        // close resources
		}
		catch (Exception e){
            System.out.println("Error cannot create file.");
		}
	}

    /**
     * The main method.
     * @param args  -   The command line arguments.
     */
	public static void main(String[] args) {

		HTMLWriter htmlWriter = new HTMLWriter();
		htmlWriter.writeToFile();
		System.out.println("The file is named htmlOutput.html");
	}
	
}
