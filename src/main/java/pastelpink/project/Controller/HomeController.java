package pastelpink.project.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import pastelpink.project.Entity.Room_detail;
import pastelpink.project.Service.HomeService;

@Controller
@RequestMapping("")
public class HomeController {


    @Autowired
    HomeService homeService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/")
    public String Home(HttpSession session,Model model)
    {
        List<Room_detail> listShow = homeService.getallroom();
        model.addAttribute("PlayerList", listShow);
        return "Home/index.html";
    }

    @PostMapping("/rooms/add")
    public String AddNewRoom(HttpSession session, Model model, @RequestParam("inputText") String pass, @RequestParam("fileInput") MultipartFile file)
    {
        int idroom = homeService.AddNewRoom(session.getAttribute("user").toString(), pass);
        if(idroom != 0)
        {
            // Tên file gốc
            if(file != null && !file.isEmpty())
            {
                String originalFilename = file.getOriginalFilename();
                // Loại bỏ số đuôi từ tên file
                String fileNameWithoutExtension = homeService.removeNumberAndExtension(originalFilename);
                fileNameWithoutExtension = fileNameWithoutExtension+".json";
                System.out.println("file la:"+fileNameWithoutExtension.toString());
                try
                {
                    
                    Path filePath = Paths.get("static/Data/", fileNameWithoutExtension);
                    // Lưu đường dẫn vào session
                    session.setAttribute("filetoLoadPath", filePath.toString());
                    // Lưu file vào đường dẫn đã xác định
                    
                    file.transferTo(filePath.toFile());
                }
                catch(Exception ex)
                {
                    System.out.println("notgood");
                }
            }
            
             return "redirect:/play/start/"+idroom;
        }
        return "redirect:/";
    }
    
    @GetMapping("/play/start/{id}")
    public String ChessBoard(HttpSession session,@PathVariable("id")  int id)
    {
        if(session.getAttribute("playeronRoom") != null)
        {
            session.removeAttribute("playeronRoom");
        }
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
                    String nameOfMasterRoom = homeService.getRoomMaster(id);
                    if(session.getAttribute("user").toString().trim().equals(nameOfMasterRoom.trim().toString()))
                    {
                        //Nếu người out là chủ phòng, xóa phòng
                        session.setAttribute("masterroom",nameOfMasterRoom);
                        
                    }
                    if(countplayer.getSoLuong() < 2 && countplayer.getSoLuong() > 0)
                        {
                            session.setAttribute("team", "den");
                            homeService.updateRoomPlayerCount(id);
                            session.setAttribute("playeronRoom", id);
                            messagingTemplate.convertAndSend("/topic/reloadPage/"+id, "Reloaded");
                        }
                        else if(countplayer.getSoLuong() == 0 || countplayer.getSoLuong() == null)
                        {   
                            session.setAttribute("team", "do");
                            homeService.updateRoomPlayerCount(id);
                            session.setAttribute("playeronRoom", id);
                            messagingTemplate.convertAndSend("/topic/reloadPage/"+id, "Reloaded");
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

    @PostMapping("/typePass/{idroom}")
    public String JoinRoomWithPass(HttpSession session,Model model,@RequestParam("inputText") String pass,@PathVariable("idroom") int idRoom,@RequestParam("fileInput") MultipartFile file)
    {
        System.out.println(pass+" idroom: "+idRoom);
        int result = homeService.CheckpassRoomIsRight(idRoom,pass);
        if(result == 1)
        {
            // Tên file gốc
            if(file != null && !file.isEmpty())
            {
                String originalFilename = file.getOriginalFilename();
                // Loại bỏ số đuôi từ tên file
                String fileNameWithoutExtension = homeService.removeNumberAndExtension(originalFilename);
                fileNameWithoutExtension = fileNameWithoutExtension+".json";
                System.out.println("file la:"+fileNameWithoutExtension.toString());
                try
                {
                    Path filePath = Paths.get("static/Data/", fileNameWithoutExtension);
                    // Lưu đường dẫn vào session
                    session.setAttribute("filetoLoadPath", filePath.toString());
                    // Lưu file vào đường dẫn đã xác định
                    
                    file.transferTo(filePath.toFile());
                }
                catch(Exception ex)
                {
                    System.out.println("notgood");
                }
            }
            return "redirect:/play/start/"+idRoom;
        }
        else
        {
            return "redirect:/";
        }
        
    }
}
