package eventspider.controllers;

import eventspider.beans.SearchBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String doSearch(@ModelAttribute SearchBean search) {

        return "searchResults";
    }

}
