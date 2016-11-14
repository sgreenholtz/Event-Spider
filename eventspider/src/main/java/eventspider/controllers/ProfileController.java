package eventspider.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for accessing the profile
 * @author Sebastian Greenholtz
 */
@Controller
public class ProfileController {

    @GetMapping(value="/profile")
    public String getProfile(Model model) {
        return "profile";
    }
}
