/**
 * The Node class implementation with a single reference to the next node.
 * @author :    Andrew Dichabeng
 * @version :   1.0
 */
public class Node<T> {

    /* instance variables */
    private T data;             /* data portion */
    private Node<T> next;       /* reference to the next node */

    /* constructors */
    Node(T dataPortion) {
        data = dataPortion;
        next = null;
    }

    Node(T dataPortion, Node<T> nextNode) {
        data = dataPortion;
        next = nextNode;
    }

    /**
     * Method to get the data portion of the Node.
     * @return  -   Returns the data element.
     */
    T getData() {
        return data;
    }

    /**
     * Method to get the reference to the next Node.
     * @return  -   Returns the next Node.
     */
    Node<T> getNext() {
        return next;
    }

    /**
     * Method to set the data portion to a new element.
     * @param dataPortion   -   The new data element to be added to the Node.
     */
    void setData(T dataPortion) {
        data = dataPortion;
    }

    /**
     * Method to set the reference to the next Node.
     * @param nextNode  -   The Node to be set as the next Node.
     */
    void setNext(Node<T> nextNode) {
        next = nextNode;
    }

}
