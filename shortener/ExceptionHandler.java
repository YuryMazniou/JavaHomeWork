package by.it.mazniou.shortener;

public class ExceptionHandler {
    public static void log(Exception e){
        Helper.printMessage(e.getMessage());
    }
}
