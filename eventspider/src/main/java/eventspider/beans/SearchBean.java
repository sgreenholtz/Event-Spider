package eventspider.beans;

import java.util.*;

/**
 * Entity representing a search. Uses java.time.LocalDate for the date of event
 * @author Sebastian Greenholtz
 */
public class SearchBean {

    private String keyword;
    private String location;
    private Date dateStart;
    private Date dateEnd;

    /**
     * Empty constructor
     */
    public SearchBean() {}

    public SearchBean(String keyword, String location,
                      Date dateStart, Date dateEnd) {
        this.keyword = keyword;
        this.location = location;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
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
     * Gets the value of dateStart.
     *
     * @return dateStart
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * Sets dateStart to given value
     *
     * @param dateStart value to set instance variable to
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Gets the value of dateEnd.
     *
     * @return dateEnd
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Sets dateEnd to given value
     *
     * @param dateEnd value to set instance variable to
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
