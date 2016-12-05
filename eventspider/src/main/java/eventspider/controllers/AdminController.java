package eventspider.controllers;

import eventspider.beans.PersistentUser;
import eventspider.beans.User;
import eventspider.beans.Roles;
import eventspider.beans.User;
import eventspider.database.EventHandler;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller to handle Admin page options
 * @author Sebastian Greenholtz
 *
 */
@Controller
public class AdminController {

    private static final Logger log = Logger.getLogger(AdminController.class);

    @GetMapping("/admin-page")
    public String getAdminPage(HttpServletRequest request, Model model) {
        PersistentUser user = (PersistentUser) request.getSession().getAttribute("activeUser");
        if (user == null) {
            model.addAttribute("restrictedAccess", true);
            model.addAttribute("user", new User());
            request.getSession().setAttribute("returnPage", "admin");
            return "login";
        } else if (user.getRole() != Roles.ADMINISTRATOR) {
            model.addAttribute("doesNotHavePermission", true);
            return "index";
        } else {
            return "admin";
        }
    }

    @GetMapping("/clearDatabase")
    public String clearDatabase(Model model, HttpServletRequest request) {
        PersistentUser user = (PersistentUser) request.getSession().getAttribute("activeUser");
        if (user == null) {
            model.addAttribute("restrictedAccess", true);
            model.addAttribute("user", new User());
            request.getSession().setAttribute("returnPage", "admin");
            return "login";
        } else if (user.getRole() != Roles.ADMINISTRATOR) {
            model.addAttribute("doesNotHavePermission", true);
            return "index";
        } else {
            boolean result = false;
            try (EventHandler handler = new EventHandler()) {
                result = handler.deleteOldItems(LocalDate.now());
            } catch (Exception e) {
                log.error(e);
            }

            String message = null;
            if (result) {
                message = "<strong>Success!</strong> Old items in database have been deleted.";
            } else {
                message = "<strong>Oops!</strong> Something went wrong and nothing was deleted.";
            }
            model.addAttribute("result", result);
            model.addAttribute("message", message);
            return "admin";
        }
    }
}
