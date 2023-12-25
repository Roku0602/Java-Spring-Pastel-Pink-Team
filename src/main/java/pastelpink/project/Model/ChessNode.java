package pastelpink.project.Model;

public class ChessNode {
   
    private String id;
    private String src;
    private int top;
    private int left;
    private String visible;
    

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getTop() {
        return this.top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return this.left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public String getVisible() {
        return this.visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }
   

    public ChessNode() {
    }

    public ChessNode(String id, String src, int top, int left, String visible) {
        this.id = id;
        this.src = src;
        this.top = top;
        this.left = left;
        this.visible = visible;
    }

}
