package eventspider.beans;

import eventspider.utility.LocalDateFieldBridge;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import org.joda.time.LocalDate;

/**
 * Bean representing an event
 * @author Sebastian Greenholtz
 */
@Entity
@Table(name = "Events")
@Indexed

public class EventBean {
    @Id
    @Column(name = "event_id")
    @GenericGenerator(name="eventbean" , strategy="identity")
    @GeneratedValue(generator="eventbean")
    private Integer eventId;

    @Field(index= Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Field(index=Index.YES, analyze= Analyze.YES, store= Store.NO)
    @Column(name = "description")
    private String description;

    @FieldBridge(impl = LocalDateFieldBridge.class)
    @Column(name = "start_date", columnDefinition = "DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
    private LocalDate startDate;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "venue_address")
    private String venueAddress;

    @Field(index=Index.YES, analyze=Analyze.NO, store=Store.NO)
    @Column(name = "city")
    private String city;

    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Column(name = "state")
    private String state;

    @Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
    @Column(name = "postal_code")
    private String postalCode;


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
     * Gets the value of startDate.
     *
     * @return startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets startDate to given value
     *
     * @param startDate value to set instance variable to
     */
    public void setStartDate(LocalDate startDate) {
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
