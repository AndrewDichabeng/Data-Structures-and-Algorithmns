/**
 * The SetInterface interface module implemented by the LinkedSet class.
 * @author  :   Andrew Dichabeng
 * @version :   1.0
 */

public interface SetInterface <T> {

    /**
     * Method to add an element to a set.
     * @param newEntry  -   The object to be added as a new entry.
     * @return  -   Returns true if the element is successfully added to the set.
     */
    boolean add(T newEntry);

    /**
     * Method to remove a specific element from a set.
     * @param anEntry   -   The element to be removed.
     * @return  -   Returns true if the element was successfully removed from the set.
     */
    boolean remove(T anEntry);

    /**
     * Method to remove an element from a set.
     * @return  -   Return the first element removed from the set.
     */
    T remove();

    /**
     * Method to remove all the elements in the set.
     */
    void clear();

    /**
     * Method to check if the set contains a specific element.
     * @param anEntry   -   The element to be checked if it is in the list.
     * @return  -   Returns true if the set contains the specific element.
     */
    boolean contains(T anEntry);

    /**
     * Method to get the number of elements in the set.
     * @return  -   Returns the integer value for the number of elements in the set.
     */
    int getCurrentSize();

    /**
     * Method to check if the set is empty or not.
     * @return  -   Returns true if the set is empty (has no elements).
     */
    boolean isEmpty();

    /**
     * Method to check if the set has reached it's maximum capacity.
     * @return  -   Returns true if the set has reached it's maximum capacity.
     */
    boolean isFull();

    /**
     * Method to store the elements in the LinkedSet into an array.
     * @return  -   Return the final array containing the elements.
     */
    T[] toArray();

    /**
     * Method to create a union of two sets.
     * @param anotherSet    -   The other set be joined with the current set.
     * @return  -   Returns the final union of the two sets.
     */
    SetInterface <T> union(SetInterface <T> anotherSet);

    /**
     * Method to produce the intersection of the two sets.
     * @param anotherSet    -   The other set to be part of the intersection.
     * @return  -   Return the intersection of the two sets.
     */
    SetInterface <T> intersection(SetInterface <T> anotherSet);

    /**
     * Method to produce the difference between two sets.
     * @param anotherSet    -   The other set to be compared to this set.
     * @return  -   Returns the difference between the two sets.
     */
    SetInterface <T> difference(SetInterface <T> anotherSet);
}