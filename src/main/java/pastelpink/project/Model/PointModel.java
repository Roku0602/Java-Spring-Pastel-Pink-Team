package pastelpink.project.Model;

public class PointModel {
    private String id;
    private int top;
    private int left;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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


    public PointModel() {
         this.id = null;
        this.top = 0;
        this.left = 0;
    }

    public PointModel(String id, int top, int left) {
        this.id = id;
        this.top = top;
        this.left = left;
    }

}
