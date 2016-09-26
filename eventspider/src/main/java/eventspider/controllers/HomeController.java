package eventspider.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Maps the homepage
 * @author Sebastian Greenholtz
 */
@Controller
public class HomeController {

    @GetMapping(value="/")
    public String getHomePage(Model model) {
        return "index";
    }
}
