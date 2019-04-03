package by.it.mazniou.HTML_editor.listeners;

import by.it.mazniou.HTML_editor.View;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*Этот класс сдушает и обрабатывает изменения состояния панели вкладок.*/

public class TabbedPaneChangeListener implements ChangeListener {
    private View view;
    @Override
    public void stateChanged(ChangeEvent e) {
        view.selectedTabChanged();
    }

    public TabbedPaneChangeListener(View view) {
        this.view = view;
    }
}
