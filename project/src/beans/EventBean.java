package beans;

/**
 * Bean representing an event
 * @author Sebastian Greenholtz
 */
public class EventBean {
    private Integer eventId;
    private String title;
    private String url;
    private String description;
//    private Date startTime;
//    private Date stopTime;
//    private String venueAddress;
//    private String city;
//    private String state;
//    private String postalCode;

    /**
     * Empty constructor
     */
    public EventBean() {}

    /**
     * Gets the value of eventId;
     * @return eventId
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * Sets eventId to given value
     * @param eventId value to set instance variable to
     */
    public void setEventId(Integer eventId) {
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
     * Sets description to given value
     * @param description value to set instance variable to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "EventBean{" +
                "eventId=" + eventId +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
