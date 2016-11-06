package eventspider.controllers;

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

    @RequestMapping("/clearDatabase")
    public String clearDatabase() {

        return "admin";
    }
}
