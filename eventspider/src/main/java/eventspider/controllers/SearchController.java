package eventspider.controllers;

import eventspider.Eventful.EventfulSearch;
import eventspider.beans.EventBean;
import eventspider.utility.EventBeanComparator;
import eventspider.beans.SearchBean;
import eventspider.database.DatabaseSearch;
import eventspider.database.EventHandler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.TreeSet;

/**
 * Controller for search functions
 * @author Sebastian Greenholtz
 */
@Controller
public class SearchController {

    private static final Logger log = Logger.getLogger(SearchController.class);

    @RequestMapping(value="search-form", method= RequestMethod.GET)
    public String getSearchForm(Model model) {
        model.addAttribute("search", new SearchBean());
        return "search";
    }

    @RequestMapping(value="search", method=RequestMethod.POST)
    public String doSearch(@ModelAttribute SearchBean search, Model model, HttpServletRequest request) throws Exception {
        EventBeanComparator comparator = new EventBeanComparator();
        TreeSet<EventBean> eventsList = new TreeSet<EventBean>(comparator);

        try(DatabaseSearch dbSearcher = new DatabaseSearch(search);
            EventHandler eventHandler = new EventHandler()) {
            if (search.getDatabaseSearch()) {
                eventsList.addAll(dbSearcher.performSearch());
            }

            if (search.getEventfulSearch()) {
                EventfulSearch eventfulSearcher = new EventfulSearch(search);
                eventsList.addAll(eventfulSearcher.performSearch());
                for (EventBean event : eventsList) {
                    eventHandler.addEvent(event);
                }
            }
            request.getSession().setAttribute("eventsList", eventsList);
            request.getSession().setAttribute("search", search);
        } catch (Exception e) {
            log.error(e);
        }
        return "searchResult";
    }

    @GetMapping(value="searchResult")
    public String returnToResults() {
        return "searchResult";
    }

}
