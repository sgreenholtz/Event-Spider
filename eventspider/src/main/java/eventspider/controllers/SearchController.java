package eventspider.controllers;

import eventspider.DAL.*;
import eventspider.beans.SearchBean;
//import eventspider.database.DatabaseSearch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

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

//    @RequestMapping(value="search", method=RequestMethod.POST)
//    public String doSearch(@ModelAttribute SearchBean search, Model model) throws Exception {
//        DatabaseSearch searcher = new DatabaseSearch(search);
//        model.addAttribute("eventsList", searcher.searchByKeywordOnly());
//        model.addAttribute("search", search);
//        return "searchResult";
//    }

}
