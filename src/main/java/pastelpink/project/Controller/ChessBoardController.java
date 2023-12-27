package pastelpink.project.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import pastelpink.project.Model.ApiResponse;
import pastelpink.project.Model.ChessMoveNodeModel;
import pastelpink.project.Model.ChessNode;
import pastelpink.project.Model.PointModel;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8080/")
@RestController
@RequestMapping("/api")
public class ChessBoardController {
     @Autowired
    private Environment environment;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/getChessBoard")
    public ResponseEntity<Object> getChessBoard() {
        try {
            Resource resource = new ClassPathResource("static/Data/ChessJson.txt");
            String filePath = resource.getFile().getAbsolutePath();
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            String chessJson = new String(bytes, StandardCharsets.UTF_8).replace("\uFEFF", "");
            System.out.println("dữ liệu: "  + chessJson);
            ObjectMapper objectMapper = new ObjectMapper();
            List<ChessNode> chessNodeList = objectMapper.readValue(chessJson, new TypeReference<List<ChessNode>>() {});

            List<List<PointModel>> matrix = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                List<PointModel> points = new ArrayList<>();
                int top = 61 + i * 74;
                for (int j = 0; j < 9; j++) {
                    int left = 106 + j * 74;
                    PointModel point = new PointModel();
                    point.setTop(top);
                    point.setLeft(left);
                    point.setId(" ");
                    ChessNode chessNode = chessNodeList.stream()
                            .filter(s -> s.getTop() == top && s.getLeft() == left)
                            .findFirst()
                            .orElse(null);
                    if (chessNode != null) {
                        point.setId(chessNode.getId().toString());
                    }
                    points.add(point);
                }
                matrix.add(points);
            }
            return ResponseEntity.ok().body(new ApiResponse(true, " ", matrix, chessNodeList));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Lỗi: "+ e.getMessage(), null, null));
        }
    }

    @PostMapping("/set-move-chess")
    public ResponseEntity<Object> moveChessNode(@RequestBody List<ChessMoveNodeModel> moveList,HttpSession session) {
        System.out.println("ok");
       
        try {
            String moveListStr = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(moveList);
            System.out.println("di chuyen: "+moveListStr);
            // Tạo một đối tượng ObjectMapper từ Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            // Chuyển đổi chuỗi JSON thành đối tượng JsonNode
            JsonNode jsonNode = objectMapper.readTree(moveListStr);

            // Lấy giá trị của trường "player" từ đối tượng JsonNode
            String playerValue = jsonNode.get(0).get("player").asText();
            System.out.println("Chuoi cat duoc: "+playerValue);

            //session được tạo ngay sau khi join phòng
            if(session.getAttribute("team") != null)
            {
                if(session.getAttribute("team").toString().equals(playerValue))
                {
                    
                }
                else
                {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Không phải turn của bạn", null, null));
                }
            }
            // Gọi hàm gửi thông điệp tới client ở đây (sử dụng WebSocket hoặc một cơ chế gửi thông điệp phù hợp)
            messagingTemplate.convertAndSend("/topic/chessMove", moveListStr);

            // Trả về kết quả
            return ResponseEntity.ok().body(new ApiResponse(true, "",null,null));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Lỗi: "+ e.getMessage(), null, null));
        }
    }
}
