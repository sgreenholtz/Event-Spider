package eventspider.database;

/**
 * An exception to catch when the record was not added to the database for
 * a reason unspecified.
 * @author Sebastian Greenholtz
 */
public class RecordNotAddedException extends Exception {

    public RecordNotAddedException() {
        super("Record was not added to the database due to an error");
    }
}
