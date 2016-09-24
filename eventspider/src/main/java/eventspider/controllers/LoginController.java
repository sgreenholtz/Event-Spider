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

    @RequestMapping(value="login", method= RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("email", email);
        return "test";
    }

    @PostMapping("verify")
    public String loginSubmit(@RequestParam String email, Model model) {
        this.email = email;
        return "redirect:login";
    }
}