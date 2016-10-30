package eventspider.controllers;

import eventspider.database.EventHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller to get and return the details page for a particular event
 * @author Sebastian Greenholtz
 */
@Controller
public class EventDetailsController {

    @RequestMapping(value="eventDetails", method = RequestMethod.GET)
    public String getEventDetails(@RequestParam int id, Model model){
        EventHandler handler = new EventHandler();
        model.addAttribute("event", handler.getEventByID(id));
        return "event-details";
    }
}
