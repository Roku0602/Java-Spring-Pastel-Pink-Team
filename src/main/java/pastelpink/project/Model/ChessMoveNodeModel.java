package pastelpink.project.Model;

public class ChessMoveNodeModel {
    private String id;
    private int starti;
    private int endi;
    private int startj;
    private int endj;
    private String player;
    private String rooms;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStarti() {
        return this.starti;
    }

    public void setStarti(int starti) {
        this.starti = starti;
    }

    public int getEndi() {
        return this.endi;
    }

    public void setEndi(int endi) {
        this.endi = endi;
    }

    public int getStartj() {
        return this.startj;
    }

    public void setStartj(int startj) {
        this.startj = startj;
    }

    public int getEndj() {
        return this.endj;
    }

    public void setEndj(int endj) {
        this.endj = endj;
    }

    public String getPlayer() {
        return this.player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getRooms() {
        return this.rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }


}
