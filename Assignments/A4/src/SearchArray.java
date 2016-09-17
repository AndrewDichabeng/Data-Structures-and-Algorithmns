/**
 * Finds an object in a 2D array where data in each row and in each column is sorted in increasing order.
 * @author  :   Andrew Dichabeng
 * @version :   1.0
 */

public class SearchArray <T extends Comparable<? super T>> {

    /**
     * Method to search a 2-dimensional array using linear search until element is found.

     */
	public static <T extends Comparable<? super T>> boolean bruteForceSearch(T[][] data, T desiredItem) {

		int row = data.length;
		int col = data[0].length;
		
		for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (data[i][j].compareTo(desiredItem) == 0) {
                    return true;
                }
            }
        }
		return false;
	}

    /**
     * Method that searches a 2-dimensional array using Binary search recursively until the desired element is found.
     * @param data  -   A 2-dimensional array.
     * @param desiredItem   -   The desired element to be searched for.
     * @return  -   Returns true if the desired element is found.
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends Comparable <? super T>> boolean search(T[][] data, T desiredItem) {

		int row = data.length - 1;
		int col = data[0].length - 1;
		
		// base case if its a 1x1 array
		if (row == 0 && col == 0) {
			if (data[0][0].compareTo(desiredItem) == 0) {
                return true;
            }
			return false;
		}

		int newRow = row;
		int newCol = col;

		if (newRow > 0) {
            newRow /= 2;
        }

		if (newCol > 0) {
            newCol /= 2;
        }

		// compares the middle element to the desiredItem
		if (data[newRow][newCol].compareTo(desiredItem) == 0) {
            return true;
        }

		// if the middle element is less than the desireItem, then check the upper left, lower left and top right sub-array.
		if (desiredItem.compareTo(data[newRow][newCol]) < 0) {
			
			boolean found;
			
			// top left
			if (newRow > 0 && newCol > 0) {
				
			    Comparable[][] halfData = new Comparable[newRow][newCol];
			
			    for (int i = 0; i < newRow; i++) {
                    for (int j = 0; j < newCol; j++) {
                        halfData[i][j] = data[i][j];
                    }
                }

                found = search(halfData, desiredItem);  // call method recursively
			     
                if (found) {
                    return found;
                }
			}
			
			// bottom left
			if (newCol > 0) {

				Comparable[][] halfData = new Comparable[row - newRow + 1][newCol];
				
			    for (int i = 0; i < row - newRow + 1; i++)	{
                    for (int j = 0; j < newCol; j++) {
                        halfData[i][j] = data[i + newRow][j];
                    }
                }

                found = search(halfData, desiredItem);     // call method recursively
			     
                if (found) {
                    return found;
                }
			}

			// top right
			if (newRow > 0) {

			    Comparable[][] halfData = new Comparable[newRow][col - newCol + 1];
							
				for (int i = 0; i < newRow; i++) {
                    for (int j = 0; j < col - newCol + 1; j++) {
                        halfData[i][j] = data[i][j + newCol];
                    }
                }
				return search(halfData, desiredItem);      // call method recursively
			}
		}

		// if the middle element is greater than desiredItem, than check the upper right, lower left and lower right sub-array if it exists.
		if (desiredItem.compareTo(data[newRow][newCol]) > 0) {
						
			boolean found;

			if (col - newCol > 0) {

			    // top right sub-array
			    Comparable[][] halfData = new Comparable[newRow + 1][col - newCol];

			    for (int i = 0; i < newRow + 1; i++) {
                    for (int j = 0; j < col - newCol; j++) {
                        halfData[i][j] = data[i][j + newCol + 1];
                    }
                }

			    found = search(halfData, desiredItem);      // call method recursively
			
			    if (found) {
                    return found;
                }
			}
			
			
			if (row - newRow > 0) {

			    // bottom left sub-array
			    Comparable[][] halfData = new Comparable[row - newRow][newCol + 1];
			
			    for (int i = 0; i < row - newRow; i++) {
                    for (int j = 0; j < newCol + 1; j++) {
                        halfData[i][j] = data[i + newRow + 1][j];
                    }
                }

			    found = search(halfData, desiredItem);         // call method recursively
			
			    if (found) {
                    return found;
                }
			}

			if (row - newRow > 0 && col - newCol > 0) {

			    // bottom right sub-array
			    Comparable[][] halfData = new Comparable[row - newRow][col - newCol];
			
			    for (int i = 0; i < row - newRow; i++) {
                    for (int j = 0; j < col - newCol; j++) {
                        halfData[i][j] = data[i + newRow + 1][j + newCol + 1];
                    }
                }
			    return search(halfData, desiredItem);      //call method recursively
			}
		}
		return false;
	}
}
