package eventspider.beans;

import javax.persistence.*;

/**
 * Bean representing an event
 * @author Sebastian Greenholtz
 */
@Entity
@Table(name = "Events")
public class EventBean {
    @Id
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "venue_address")
    private String venueAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "start_date")
    private String startDate;


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
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets stopTime to given value
     *
     * @param stopTime value to set instance variable to
     */
    public void setEndTime(String stopTime) {
        this.endTime = stopTime;
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

    /**
     * Gets the value of startDate.
     *
     * @return startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets startDate to given value
     *
     * @param startDate value to set instance variable to
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "EventBean{" +
                "eventId=" + eventId +
                ", title='" + title +
                '}';
    }
}
