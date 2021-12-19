import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RotateButton extends JButton implements ActionListener {

  
    protected View view;
    private RotateCommand rotateCommand;
    private UndoManager undoManager;
    private RotateCommand.Direction direction;
    
    public RotateButton(UndoManager undoManager, View jFrame, RotateCommand.Direction direction) {
      super("Rotate " + (direction == RotateCommand.Direction.LEFT ? "Left" : "Right"));
      addActionListener(this);
      view = jFrame;
      this.undoManager = undoManager;
      this.direction = direction;
    }
    public void actionPerformed(ActionEvent event) {
      rotateCommand = new RotateCommand(direction);
      undoManager.beginCommand(rotateCommand);
      undoManager.endCommand(rotateCommand);
    }
  }
  