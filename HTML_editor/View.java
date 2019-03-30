package by.it.mazniou.HTML_editor;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane=new JTabbedPane();
    private JTextPane htmlTextPane=new JTextPane();
    private JEditorPane plainTextPane=new JEditorPane();

    @Override
    public void actionPerformed(ActionEvent e) {

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
    public void initMenuBar(){}
    public void initEditor(){
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPane=new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML",jScrollPane);
        JScrollPane jScrollPane1=new JScrollPane(plainTextPane);
        tabbedPane.add("Текст",jScrollPane1);
        tabbedPane.setPreferredSize(new Dimension(300,300));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        Container c = getContentPane();
        c.add(tabbedPane,BorderLayout.CENTER);

    }
    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }
    public void selectedTabChanged(){}
}
