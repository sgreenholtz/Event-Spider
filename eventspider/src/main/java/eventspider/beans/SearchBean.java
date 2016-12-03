package eventspider.beans;

import org.joda.time.*;

/**
 * Entity representing a search. Uses org.joda.time.LocalDate for the date of event
 * @author Sebastian Greenholtz
 */
public class SearchBean {

    private String keyword;
    private String location;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Integer numResults;
    private Boolean databaseSearch;
    private Boolean eventfulSearch;

    /**
     * Empty constructor
     */
    public SearchBean() {}

    public SearchBean(String keyword, String location,
                      LocalDate dateStart, LocalDate dateEnd) {
        this();
        this.keyword = keyword;
        this.location = location;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    /**
     * Gets the value of databaseSearch.
     *
     * @return databaseSearch
     */
    public Boolean getDatabaseSearch() {
        return databaseSearch;
    }

    /**
     * Sets databaseSearch to given value
     *
     * @param databaseSearch value to set instance variable to
     */
    public void setDatabaseSearch(Boolean databaseSearch) {
        this.databaseSearch = databaseSearch;
    }

    /**
     * Gets the value of eventfulSearch.
     *
     * @return eventfulSearch
     */
    public Boolean getEventfulSearch() {
        return eventfulSearch;
    }

    /**
     * Sets eventfulSearch to given value
     *
     * @param eventfulSearch value to set instance variable to
     */
    public void setEventfulSearch(Boolean eventfulSearch) {
        this.eventfulSearch = eventfulSearch;
    }

    /**
     * Gets the value of numResults.
     *
     * @return numResults
     */
    public Integer getNumResults() {
        return numResults;
    }

    /**
     * Sets numResults to given value
     *
     * @param numResults value to set instance variable to
     */
    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
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
    public LocalDate getDateStart() {
        return dateStart;
    }

    /**
     * Sets dateStart to given value
     *
     * @param dateStart value to set instance variable to
     */
    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Gets the value of dateEnd.
     *
     * @return dateEnd
     */
    public LocalDate getDateEnd() {
        return dateEnd;
    }

    /**
     * Sets dateEnd to given value
     *
     * @param dateEnd value to set instance variable to
     */
    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "SearchBean{" +
                "keyword='" + keyword + '\'' +
                ", location='" + location + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
