package eventspider.controllers;

import eventspider.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller for Login
 * @author Sebastian Greenholtz
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute User user) {
        return "result";
    }
}