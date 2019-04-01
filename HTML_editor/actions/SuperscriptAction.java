package by.it.mazniou.HTML_editor.actions;

import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

/*Он будет отвечать за стиль "Надстрочный знак"*/
public class SuperscriptAction extends StyledEditorKit.StyledTextAction {
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public SuperscriptAction() {
        super("Надстрочный знак");
    }

    public SuperscriptAction(String nm) {
        super(nm);
    }
}
