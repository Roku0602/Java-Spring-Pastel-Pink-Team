package pastelpink.project.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pastelpink.project.Model.ApiResponse;
import pastelpink.project.Model.ChessNode;
import pastelpink.project.Model.PointModel;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/api")
public class ChessBoardController {
     @Autowired
    private Environment environment;

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
            return ResponseEntity.ok().body(new ApiResponse(true, "", matrix, chessNodeList));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Lỗi: "+ e.getMessage(), null, null));
        }
    }
}

