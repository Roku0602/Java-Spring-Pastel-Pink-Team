package pastelpink.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import pastelpink.project.Entity.Room_detail;
import pastelpink.project.Service.HomeService;

@Controller
@RequestMapping("")
public class HomeController {


    @Autowired
    HomeService homeService;

    @GetMapping("/")
    public String Home(HttpSession session,Model model)
    {
        List<Room_detail> listShow = homeService.getallroom();
        model.addAttribute("PlayerList", listShow);
        return "Home/index.html";
    }

    @GetMapping("/rooms/add")
    public String AddNewRoom(HttpSession session,Model model)
    {
        
        return "redirect:/";
    }
    
    @GetMapping("/play/start/{id}")
    public String ChessBoard(HttpSession session,@PathVariable("id")  int id)
    {
        if(session.getAttribute("user") != null)
        {
            Room_detail countplayer = homeService.CheckCountNumPlayer(id);
            if(countplayer != null)
            {
                if(session.getAttribute("playeronRoom") != null)
                {
                    if(session.getAttribute("playeronRoom").toString().trim().equals(id))
                    {
                        //Người chơi đã ở ngay trong phòng, không cần tăng thêm
                    }
                }
                else
                {
                    if(countplayer.getSoLuong() < 2 && countplayer.getSoLuong() > 0)
                        {
                            session.setAttribute("team", "den");
                            homeService.updateRoomPlayerCount(id);
                            session.setAttribute("playeronRoom", id);
                        }
                        else if(countplayer.getSoLuong() == 0 || countplayer.getSoLuong() == null)
                        {   
                            session.setAttribute("team", "do");
                            homeService.updateRoomPlayerCount(id);
                            session.setAttribute("playeronRoom", id);
                        }
                }
                
            }
            else
            {
                return "redirect:/";
            }
            
            return "ChessBoard/index.html";
        }
        else
        {
            return "redirect:/login";
        }
        
    }
}
