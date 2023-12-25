package pastelpink.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/play")
public class HomeController {
    @GetMapping("/start")
    public String ChessBoard()
    {
        return "Home/index.html";
    }
}
