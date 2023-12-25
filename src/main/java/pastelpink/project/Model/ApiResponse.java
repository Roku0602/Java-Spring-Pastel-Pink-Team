package pastelpink.project.Model;

import java.util.List;

public class ApiResponse {
    private boolean status;
    private String message;
    private List<List<PointModel>> matrix;
    private List<ChessNode> chessNodeList;

    // Constructors, getters, and setters

    public ApiResponse() {
        // Default constructor
    }

    public ApiResponse(boolean status, String message, List<List<PointModel>> matrix, List<ChessNode> chessNodeList) {
        this.status = status;
        this.message = message;
        this.matrix = matrix;
        this.chessNodeList = chessNodeList;
    }

    // Getters and setters...

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<List<PointModel>> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<List<PointModel>> matrix) {
        this.matrix = matrix;
    }

    public List<ChessNode> getChessNodeList() {
        return chessNodeList;
    }

    public void setChessNodeList(List<ChessNode> chessNodeList) {
        this.chessNodeList = chessNodeList;
    }

}
