package eventspider.controllers;

import eventspider.beans.*;
import eventspider.database.UserHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Controller for Login
 * @author Sebastian Greenholtz
 * TODO: Figure out error on incorrect login
 */
@Controller
public class LoginController {

    @RequestMapping(value="login", method=RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("notLoggedIn", false);
        return "login";
    }

    @PostMapping(value="verify")
    public String loginSubmit(@RequestParam String email, @RequestParam String password, Model model,
                              HttpServletRequest request) {
        User attempt = new User(email, password);
        UserHandler handler = new UserHandler();
        LoggedInUser user = handler.logIn(attempt);
        handler.closeSession();
        if (user == null) {
            model.addAttribute("notLoggedIn", true);
            model.addAttribute("user", new User());
            return "login";
        } else {
            request.getSession().setAttribute("activeuser", user);
            return "index";
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
        UserHandler handler = new UserHandler();
        handler.register(user);
        handler.closeSession();
        return "login";
    }

    @GetMapping(value="logout")
    public String logOut(HttpServletRequest request) {
        request.getSession().removeAttribute("activeuser");
        return "index";
    }
}