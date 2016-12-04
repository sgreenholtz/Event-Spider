package eventspider.controllers;

import eventspider.beans.PersistentUser;
import eventspider.beans.User;
import eventspider.beans.Profile;
import eventspider.factories.ProfileFactory;
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
        PersistentUser user = (PersistentUser) request.getSession().getAttribute("activeUser");
        if (user != null) {
            ProfileFactory profileFactory = new ProfileFactory();
            Profile profile = profileFactory.getProfile(user.getUserId());
            model.addAttribute("profile", profile);
            return "profile";
        } else {
            model.addAttribute("restrictedAccess", true);
            model.addAttribute("user", new User());
            request.getSession().setAttribute("returnPage", "profile");
            return "login";
        }
    }
}
