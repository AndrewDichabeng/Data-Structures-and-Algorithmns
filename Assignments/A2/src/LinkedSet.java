import java.lang.reflect.Array;
import java.util.LinkedList;

/**
 * The LinkedSet class implementation.
 * @author :    Andrew Dichabeng
 * @version :   1.0
 */

public class LinkedSet<T> implements SetInterface <T> {

    private static final int MAX_SIZE = Integer.MAX_VALUE;      /* maximum size of the LinkedSet */

    // instance variable
    private LinkedList<T> set;

    // constructor
    public LinkedSet() {
        this.set = new LinkedList<>();
    }

    /**
     * Method to add an element to the LinkedSet.
     * @param newEntry  -   The specific element to be added to the LinkedSet.
     * @return  -   Returns true if the element was successfully added to the LinkedSet.
     */
    public boolean add(T newEntry) {
        if (!this.contains(newEntry)) {
            this.set.add(newEntry);
            return true;
        }
        return false;
    }

    /**
     * Method to remove an element from a LinkedSet and if the element is contained in the LinkedSet.
     * @param anEntry   -   The specific element to be removed from the LinkedSet.
     * @return  -   Returns the element that was removed from the LinkedSet.
     */
    public boolean remove(T anEntry) {
        if (this.contains(anEntry)) {
            this.set.remove(anEntry);
            return true;
        }
        return false;
    }

    /**
     * Method to remove the first element of the LinkedSet.
     * @return  -   Returns the element that was removed from the LinkedSet.
     */
    public T remove() {
        if (this.set == null) {
            return null;
        }
        return this.set.remove(0);
    }

    /**
     * Method to clear(remove all elements) the LinkedSet.
     */
    public void clear() {
        this.set.clear();
    }

    /**
     * Method to check if the LinkedSet contains a specific element.
     * @param anEntry   -   The specific element to be checked for in the LinkedSet.
     * @return  -   Returns true if the LinkedSet contains the specific element.
     */
    public boolean contains(T anEntry) {
        for (T e: this.set) {
            if (e.equals(anEntry)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to get the current size (number of elements) in the LinkedSet.
     * @return  -   Returns the size of the LinkedSet.
     */
    public int getCurrentSize() {
        return this.set.size();
    }

    /**
     * Method to check if the LinkedSet is empty or not.
     * @return  -   Returns true if the LinkedSet is empty.
     */
    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    /**
     * Method to check if the LinkedSet has reached it's maximum capacity.
     * @return  -   Returns true if the LinkedSet has reached it's maximum capacity.
     */
    public boolean isFull() {
        return this.getCurrentSize() == MAX_SIZE;
    }

    /**
     * Method to store the elements in the LinkedSet into an array.
     * @return  -   Return the final array containing the elements.
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        if (this.getCurrentSize() == 0) {
            return null;
        }
        T[] tArray = (T[]) Array.newInstance(this.set.get(0).getClass(), this.getCurrentSize());
        for (int i = 0; i < this.getCurrentSize(); i++) {
            tArray[i] = this.set.get(i);
        }
        return tArray;
    }

    /**
     * Method to create a union of two sets.
     * @param anotherSet    -   The other set be joined with the current set.
     * @return  -   Returns the final union of the two sets.
     */
    public SetInterface<T> union(SetInterface<T> anotherSet) {
        SetInterface<T> union = new LinkedSet<>();
        for (T element: this.toArray()) {
            union.add(element);
        }
        for (T element: anotherSet.toArray()) {
            union.add(element);
        }
        return union;
    }

    /**
     * Method to produce the intersection of the two sets.
     * @param anotherSet    -   The other set to be part of the intersection.
     * @return  -   Return the intersection of the two sets.
     */
    public SetInterface<T> intersection(SetInterface<T> anotherSet) {
        SetInterface<T> intersect = new LinkedSet<>();
        for (T element: this.toArray()) {
            if (anotherSet.contains(element)) {
                intersect.add(element);
            }
        }
        return intersect;
    }

    /**
     * Method to produce the difference between two sets.
     * @param anotherSet    -   The other set to be compared to this set.
     * @return  -   Returns the difference between the two sets.
     */
    public SetInterface<T> difference(SetInterface<T> anotherSet) {
        SetInterface<T> intersect = this.intersection(anotherSet);
        SetInterface<T> difference = new LinkedSet<>();
        for (T element: intersect.toArray()) {
            if (!anotherSet.contains(element)) {
                difference.add(element);
            }
        }
        return difference;
    }

}