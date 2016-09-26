package eventspider.beans;

import java.time.*;

/**
 * Entity representing a search. Uses java.time.LocalDate for the date of event
 * @author Sebastian Greenholtz
 */
public class SearchBean {

    private String keyword;
    private String location;
    private LocalDate date;

    /**
     * Empty constructor
     */
    public SearchBean() {}

    public SearchBean(String keyword, String location, LocalDate date) {
        this.keyword = keyword;
        this.location = location;
        this.date = date;
    }

    /**
     * Gets the value of keyword.
     *
     * @return keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Sets keyword to given value
     *
     * @param keyword value to set instance variable to
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Gets the value of location.
     *
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location to given value
     *
     * @param location value to set instance variable to
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the value of date.
     *
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets date to given value
     *
     * @param date value to set instance variable to
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
