package eventspider.beans;

import java.util.List;

/**
 * Represents all the data for a user profile
 * @author Sebastian Greenholtz
 */
public class Profile {

    private String image;
    private List<EventBean> events;
    //TODO: Add image upload and storage


    /**
     * Gets the value of image.
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image to given value
     * @param image value to set instance variable to
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the value of events.
     * @return events
     */
    public List<EventBean> getEvents() {
        return events;
    }

    /**
     * Sets events to given value
     * @param events value to set instance variable to
     */
    public void setEvents(List<EventBean> events) {
        this.events = events;
    }
}
