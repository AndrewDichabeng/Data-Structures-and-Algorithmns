/**
 * @author  :   Andrew Dichabeng
 * file     :   Change.java
 * description  :   A class to convert a cent amount and compute the possible combinations that the amount can be give
 *                  as change in the following denominations :
 *                  {PENNY = $ 0.01, NICKEL = $ 0.05, DIME = $ 0.10, QUARTER = $ 0.25 }
 */
public class Change {

    // Constants
    private static final int PENNY_VALUE = 1;
    private static final int NICKEL_VALUE = 5;
    private static final int DIME_VALUE = 10;
    private static final int QUARTER_VALUE = 25;
    private static final int[] COIN_ARRAY = {PENNY_VALUE, NICKEL_VALUE, DIME_VALUE, QUARTER_VALUE};
    private static final int DEFAULT_INDEX = 0;     /* NOTE :   Do not change the default index */

    // Instance Variables
    private int amount;
    private int counter;

    /**
     * The Change Object constructor.
     * @param amount    -   The amount to be changed.
     */
    public Change(int amount) {
        this.setAmount(amount);
        this.setCounter(0);
        this.calculateAmount(this.getAmount(), COIN_ARRAY, DEFAULT_INDEX);  /* the call to the recursive method. */
    }

    /**
     * Method to get the amount of the Change Object.
     * @return  -   Returns the amount instance variable of the Change Object.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Method to get the counter of the Change Object.
     * @return  -   Returns the counter instance variable of the Change Object.
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Method to set the amount of the Change Object.
     * @param amount -  The new value of the amount to be set.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Method to set the counter of the Change Object.
     * @param counter - The new value for the counter to be set.
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * A recursive method that finds all the possible combinations that the amount can be changed into using the
     * coin denominations above { $0.01, $0.05, $0.10, $0.25}.
     * @param amount    -   The amount to be changed.
     * @param coinArray -   The coin array containing the coin denominations.
     * @param index -   The index to start with in the coin array. {default = 0}
     */
    private void calculateAmount(int amount, int[] coinArray , int index) {

        int remainder;

        if (amount == 0) {  // base case
            this.counter++;
            return;
        }

        for (int i = index; i < coinArray.length; i++ ) {
            remainder = amount - coinArray[i];
            if (remainder >= 0) {
                this.calculateAmount(remainder, coinArray, i);  /* recursive call */
            }
        }
    }

}
