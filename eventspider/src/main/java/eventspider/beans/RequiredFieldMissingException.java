package eventspider.beans;

/**
 * Exception class for when a required field is null
 * @author Sebastian Greenholtz
 */
public class RequiredFieldMissingException extends Exception {

    public RequiredFieldMissingException(String message) {
        super(message);
    }
}
