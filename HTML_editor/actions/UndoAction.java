package by.it.mazniou.HTML_editor.actions;

import by.it.mazniou.HTML_editor.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/*Класс отмены действия*/
public class UndoAction extends AbstractAction {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        view.undo();
    }
    public UndoAction(View view){
        this.view=view;
    }
}
