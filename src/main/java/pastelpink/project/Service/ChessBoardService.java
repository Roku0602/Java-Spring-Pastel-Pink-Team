package pastelpink.project.Service;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;

import org.springframework.stereotype.Service;

import pastelpink.project.Model.ChessNode;

@Service
public class ChessBoardService {

    public static void rotateChessNodes(List<ChessNode> chessNodeList, int angleForTeam1, int angleForTeam2,String team) {
        for (ChessNode chessNode : chessNodeList) {
            int teamAngle = ("den".equals(team)) ? angleForTeam1 : angleForTeam2;

            // Xoay tọa độ chessNode
            Point2D rotatedCoordinates = rotatePoint(chessNode.getTop(), chessNode.getLeft(), teamAngle);
            chessNode.setTop((int) rotatedCoordinates.getX());
            chessNode.setLeft((int) rotatedCoordinates.getY());
        }
        System.out.println("Đã xoay xong");
    }

    private static Point2D rotatePoint(int x, int y, int angle) {
        double xDouble = (double) x;
        double yDouble = (double) y;

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), xDouble, yDouble);

        Point2D rotatedPoint = new Point2D.Double();
        transform.transform(new Point2D.Double(xDouble, yDouble), rotatedPoint);

        return rotatedPoint;
    }
    
}
