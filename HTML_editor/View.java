package by.it.mazniou.HTML_editor;


import by.it.mazniou.HTML_editor.listeners.FrameListener;
import by.it.mazniou.HTML_editor.listeners.TabbedPaneChangeListener;
import by.it.mazniou.HTML_editor.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
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

    public void showAbout(){
        JOptionPane.showMessageDialog (null, "Message", "Title", JOptionPane.INFORMATION_MESSAGE);
    }

    public void update(){
        HTMLDocument document=controller.getDocument();
        htmlTextPane.setDocument(document);
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public boolean isHtmlTabSelected(){
        if(tabbedPane.getSelectedIndex()==0)return true;
        return false;
    }
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
        switch (e.getActionCommand()){
            case "Новый":controller.createNewDocument();break;
            case "Открыть":controller.openDocument();break;
            case "Сохранить":controller.saveDocument();break;
            case "Сохранить как...":controller.saveDocumentAs();break;
            case "Выход":this.exit();break;
            case "О программе":this.showAbout();break;
        }

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
    public void selectedTabChanged(){
        switch (tabbedPane.getSelectedIndex()){
            case 0:controller.setPlainText(plainTextPane.getText());break;
            case 1:plainTextPane.setText(controller.getPlainText());break;
        }
        this.resetUndo();
    }
}
