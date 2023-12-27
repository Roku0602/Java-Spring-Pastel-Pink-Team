package pastelpink.project.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import pastelpink.project.Model.WebSocketMessageModel;

@Controller
public class WebSocketController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/receiveMessage")
    public WebSocketMessageModel sendMessage(WebSocketMessageModel message) {
        return message;
    }

    @MessageMapping("/chessMove")
    @SendTo("/topic/chessMove")
    public String chessMove(String moveNode) {
        return moveNode;
    }

    
}

