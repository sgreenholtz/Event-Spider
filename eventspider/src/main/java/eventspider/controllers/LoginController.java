package eventspider.controllers;

import eventspider.beans.*;
import eventspider.database.ProfileHandler;
import eventspider.database.UserHandler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Controller for Login
 * @author Sebastian Greenholtz
 */
@Controller
public class LoginController {

    private static final Logger log = Logger.getLogger(LoginController.class);

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
        try (UserHandler handler = new UserHandler();
             ProfileHandler profileHandler = new ProfileHandler()){
            User user = handler.logIn(attempt);
            if (user == null) {
                model.addAttribute("notLoggedIn", true);
                model.addAttribute("user", new User());
                return "login";
            } else {
                request.getSession().setAttribute("activeuser", user);
                String firstName = profileHandler.getFirstNameForUser(user.getUserID());
                request.getSession().setAttribute("userFirstName", firstName);
                return (String) request.getSession().getAttribute("returnPage");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "error";
    }

    @GetMapping(value="register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value="register")
    public String registerUser(@ModelAttribute User user) {
        try (UserHandler handler = new UserHandler()){
            handler.register(user);
        } catch (Exception e) {
            log.error(e);
        }

        return "login";
    }

    @GetMapping(value="logout")
    public String logOut(HttpServletRequest request) {
        request.getSession().removeAttribute("activeuser");
        return "index";
    }
}