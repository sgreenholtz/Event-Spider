package eventspider.controllers;

import eventspider.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Login
 * @author Sebastian Greenholtz
 */
@Controller
public class LoginController {

    private String email;

    @RequestMapping(value="login", method=RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value="test", method= RequestMethod.GET)
    public String loginResult(Model model) {
        model.addAttribute("email", email);
        return "test";
    }

    @RequestMapping(value="verify", method=RequestMethod.POST)
    public String loginSubmit(@RequestParam String email, Model model) {
        this.email = email;
        return "redirect:test";
    }
}