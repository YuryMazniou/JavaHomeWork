package by.it.mazniou.HTML_editor.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class UndoMenuListener implements MenuListener {
    private View view;
    private JMenuItem undoMenuItem;  //"Отменить"
    private JMenuItem redoMenuItem;  //"Вернуть"
    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem){
        this.view=view;
        this.undoMenuItem=undoMenuItem;
        this.redoMenuItem=redoMenuItem;
    }
    @Override
    public void menuSelected(MenuEvent e) {
        if(view.canUndo())undoMenuItem.setEnabled(true); // canUndo() можем ли мы отменить действие
        else undoMenuItem.setEnabled(false);
        if(view.canRedo())redoMenuItem.setEnabled(true);  //canRedo() можем ли мы вернуть действие
        else redoMenuItem.setEnabled(false);
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
