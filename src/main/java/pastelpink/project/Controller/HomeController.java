package pastelpink.project.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/play")
public class HomeController {
    @GetMapping("/start/{id}")
    public String ChessBoard(HttpSession session)
    {
        if(session.getAttribute("team") != null)
        {
            return "ChessBoard/index.html";
        }
        else
        {
            return "redirect:/login";
        }
        
    }
}
