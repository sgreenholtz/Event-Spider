package beans;

/**
 * Bean representing an event
 * @author Sebastian Greenholtz
 */
public class EventBean {
    private String eventId;
    private String title;
    private String url;
    private String description;
    private String startTime;
    private String stopTime;
    private String venueAddress;
    private String city;
    private String state;
    private String postalCode;

    /**
     * Empty constructor
     */
    public EventBean() {}

    /**
     * Gets the value of eventId;
     * @return eventId
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Sets eventId to given value
     * @param eventId value to set instance variable to
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * Gets the value of title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title to given value
     * @param title value to set instance variable to
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the value of url.
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url to given value
     * @param url value to set instance variable to
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the value of description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the value of startTime.
     *
     * @return startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets startTime to given value
     *
     * @param startTime value to set instance variable to
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the value of stopTime.
     *
     * @return stopTime
     */
    public String getStopTime() {
        return stopTime;
    }

    /**
     * Sets stopTime to given value
     *
     * @param stopTime value to set instance variable to
     */
    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    /**
     * Gets the value of venueAddress.
     *
     * @return venueAddress
     */
    public String getVenueAddress() {
        return venueAddress;
    }

    /**
     * Sets venueAddress to given value
     *
     * @param venueAddress value to set instance variable to
     */
    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    /**
     * Gets the value of city.
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city to given value
     *
     * @param city value to set instance variable to
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the value of state.
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state to given value
     *
     * @param state value to set instance variable to
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the value of postalCode.
     *
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets postalCode to given value
     *
     * @param postalCode value to set instance variable to
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Sets description to given value
     * @param description value to set instance variable to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the first X characters of the description
     * @return Substring of the description variable for display
     */
    public String getShortDescription() {
        return description.substring(0,150);
    }

    @Override
    public String toString() {
        return "EventBean{" +
                "eventId=" + eventId +
                ", title='" + title + '\'' +
                ", description='" + description.substring(0, 99) + '\'' +
                '}';
    }
}
