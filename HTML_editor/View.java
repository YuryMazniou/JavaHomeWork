package by.it.mazniou.HTML_editor;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane=new JTabbedPane();
    private JTextPane htmlTextPane=new JTextPane();
    private JEditorPane plainTextPane=new JEditorPane();
    private UndoManager undoManager=new UndoManager();
    private UndoListener undoListener=new UndoListener(undoManager);

    public UndoListener getUndoListener() {
        return undoListener;
    }
    public void resetUndo(){
        undoManager.discardAllEdits();  //сбрасывать все правки в менеджере undoManager
    }
    public void undo(){
        try{undoManager.undo();}
        catch(Exception e){ExceptionHandler.log(e);}
    }
    public void redo(){
        try{undoManager.redo();}
        catch(Exception e){ExceptionHandler.log(e);}
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public View(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Устанавливает системные настройки
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
    public boolean canUndo(){
        return undoManager.canUndo(); //можем ли мы отменить действие
    }
    public boolean canRedo(){
        return undoManager.canRedo();  //можем ли мы вернуть действие
    }
    public void init(){
        initGui();
        FrameListener lis=new FrameListener(this);
        addWindowListener(lis);
        setVisible(true);
    }
    public void exit(){ controller.exit();}
    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void initMenuBar(){
        JMenuBar bar=new JMenuBar();
        MenuHelper.initFileMenu(this,bar);
        MenuHelper.initEditMenu(this,bar);
        MenuHelper.initStyleMenu(this,bar);
        MenuHelper.initAlignMenu(this,bar);
        MenuHelper.initColorMenu(this,bar);
        MenuHelper.initFontMenu(this,bar);
        MenuHelper.initHelpMenu(this,bar);
        getContentPane().add(bar,BorderLayout.NORTH);
    }
    public void initEditor(){
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPane=new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML",jScrollPane);
        JScrollPane jScrollPane1=new JScrollPane(plainTextPane);
        tabbedPane.add("Текст",jScrollPane1);
        tabbedPane.setPreferredSize(new Dimension(300,300));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        getContentPane().add(tabbedPane,BorderLayout.CENTER);
    }
    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }
    public void selectedTabChanged(){}
}
