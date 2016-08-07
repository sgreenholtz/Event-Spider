package service;

/**
 * Exception class when the given tag was not found
 * @author Sebastian Greenholtz
 */
public class TagNotFoundExecption extends Exception {

    public TagNotFoundExecption(String message) {
        super(message);
    }
}
