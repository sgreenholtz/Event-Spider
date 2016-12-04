package eventspider.controllers;

import eventspider.beans.EventBean;
import eventspider.beans.PersistentUser;
import eventspider.beans.User;
import eventspider.database.EventHandler;
import eventspider.database.UserHandler;
import org.apache.log4j.Logger;
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

    private static final Logger log = Logger.getLogger(EventDetailsController.class);

    @RequestMapping(value="eventDetails", method = RequestMethod.GET)
    public String getEventDetails(@RequestParam int id, Model model){
        try (EventHandler handler = new EventHandler()) {
            model.addAttribute("event", handler.getEventByID(id));
        } catch (Exception e) {
            log.error(e);
        }

        return "event-details";
    }

    @GetMapping(value="addEventToUser")
    public String addEventToUser(@RequestParam int id, Model model, HttpServletRequest request) {
        try (EventHandler handler = new EventHandler();
             UserHandler userHandler = new UserHandler()) {
            PersistentUser user = (PersistentUser)request.getSession().getAttribute("activeUser");
            EventBean event = handler.getEventByID(id);
            User fullUser = userHandler.getUser(user.getUserId());
            boolean success = handler.saveEventToUser(fullUser, event);
            model.addAttribute("success", success);
            model.addAttribute("event", handler.getEventByID(id));
        } catch (Exception e){
            log.error(e);
        }
        return "event-details";
    }
}
