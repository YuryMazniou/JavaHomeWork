package by.it.mazniou.HTML_editor;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document; //модель
    private File currentFile;  //поле которое отвечает за файл, который сейчас открыт в нашем редакторе (текущий файл)

    public HTMLDocument getDocument() {
        return document;
    }
    public void resetDocument(){
        if(document!=null){
            document.removeUndoableEditListener(view.getUndoListener());  //Удалять у текущего документа слушателя правок
        }
        document= (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }
    public void setPlainText(String text){
        /*Он будет записывать переданный текст с html тегами в документ document*/
        resetDocument();
        StringReader reader=new StringReader(text);
        HTMLEditorKit editorKit=new HTMLEditorKit();
        try {
            editorKit.read(reader,document,0);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        } catch (BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }
    public String getPlainText(){
        /*возвращает html текст со всеми тегами*/
        StringWriter writer=new StringWriter();
        HTMLEditorKit editorKit=new HTMLEditorKit();
        try {
            editorKit.write(writer,document,0,document.getLength());
        } catch (IOException e) {
            ExceptionHandler.log(e);
        } catch (BadLocationException e) {
            ExceptionHandler.log(e);
        }
        return String.valueOf(writer);
    }
    public Controller(View view) {
        this.view = view;
    }
    public void init(){createNewDocument();}
    public void exit(){ System.exit(0);}
    public static void main(String[] args) {
        View view1=new View();
        Controller controller=new Controller(view1);
        view1.setController(controller);
        view1.init();
        controller.init();
    }
    public void createNewDocument(){
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile=null;
    }
    public void openDocument(){
        try{
            view.selectHtmlTab();
            JFileChooser j = new JFileChooser();
            j.setFileFilter(new HTMLFileFilter());
            j.setDialogTitle("Open File");
            int result=j.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION ){
                currentFile=j.getSelectedFile();
                resetDocument();
                view.setTitle(currentFile.getName());
                try(FileReader reader=new FileReader(currentFile)){
                    HTMLEditorKit editorKit=new HTMLEditorKit();
                    editorKit.read(reader,document,0);
                }
                view.resetUndo();
            }
        }catch(Exception e){ExceptionHandler.log(e);}
    }
    public void saveDocument(){
        try{
            view.selectHtmlTab();
            if(currentFile!=null) {
                try (FileWriter writer = new FileWriter(currentFile)) {
                    HTMLEditorKit editor=new HTMLEditorKit();
                    editor.write(writer,document,0,document.getLength());
                }
            }
            else saveDocumentAs();
        }catch(Exception e){ExceptionHandler.log(e);}
    }
    public void saveDocumentAs(){
        try {
            view.selectHtmlTab();
            JFileChooser j = new JFileChooser();
            j.setFileFilter(new HTMLFileFilter());
            j.setDialogTitle("Save File");
            int result=j.showSaveDialog(view);
            if (result==JFileChooser.APPROVE_OPTION) {
                currentFile = j.getSelectedFile();
                view.setTitle(currentFile.getName());
                try (FileWriter writer = new FileWriter(currentFile)) {
                    HTMLEditorKit editor=new HTMLEditorKit();
                    editor.write(writer,document,0,document.getLength());
                } catch (IOException e) {
                    ExceptionHandler.log(e);
                }
            }
        }catch(Exception e){
            ExceptionHandler.log(e);
        }
    }
}
