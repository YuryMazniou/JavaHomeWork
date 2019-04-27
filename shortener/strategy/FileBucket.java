package by.it.mazniou.shortener.strategy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path= Files.createTempFile(null,null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {}
    }
    //должен возвращать размер файла на который указывает path.
    public long getFileSize(){
        try {
            return Files.size(path);
        } catch (IOException e) {
            return 0;
        }
    }
    //должен сериализовывать переданный entry в файл. Учти, каждый entry может содержать еще один entry.
    public void putEntry(Entry entry){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path))){
            outputStream.writeObject(entry);
        }
        catch (IOException e){}
    }
    //должен забирать entry из файла. Если файл имеет нулевой размер, вернуть null.
    public Entry getEntry() {
        if(getFileSize()>0){
            try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))) {
                Entry e = null;
                e = (Entry) objectInputStream.readObject();
                return e;
            }catch(IOException | ClassNotFoundException e){}
        }
        return null;
    }
    //удалять файл на который указывает path.
    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {}
    }
}
