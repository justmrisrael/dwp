import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class View extends JFrame {

  private UIContext uiContext;
  private JPanel drawingPanel;
  private JPanel buttonPanel;
  private JButton lineButton;
  private JButton deleteButton;
  private JButton labelButton;
  private JButton selectButton;
  private JButton saveButton;
  private JButton openButton;
  private JButton undoButton;
  private JButton redoButton;
  private JButton triangleButton;
  private JButton rotateLeftButton;
  private JButton rotateRightButton;
  private static UndoManager undoManager;
  private String fileName;
  private static Model model;

  public UIContext getUI() {
    return uiContext;
  }

  public static void setModel(Model model) {
    View.model = model;
  }

  public static void setUndoManager(UndoManager undoManager) {
    View.undoManager = undoManager;
  }

  private class DrawingPanel extends JPanel {
  
    private MouseListener currentMouseListener;
    private KeyListener currentKeyListener;
    private FocusListener currentFocusListener;
  
    public DrawingPanel() {
      setLayout(null);
    }
  
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      (NewSwingUI.getInstance()).setGraphics(g);
      g.setColor(Color.BLUE);
      Enumeration<Item> enumeration = model.getItems();
      while (enumeration.hasMoreElements()) {
        ((Item) enumeration.nextElement()).render();
      }
      g.setColor(Color.RED);
      enumeration = model.getSelectedItems();
      while (enumeration.hasMoreElements()) {
        ((Item) enumeration.nextElement()).render();
      }
    }
    
    public void addMouseListener(MouseListener newListener) {
      removeMouseListener(currentMouseListener);
      currentMouseListener =  newListener;
      super.addMouseListener(newListener);
    }
    
    public void addKeyListener(KeyListener newListener) {
      removeKeyListener(currentKeyListener);
      currentKeyListener =  newListener;
      super.addKeyListener(newListener);
    }
    
    public void addFocusListener(FocusListener newListener) {
      removeFocusListener(currentFocusListener);
      currentFocusListener =  newListener;
      super.addFocusListener(newListener);
    }
  
  }
  
  public void setFileName(String fileName) {
    this.fileName = fileName;
    setTitle("Drawing Program 1.1  " + fileName);
  }
  
  public String getFileName() {
    return fileName;
  }

  public View() {
    super("Drawing Program 1.1  Untitled");
    fileName = null;
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
        System.exit(0);
      }
    });
    Model.setUI(NewSwingUI.getInstance());
    drawingPanel = new DrawingPanel();
    buttonPanel = new JPanel();
    Container contentpane = getContentPane();
    contentpane.add(buttonPanel, "North");
    contentpane.add(drawingPanel);
    lineButton= new LineButton(undoManager, this, drawingPanel);
    labelButton = new LabelButton(undoManager, this, drawingPanel);
    selectButton= new SelectButton(undoManager, this, drawingPanel);
    deleteButton= new DeleteButton(undoManager);
    saveButton= new SaveButton(undoManager, this);
    openButton= new OpenButton(undoManager, this);
    undoButton = new UndoButton(undoManager);
    redoButton = new RedoButton(undoManager);
    triangleButton = new TriangleButton(undoManager, this, drawingPanel);
    rotateLeftButton = new RotateButton(undoManager, this, RotateCommand.Direction.LEFT);
    rotateRightButton = new RotateButton(undoManager, this, RotateCommand.Direction.RIGHT);
    buttonPanel.add(lineButton);
    buttonPanel.add(triangleButton);
    buttonPanel.add(labelButton);
    buttonPanel.add(selectButton);
    buttonPanel.add(rotateLeftButton);
    buttonPanel.add(rotateRightButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(saveButton);
    buttonPanel.add(openButton);
    buttonPanel.add(undoButton);
    buttonPanel.add(redoButton);
    this.setSize(600, 400);
  }

  public void refresh() {
    drawingPanel.repaint();
  }

  public static Point mapPoint(Point point){
    return point;
  }
  
}