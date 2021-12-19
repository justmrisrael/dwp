import java.awt.Point;

public class TriangleCommand extends Command {


    private Triangle triangle;

    public TriangleCommand(){
        triangle = new Triangle();
    }

    public void setPoint1(Point p){
        triangle.setPoint1(p);
    }
    public void setPoint2(Point p){
        triangle.setPoint2(p);
        model.setChanged();
    }
    public void setPoint3(Point p){
        triangle.setPoint3(p);
        model.setChanged();
    }

    @Override
    public boolean undo() {
        model.removeItem(triangle);
        return false;
    }

    @Override
    public boolean redo() {
        execute();
        return false;
    }

    @Override
    public void execute() {
        model.addItem(triangle);
    }

    public boolean end() {
        if (triangle.getPoint1() == null) {
            undo();
            return false;
        }
        if (triangle.getPoint2() == null || triangle.getPoint3() == null) {
            triangle.setPoint2(triangle.getPoint1());
            triangle.setPoint3(triangle.getPoint1());
        }
        return true;
    }
    
}