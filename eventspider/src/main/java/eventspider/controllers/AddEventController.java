package eventspider.controllers;

import eventspider.beans.EventBean;
import eventspider.database.EventHandler;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller to add events to the database, either from the form or from
 * an external source search
 * @author Sebastian Greenholtz
 */
@Controller
public class AddEventController {

    private static final Logger log = Logger.getLogger(AddEventController.class);

    @RequestMapping(value="addEventManual", method=RequestMethod.POST)
    public String addEventManual(@ModelAttribute EventBean event) {
        try (EventHandler handler = new EventHandler()){
            handler.addEvent(event);
        } catch (Exception e) {
            log.error(e);
        }
        return "index";
    }

    @RequestMapping(value="add-event-form", method=RequestMethod.GET)
    public String getEventForm(Model model, HttpServletRequest request) {
        model.addAttribute("event", new EventBean());
        if (request.getSession().getAttribute("activeuser") != null) {
            return "addEvent";
        } else {
            model.addAttribute("restrictedAccess", true);
            request.getSession().setAttribute("returnPage", "addEvent");
            return "login";
        }
    }
}
