package eventspider.controllers;

import eventspider.beans.*;
import eventspider.database.ProfileHandler;
import eventspider.database.*;
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

    @PostMapping(value="login")
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
                String firstName = profileHandler.getFirstNameForUser(user.getUserID());
                request.getSession().setAttribute("activeUser", new PersistentUser(user, firstName));
                return (String) request.getSession().getAttribute("returnPage");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "error";
    }

    @GetMapping(value="register")
    public String registerForm(Model model) {
        model.addAttribute("register", new Registration());
        return "register";
    }

    @PostMapping(value="register")
    public String registerUser(@ModelAttribute Registration registration) {
        try (UserHandler handler = new UserHandler()){
            handler.register(registration);
        } catch (Exception e) {
            log.error(e);
        }

        return "login";
    }

    @GetMapping(value="logout")
    public String logOut(HttpServletRequest request) {
        request.getSession().removeAttribute("activeUser");
        return "index";
    }
}
