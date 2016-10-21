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

    @RequestMapping(value="/")
    public String index(Model model) {
        return "index";
    }
}
