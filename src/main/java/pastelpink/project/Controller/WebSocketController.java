package pastelpink.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import pastelpink.project.Model.WebSocketMessageModel;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendMessage/{idRoom}")
    @SendTo("/topic/messages/{idRoom}")
    public WebSocketMessageModel sendMessage(WebSocketMessageModel message) {
        return message;
    }

    @MessageMapping("/chessMove/{idRoom}")
    @SendTo("/topic/chessMove/{idRoom}")
    public String chessMove(String moveNode) {
        return moveNode;
    }

    @MessageMapping("/chessReload/{idRoom}")
    @SendTo("/topic/chessReload/{idRoom}")
    public String chessReloaded(String moveNode) {
        return moveNode;
    }

   
    @MessageMapping("/reloadPage/{id}")
    @SendTo("/topic/reloadPage/{id}")
    public String reloadPage(String roomid) {
       
        // Gửi thông báo đến tất cả các kết nối khác
        System.out.println("===========================================================================");
       return  roomid;
    }

    
}

