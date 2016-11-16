package eventspider.controllers;

import eventspider.beans.LoggedInUser;
import eventspider.beans.Roles;
import eventspider.database.EventHandler;
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

    @GetMapping("/admin-page")
    public String getAdminPage(HttpServletRequest request, Model model) {
        LoggedInUser user = (LoggedInUser)request.getSession().getAttribute("activeuser");
        if (user == null) {
            model.addAttribute("restrictedAccess", true);
            return "login";
        } else if (user.getRole() != Roles.ADMINISTRATOR) {
            model.addAttribute("doesNotHavePermission", true);
            return "index";
        } else {
            model.addAttribute("restrictedAccess", true);
            return "login";
        }
    }

    @GetMapping("/clearDatabase")
    public String clearDatabase(Model model, HttpServletRequest request) {
        LoggedInUser user = (LoggedInUser) request.getSession().getAttribute("activeuser");
        if (user == null) {
            model.addAttribute("restrictedAccess", true);
            return "login";
        } else if (user.getRole() != Roles.ADMINISTRATOR) {
            model.addAttribute("doesNotHavePermission", true);
            return "index";
        } else {

            EventHandler handler = new EventHandler();
            boolean result = handler.deleteOldItems(LocalDate.now());
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
