package eventspider.beans;

/**
 * Contructs factory beans
 * @author Sebastian Greenholtz
 * TODO: finish this class
 */
public class ProfileFactory {

    private int userId;

    /**
     * Constructor sets userId
     * @param id
     */
    public ProfileFactory(int id) {
        userId = id;
    }

    public Profile getProfile() {
        Profile profile = new Profile();
        //get the path to the image
        //get the list of events
        return profile;

    }
}
