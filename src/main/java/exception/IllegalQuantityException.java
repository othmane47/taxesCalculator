package exception;

/**
 * The type Illegal quantity exception.
 */
public class IllegalQuantityException extends Exception {
    /**
     * Instantiates a new Illegal quantity exception.
     */
    public IllegalQuantityException() {
        super("Quantity should be higher than 1");
    }

}
