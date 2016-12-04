package eventspider.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Maps the homepage
 * @author Sebastian Greenholtz
 */
@Controller
public class HomeController implements ErrorController {

    @RequestMapping(value="/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/error")
    public String errorPage() {
        return "Error handling";
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
