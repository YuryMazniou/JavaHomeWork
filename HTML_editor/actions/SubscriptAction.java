package by.it.mazniou.HTML_editor.actions;

import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

/*отвечает за стиль текста "Подстрочный знак"*/
public class SubscriptAction extends StyledEditorKit.StyledTextAction{
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public SubscriptAction() {
        super("Подстрочный знак");
    }

    public SubscriptAction(String nm) {
        super(nm);
    }
}
