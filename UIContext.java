import java.awt.Point;

public interface UIContext {

  public abstract void drawLine(Point point1, Point point2 );

  public abstract void drawLabel(Label label);

  public abstract void drawLineSegments(Point[] points, boolean closed);

}