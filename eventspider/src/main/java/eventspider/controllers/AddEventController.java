package eventspider.controllers;

import eventspider.beans.EventBean;
import eventspider.database.EventHandler;
import eventspider.utility.MySqlDateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to add events to the database, either from the form or from
 * an external source search
 * @author Sebastian Greenholtz
 */
@Controller
public class AddEventController {

    @RequestMapping(value="/addEventManual", method=RequestMethod.POST)
    public String addEventManual(@ModelAttribute EventBean event) {
        EventHandler handler = new EventHandler();
        handler.addEvent(event);
        return "/";
    }

    @RequestMapping(value="add-event-form", method=RequestMethod.GET)
    public String getEventForm(Model model) {
        model.addAttribute("event", new EventBean());
        return "addEvent";
    }
}
