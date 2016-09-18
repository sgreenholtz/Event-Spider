package Service;

/**
 * Exception thrown if a URL is given that does not point to an RSS feed
 * when the RSS feed is expected
 * @author Sebastian Greenholtz
 */
public class UrlNotRssException extends Exception {

    public UrlNotRssException(String message) {
        super(message);
    }
}
