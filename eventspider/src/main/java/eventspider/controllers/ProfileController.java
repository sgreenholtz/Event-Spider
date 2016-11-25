package eventspider.controllers;

import eventspider.beans.LoggedInUser;
import eventspider.beans.Profile;
import eventspider.beans.ProfileFactory;
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

    @GetMapping(value="profile")
    public String getProfile(Model model, HttpServletRequest request) {
        LoggedInUser activeuser = (LoggedInUser) request.getSession().getAttribute("activeuser");
        if (activeuser != null) {
            ProfileFactory profileFactory = new ProfileFactory();
            Profile profile = profileFactory.getProfile(activeuser.getUserID());
            model.addAttribute("profile", profile);
            return "profile";
        } else {
            model.addAttribute("restrictedAccess", true);
            return "login";
        }
    }
}
