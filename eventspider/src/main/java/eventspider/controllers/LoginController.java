package eventspider.controllers;

import eventspider.beans.LoggedInUser;
import eventspider.beans.RequiredFieldMissingException;
import eventspider.beans.User;
import eventspider.beans.UserRoles;
import eventspider.database.UserHandler;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Login
 * @author Sebastian Greenholtz
 */
@Controller
public class LoginController {

    private LoggedInUser user;
    private Boolean loggedInFail = false;

    @RequestMapping(value="login", method=RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("notLoggedIn", loggedInFail);
        return "login";
    }

    @RequestMapping(value="test", method= RequestMethod.GET)
    public String loginResult(Model model) {
        model.addAttribute("user", user);
        return "test";
    }

    @RequestMapping(value="verify", method=RequestMethod.POST)
    public String loginSubmit(@RequestParam String email, @RequestParam String password, Model model) {
        User attempt = new User(email, password);
        UserHandler handler = new UserHandler();
        user = handler.logIn(attempt);
        if (user == null) {
            loggedInFail = true;
            model.addAttribute("notLoggedIn", loggedInFail);
            return "login";
        } else {
            return "index       ";
        }
    }

    @RequestMapping(value="register", method=RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value="register", method=RequestMethod.POST)
    public String registerUser(@ModelAttribute User user)
        throws RequiredFieldMissingException {
        user.setRole(UserRoles.MEMBER);
        UserHandler handler = new UserHandler();
        handler.register(user);
        loggedInFail = false;
        return "login";
    }
}