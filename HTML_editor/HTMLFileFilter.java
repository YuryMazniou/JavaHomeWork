package by.it.mazniou.HTML_editor;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        return f.isDirectory()||(f.getName().toLowerCase().contains(".html")||f.getName().toLowerCase().contains(".htm"));
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
