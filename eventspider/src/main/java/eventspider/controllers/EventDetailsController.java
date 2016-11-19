package eventspider.controllers;

import eventspider.beans.User;
import eventspider.database.EventHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping(value="addEventToUser")
    public String addEventToUser(@RequestParam int id, Model model, HttpServletRequest request) {
        EventHandler handler = new EventHandler();
        User user = (User)request.getSession().getAttribute("activeuser");
        boolean success = handler.saveEventToUser(user.getUserID(), id);
        model.addAttribute("success", success);
        model.addAttribute("event", handler.getEventByID(id));
        return "event-details";
    }
}
