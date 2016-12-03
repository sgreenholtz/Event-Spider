package eventspider.factories;

import eventspider.Eventful.EventItem;
import eventspider.beans.EventBean;
import org.apache.log4j.Logger;

import java.util.*;
import java.text.*;
import org.joda.time.LocalDate;

/**
 * Creates Event Beans from a variety of sources
 * @author Sebastian Greenholtz
 */
public class EventFactory {

    private List<EventBean> eventList;

    /**
     * Empty constructor
     */
    public EventFactory() {
        eventList = new ArrayList<>();
    }

    /**
     * Gets the value of eventList.
     * @return eventList
     */
    public List<EventBean> getEventList() {
        return eventList;
    }

    /**
     * Sets eventList to given value
     * @param eventList value to set instance variable to
     */
    public void setEventList(List<EventBean> eventList) {
        this.eventList = eventList;
    }

    /**
     * Creates an Event bean using Strings for each instance
     * @param title
     * @param url
     * @param description
     * @param startDate
     * @param startTime
     * @param stopTime Can be left blank if no stop time is indicated
     * @param venueAddress
     * @param city
     * @param state
     * @param postalCode Either zip code or Canadian Postal code
     * @return Event bean
     */
    public EventBean createBean(String title,
                                String url,
                                String description,
                                LocalDate startDate,
                                String startTime,
                                String stopTime,
                                String venueAddress,
                                String city,
                                String state,
                                String postalCode) {
        EventBean event = new EventBean();
        event.setTitle(title);
        event.setUrl(url);
        event.setDescription(description);
        event.setStartDate(startDate);
        event.setStartTime((startTime));
        if (!stopTime.equals("")) {
            event.setEndTime((stopTime));
        } else {
            event.setEndTime(null);
        }
        event.setVenueAddress(venueAddress);
        event.setCity(city);
        event.setState(state);
        event.setPostalCode(postalCode);
        return event;
    }

    /**
     * Creates an Event bean with all of the instance variables
     * including the ID as a int
     * @param id Integer id for the event
     * @param title
     * @param url
     * @param description
     * @param startDate
     * @param startTime
     * @param stopTime Can be left blank if no stop time is indicated
     * @param venueAddress
     * @param city
     * @param state
     * @param postalCode Either zip code or Canadian Postal code
     * @return Event bean
     */
    public EventBean createBean(Integer id,
                                String title,
                                String url,
                                String description,
                                LocalDate startDate,
                                String startTime,
                                String stopTime,
                                String venueAddress,
                                String city,
                                String state,
                                String postalCode) {
        EventBean event = new EventBean();
        event.setEventId(id);
        event.setTitle(title);
        event.setUrl(url);
        event.setDescription(description);
        event.setStartDate(startDate);
        event.setStartTime((startTime));
        if (!stopTime.equals("")) {
            event.setEndTime((stopTime));
        } else {
            event.setEndTime(null);
        }
        event.setVenueAddress(venueAddress);
        event.setCity(city);
        event.setState(state);
        event.setPostalCode(postalCode);
        return event;
    }

    /**
     * Creates an event using the data from the Eventful event, which has
     * more information than used by Event Spider
     * @param eventfulEvent an event from an Eventful search
     * @return Event Bean
     */
    public EventBean createBean(EventItem eventfulEvent) {
        EventBean event = new EventBean();
        event.setTitle(eventfulEvent.getTitle());
        event.setUrl(eventfulEvent.getUrl());
        event.setDescription(eventfulEvent.getDescription());
        event.setStartDate(extractDate(eventfulEvent.getStart_time()));
        event.setStartTime(extractTime(eventfulEvent.getStart_time()));
        event.setEndTime(extractTime(eventfulEvent.getStop_time()));
        event.setVenueAddress((String)eventfulEvent.getVenue_address());
        event.setCity(eventfulEvent.getCity_name());
        event.setState(eventfulEvent.getRegion_abbr());
        event.setPostalCode((String)eventfulEvent.getPostal_code());
        return event;
    }

    /**
     * Extracts the time out of a date-time string yyyy-mm-dd hh:mm:ss
     * @param dateTime string date and time
     * @return String of time as hh:mm
     */
    private String extractTime(String dateTime) {
        if (dateTime == null) {
            return null;
        } else {
            return dateTime.substring(11, 16);
        }
    }

    /**
     * Extracts the date out of a date-time string yyyy-mm-dd hh:mm:ss
     * @param dateTime string date and time
     * @return LocalDate created from the date fields
     */
    private LocalDate extractDate(String dateTime) {
        int year = Integer.parseInt(dateTime.substring(0, 4));
        int month = Integer.parseInt(dateTime.substring(5, 7));
        int day = Integer.parseInt(dateTime.substring(8, 10));
        return new LocalDate(year, month, day);

    }



}