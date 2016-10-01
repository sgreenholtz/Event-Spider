package eventspider.controllers;

import eventspider.beans.EventBean;
import eventspider.database.EventHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to add events to the database, either from the form or from
 * an external source search
 * @author Sebastian Greenholtz
 */
@Controller
public class AddEventController {

    @RequestMapping(value="/addEvent", method= RequestMethod.POST)
    public String addEventManual(@ModelAttribute EventBean event) {
        EventHandler handler = new EventHandler();
        handler.addEvent(event);
        return "/";
    }
}
