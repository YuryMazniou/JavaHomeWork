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
        System.out.println(logParser.getNumberOfAttemptToSolveTask(18,null,null));
        System.out.println(logParser.execute("get ip"));
        System.out.println(logParser.execute("get user"));
        System.out.println(logParser.execute("get event for date = \"30.01.2014 12:56:22\""));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\""));
        System.out.println(logParser.execute("get date for user = \"Amigo\""));
        System.out.println(logParser.execute("get event for user = \"Eduard Petrovich Morozko\""));
        System.out.println(logParser.execute("get status for user = \"Eduard Petrovich Morozko\""));
    }
}