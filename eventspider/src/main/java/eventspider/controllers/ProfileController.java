package eventspider.controllers;

import eventspider.beans.LoggedInUser;
import eventspider.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for accessing the profile
 * @author Sebastian Greenholtz
 */
@Controller
public class ProfileController {

    @GetMapping(value="/profile")
    public String getProfile(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("activeuser") != null) {

            return "profile";
        } else {
            model.addAttribute("restrictedAccess", true);
            return "login";
        }
    }
}
