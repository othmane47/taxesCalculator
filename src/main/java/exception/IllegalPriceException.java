package exception;

/**
 * The type Illegal price exception.
 */
public class IllegalPriceException extends Exception {
    /**
     * Instantiates a new Illegal price exception.
     */
    public IllegalPriceException() {
        super("Price should be higher than 0€");
    }
}
