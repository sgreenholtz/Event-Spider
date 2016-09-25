package eventspider.controllers;

import eventspider.beans.LoggedInUser;
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

    @RequestMapping(value="login", method=RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
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
            return "redirect:login";
        } else {
            return "redirect:test";
        }
    }

    @RequestMapping(value="register", method=RequestMethod.GET)
    public String registerForm(Model model) {
        return "register";
    }

    @RequestMapping(value="register", method=RequestMethod.POST)
    public String registerUser(@RequestParam String email, @RequestParam String password,
                               @RequestParam String firstName, @RequestParam String lastName, Model model) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPassword(DigestUtils.sha1Hex(password));
        newUser.setRole(UserRoles.MEMBER);
        UserHandler handler = new UserHandler();
        handler.register(newUser);
        return "redirect:login";
    }
}