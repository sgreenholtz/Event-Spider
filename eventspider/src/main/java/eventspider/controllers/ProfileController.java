package eventspider.controllers;

import eventspider.beans.PersistentUser;
import eventspider.beans.User;
import eventspider.beans.Profile;
import eventspider.database.ProfileHandler;
import eventspider.database.UserHandler;
import org.apache.log4j.Logger;
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

    private static final Logger log = Logger.getLogger(ProfileController.class);

    @GetMapping(value="profile")
    public String getProfile(Model model, HttpServletRequest request) {
        PersistentUser user = (PersistentUser) request.getSession().getAttribute("activeUser");
        if (user != null) {
            try (ProfileHandler profileHandler = new ProfileHandler();
                 UserHandler userHandler = new UserHandler()){
                User fullUser = userHandler.getUser(user.getUserId());
                Profile profile = profileHandler.getProfile(fullUser);
                model.addAttribute("profile", profile);
            } catch (Exception e) {
                log.error(e);
            }
            return "profile";
        } else {
            model.addAttribute("restrictedAccess", true);
            model.addAttribute("user", new User());
            request.getSession().setAttribute("returnPage", "profile");
            return "login";
        }
    }
}
