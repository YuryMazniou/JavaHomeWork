package by.it.mazniou.parserLogs;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("D:\\programIT\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs\\example.log"));
        logParser.getNumberOfUniqueIPs(null, new Date());
        System.out.println(logParser.getNumberOfUniqueIPs(null, null));
        System.out.println(logParser.getUniqueIPs(null,null));
        System.out.println(logParser.getIPsForUser("Amigo",null,null));
        System.out.println(logParser.getIPsForEvent(Event.LOGIN,null,null));
        System.out.println(logParser.getIPsForStatus(Status.OK,null,null));
    }
}