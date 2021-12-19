import java.awt.Point;
import java.io.Serializable;

public abstract class Item implements Serializable {

  protected static UIContext uiContext;
  
  public static void setUIContext(UIContext uiContext) {
    Item.uiContext = uiContext;
  }
  
  public abstract boolean includes(Point point);
  
  protected double distance(Point point1, Point point2) {
    double xDifference = point1.getX() - point2.getX();
    double yDifference = point1.getY() - point2.getY();
    return ((double) (Math.sqrt(xDifference * xDifference + yDifference * yDifference)));
  }
  
  public void render() {
    
  }

  public abstract Point[] getPoints();
  
}