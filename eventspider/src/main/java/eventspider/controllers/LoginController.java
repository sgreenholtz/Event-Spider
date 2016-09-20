package eventspider.controllers;

import eventspider.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller for Login
 * @author Sebastian Greenholtz
 */
@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(@ModelAttribute User user) {
        return "result";
    }
}