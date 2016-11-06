package eventspider.controllers;

import eventspider.database.EventHandler;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to handle Admin page options
 * @author Sebastian Greenholtz
 *
 */
@Controller
public class AdminController {

    @GetMapping("/admin-page")
    public String getAdminPage(Model model) {
        return "admin";
    }

    @GetMapping("/clearDatabase")
    public String clearDatabase(Model model) {
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
