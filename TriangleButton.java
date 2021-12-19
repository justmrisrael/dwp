import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TriangleButton   extends JButton implements ActionListener {

    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private TriangleCommand triCommand;
    private UndoManager undoManager;
  
    public TriangleButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
      super("Triangle");
      this.undoManager = undoManager;
      addActionListener(this);
      view = jFrame;
      drawingPanel = jPanel;
      mouseHandler = new MouseHandler();
    }
  
    public void actionPerformed(ActionEvent event) {
      view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

      drawingPanel.addMouseListener(mouseHandler);

      drawingPanel.addMouseMotionListener(mouseHandler);
    }
  
    private class MouseHandler extends MouseAdapter {
      private int pointCount = 0;
  
      public void mouseClicked(MouseEvent event) {
        if (++pointCount == 1) {
            triCommand = new TriangleCommand();
            triCommand.setPoint1(View.mapPoint(event.getPoint()));
            undoManager.beginCommand(triCommand);          
        } else if (pointCount == 2) {
          triCommand.setPoint2(View.mapPoint(event.getPoint()));
        }else if (pointCount == 3){
          triCommand.setPoint3(View.mapPoint(event.getPoint()));
          drawingPanel.removeMouseListener(this);
          view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
          undoManager.endCommand(triCommand);
        }
      }
      public void mouseMoved(MouseEvent event) {
        if (pointCount < 1) return;
        Point p = View.mapPoint(event.getPoint());
        switch(pointCount){
          case 1:
            triCommand.setPoint2(p);
            break;
          case 2:
            triCommand.setPoint3(p);
            break;
        }
      }
    }
  
  }