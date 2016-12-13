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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
            model.addAttribute("profile", getProfile(getFullUser(user)));
            return "profile";
        } else {
            model.addAttribute("restrictedAccess", true);
            model.addAttribute("user", new User());
            request.getSession().setAttribute("returnPage", "profile");
            return "login";
        }
    }

    @GetMapping(value="edit-profile")
    public String getEditForm(Model model, HttpServletRequest request) {
        PersistentUser user = (PersistentUser) request.getSession().getAttribute("activeUser");
        User fullUser = getFullUser(user);
        model.addAttribute("profile", getProfile(fullUser));
        model.addAttribute("user", fullUser);
        return "editProfile";
    }

    @PostMapping(value="submit-edit-profile")
    public String updateProfile(@ModelAttribute Profile profile) {
        return "redirect:edit-profile";
    }

    @PostMapping(value="submit-edit-user")
    public String updateUserInfo(@ModelAttribute User user) {

        return "redirect:edit-profile";
    }

    /**
     * Gets profile for the user
     * @param user User bean for which to get user
     * @return Profile bean
     */
    private Profile getProfile(User user) {
        Profile profile = null;
        try (ProfileHandler profileHandler = new ProfileHandler()){
            profile = profileHandler.getProfile(user);
        } catch (Exception e) {
            log.error(e);
        }
        return profile;
    }

    /**
     * Get full user bean
     * @param user PersistentUser from Session
     * @return User bean with all fields
     */
    private User getFullUser(PersistentUser user) {
        User fullUser = null;
        try (UserHandler userHandler = new UserHandler()){
            fullUser = userHandler.getUser(user.getUserId());
        } catch (Exception e) {
            log.error(e);
        }
        return fullUser;
    }
}
