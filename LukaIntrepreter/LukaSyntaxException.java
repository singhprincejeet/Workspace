Author : Princejeet Singh Sandhu

/**
 * An object of the class <code>LukaSyntaxException</code> is thrown by the
 * methods of a <code>Interpreter</code> to indicate the illegal situations where the
 * syntax of Luka is not followed
 */
public class LukaSyntaxException extends RuntimeException {

    public LukaSyntaxException() {
        super();
    }

    public LukaSyntaxException(String message) {
        super(message);
    }
}
