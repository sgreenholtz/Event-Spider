package eventspider.controllers;

import eventspider.Eventful.EventfulSearch;
import eventspider.beans.EventBean;
import eventspider.beans.SearchBean;
import eventspider.database.DatabaseSearch;
import eventspider.database.EventHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for search functions
 * @author Sebastian Greenholtz
 */
@Controller
public class SearchController {

    @RequestMapping(value="search-form", method= RequestMethod.GET)
    public String getSearchForm(Model model) {
        model.addAttribute("search", new SearchBean());
        return "search";
    }

    @RequestMapping(value="search", method=RequestMethod.POST)
    public String doSearch(@ModelAttribute SearchBean search, Model model, HttpServletRequest request) throws Exception {
        List<EventBean> eventsList = new ArrayList<>();

        if (search.getDatabaseSearch()) {
            DatabaseSearch dbSearcher = new DatabaseSearch(search);
            eventsList.addAll(dbSearcher.performSearch());
        }

        if (search.getEventfulSearch()) {
            EventfulSearch eventfulSearcher = new EventfulSearch(search);
            eventsList.addAll(eventfulSearcher.performSearch());
            EventHandler eventHandler = new EventHandler();
            for (EventBean event : eventsList) {
                eventHandler.addEvent(event);
            }
        }
        request.getSession().setAttribute("eventsList", eventsList);
        model.addAttribute("search", search);
        return "searchResult";
    }

    @GetMapping(value="searchResult")
    public String returnToResults() {
        return "searchResult";
    }

}
