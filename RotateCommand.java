import java.awt.Point;
import java.util.Enumeration;
import java.util.Iterator;

public class RotateCommand extends Command {
    public enum Direction {
        LEFT, RIGHT
    }
    private Enumeration<Item> items = null;
    private final Direction direction;

    public RotateCommand(Direction direction) {
        this.direction = direction;
    }
    
    public boolean undo() {
        if (direction == Direction.LEFT) execute(Direction.RIGHT);
        else if (direction == Direction.RIGHT) execute(Direction.LEFT);
        return true;
    }
    public boolean  redo() {
        execute();
        return true;
    }

    public void execute() {
        execute(direction);
    }

    private void execute(Direction dir){
        items = model.getSelectedItems();
        Iterator<Item> itr = items.asIterator();
        while(itr.hasNext()){
            Item item = itr.next();
            switch(dir){
                case LEFT:
                    rotateLeft(item);
                    break;
                case RIGHT:
                    rotateRight(item);
                    break;
            }            
        }
        model.setChanged();
    }



    private void rotateLeft(Item item){
        Point[] points = item.getPoints();
        Point axis = getRotationAxis(points);
        for (Point p : points){
            if (p == null) continue;
            rotateLeftAbout(p, axis);
        }
    }
    private void rotateRight(Item item){
        Point[] points = item.getPoints();
        Point axis = getRotationAxis(points);
        for (Point p : points){
            if (p == null) continue;
            rotateRightAbout(p, axis);
        }
    }

    private Point getRotationAxis(Point[] points){
        if (points.length <= 0) return new Point(0, 0);
        int count = 0;
        Point median = new Point();
        for (Point p : points){
            if (p == null) continue;
            count++;
            median.x += p.x;
            median.y += p.y;
        }
        median.x /= count;
        median.y /= count;
        return median;
    }

    private void rotateLeftAbout(Point vertex, Point axis) {
        Point result = new Point();
        result.x = axis.x + (vertex.y - axis.y);
        result.y = axis.y + (axis.x - vertex.x);
        vertex.x = result.x;
        vertex.y = result.y;
    }

    private void rotateRightAbout(Point vertex, Point axis) {
        Point result = new Point();
        result.x = axis.x + (axis.y - vertex.y);
        result.y = axis.y + (vertex.x - axis.x);
        vertex.x = result.x;
        vertex.y = result.y;
    }


  }
  