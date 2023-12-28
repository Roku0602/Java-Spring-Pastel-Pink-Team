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

    @MessageMapping("/chessMove/{idRoom}")
    @SendTo("/topic/chessMove/{idRoom}")
    public String chessMove(String moveNode) {
        return moveNode;
    }

    
}

