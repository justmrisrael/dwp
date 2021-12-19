import java.awt.Point;

public class Triangle extends Item {

    private Point point1;
    private Point point2;
    private Point point3;

    public Triangle(){
        point1 = null;
        point2 = null;
        point3 = null;
    }

    public void render() {
        uiContext.drawLineSegments(getPoints(), true);
    }

    public void setPoint1(Point point) {
        point1 = point;
    }

    public void setPoint2(Point point) {
        point2 = point;
    }

    public void setPoint3(Point point) {
        point3 = point;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public Point getPoint3() {
        return point3;
    }

    public String toString() {
        return "Triangle comprising [" + point1 + ", " + point2 + ", " + point3 + "]";
    }

    @Override
    public boolean includes(Point point) {
        return ((distance(point, point1 ) < 10.0) || (distance(point, point2)< 10.0) || (distance(point, point3)< 10.0));
    }

    @Override
    public Point[] getPoints() {
        return new Point[]{point1, point2, point3};
    }
    
}