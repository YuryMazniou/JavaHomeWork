package by.it.mazniou.parserLogs;

import by.it.mazniou.parserLogs.query.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private List<String> listLogs=new ArrayList<>();
    private List<ParserLog.User> listUsers;
    private Set<String>setResult;

    public LogParser(Path logDir) {
        this.logDir = logDir;
        if(this.logDir!=null&&this.logDir.toFile().exists()) {
            listLogs = processFilesFromFolder(listLogs, logDir.toFile());
            parse();
        }
    }
    private void parse(){
        ParserLog parserLog = new ParserLog(listLogs);
        parserLog.parseLines();
        listUsers = parserLog.getUsers();
    }
    private List<String> processFilesFromFolder(List<String> logs, File file) {
        if(file.isFile() && file.getName().endsWith(".log")){
            logs.addAll(loadListOfLogsFromFiles(file));
        }
        else {
            File[] folderEntries = file.listFiles();
            for (File entry : folderEntries) {
                if (entry.isDirectory()) {
                    processFilesFromFolder(logs, entry);
                }
                if (entry.isFile() && entry.getName().endsWith(".log")) {
                    logs.addAll(loadListOfLogsFromFiles(entry));
                }
            }
        }
        return logs;
    }
    private boolean isDateInRange(Date check, Date after, Date before) {
        boolean fits = before == null || check.before(before) || check.equals(before);
        return fits && (after == null || check.after(after) || check.equals(after));
    }

    @Override
    public Set<Object> execute(String query) {
        if(query.equals("get ip")){
            Set<Object>map=new HashSet<>();
            Set<String>set=getUniqueIPs(null,null);
            for (String s:set) {
                map.add(new String(s));
            }
            return map;
        }
        if(query.equals("get user")){
            Set<Object>map=new HashSet<>();
            Set<String>set=getAllUsers();
            for (String s:set) {
                map.add(new String(s));
            }
            return map;}
        if(query.equals("get date")){
            Set<Object>map=new HashSet<>();
            for (ParserLog.User u:listUsers) {
                map.add(u.getDate());
            }
            return map;
        }
        if(query.equals("get event")){
            Set<Object>map=new HashSet<>();
            for (ParserLog.User u:listUsers) {
                map.add(u.getEvent());
            }
            return map;
        }
        if(query.equals("get status")){
            Set<Object>map=new HashSet<>();
            for (ParserLog.User u:listUsers) {
                map.add(u.getStatus());
            }
            return map;
        }
        return null;
    }

    //загружаем все строки логов из файлов в список
    private List<String> loadListOfLogsFromFiles(File entry) {
        List<String> listLogs = new ArrayList<>();
        try {
            listLogs = Files.readAllLines(entry.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listLogs;
    }
    //должен возвращать количество уникальных IP адресов за выбранный период
    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        setResult=new HashSet<>();
        if (!listLogs.isEmpty()) {
            if(after==null&&before==null){
                for (int i = 0; i < listUsers.size(); i++) {
                    setResult.add(listUsers.get(i).getIpAdress());
                }
                return setResult.size();
            }
            else {
                for (int i = 0; i < listUsers.size(); i++) {
                    if (isDateInRange(listUsers.get(i).getDate(), after, before)) {
                        setResult.add(listUsers.get(i).getIpAdress());
                    }
                }
                return setResult.size();
            }
        }
        return setResult.size();
    }
    //должен возвращать множество, содержащее все не повторяющиеся IP
    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        setResult=new HashSet<>();
        if (!listLogs.isEmpty()) {
            if(after==null&&before==null){
                for (int i = 0; i < listUsers.size(); i++) {
                    setResult.add(listUsers.get(i).getIpAdress());
                }
                return setResult;
            }
            else {
                for (int i = 0; i < listUsers.size(); i++) {
                    if (isDateInRange(listUsers.get(i).getDate(), after, before)) {
                        setResult.add(listUsers.get(i).getIpAdress());
                    }
                }
                return setResult;
            }
        }
        return setResult;
    }
    //должен возвращать IP, с которых работал переданный пользователь.
    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        setResult=new HashSet<>();
        if (!listLogs.isEmpty()) {
            if(after==null&&before==null){
                for (int i = 0; i < listUsers.size(); i++) {
                    if(user.equals(listUsers.get(i).getUserName())) {
                        setResult.add(listUsers.get(i).getIpAdress());
                    }
                }
                return setResult;
            }
            else {
                for (int i = 0; i < listUsers.size(); i++) {
                    if (user.equals(listUsers.get(i).getUserName()) && isDateInRange(listUsers.get(i).getDate(), after, before)) {
                        setResult.add(listUsers.get(i).getIpAdress());
                    }
                }
                return setResult;
            }
        }
        return setResult;
    }
    //должен возвращать IP, с которых было произведено переданное событие.
    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        setResult=new HashSet<>();
        if (!listLogs.isEmpty()) {
            if(after==null&&before==null){
                for (int i = 0; i < listUsers.size(); i++) {
                    if(event== listUsers.get(i).getEvent()) {
                        setResult.add(listUsers.get(i).getIpAdress());
                    }
                }
                return setResult;
            }
            else {
                for (int i = 0; i < listUsers.size(); i++) {
                    if (event == listUsers.get(i).getEvent() && isDateInRange(listUsers.get(i).getDate(), after, before)) {
                        setResult.add(listUsers.get(i).getIpAdress());
                    }
                }
                return setResult;
            }
        }
        return setResult;
    }
    //должен возвращать IP, события с которых закончилось переданным статусом.
    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        setResult=new HashSet<>();
        if (!listLogs.isEmpty()) {
            if(after==null&&before==null){
                for (int i = 0; i < listUsers.size(); i++) {
                    if(status== listUsers.get(i).getStatus()) {
                        setResult.add(listUsers.get(i).getIpAdress());
                    }
                }
                return setResult;
            }
            else {
                for (int i = 0; i < listUsers.size(); i++) {
                    if (status == listUsers.get(i).getStatus() && isDateInRange(listUsers.get(i).getDate(), after, before)) {
                        setResult.add(listUsers.get(i).getIpAdress());
                    }
                }
                return setResult;
            }
        }
        return setResult;
    }
    //должен возвращать всех пользователей.
    @Override
    public Set<String> getAllUsers() {
        setResult=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            for (ParserLog.User u:listUsers) {
                setResult.add(u.getUserName());
            }
        }
        return setResult;
    }
    //должен возвращать количество уникальных пользователей.
    @Override
    public int getNumberOfUsers(Date after, Date before) {
        setResult=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    setResult.add(u.getUserName());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (isDateInRange(u.getDate(), after, before)) setResult.add(u.getUserName());
                }
            }
        }
        return setResult.size();
    }
    //должен правильно возвращать количество уникальных событий за период
    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event>set=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(user.equals(u.getUserName()))set.add(u.getEvent());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (user.equals(u.getUserName()) && isDateInRange(u.getDate(), after, before))
                        set.add(u.getEvent());
                }
            }
        }
        return set.size();
    }
    //должен возвращать пользователей с определенным IP.
    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        setResult=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(ip.equals(u.getIpAdress()))setResult.add(u.getUserName());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (ip.equals(u.getIpAdress()) && isDateInRange(u.getDate(), after, before))
                        setResult.add(u.getUserName());
                }
            }
        }
        return setResult;
    }
    //должен возвращать пользователей, которые были залогинены.
    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        setResult=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()==Event.LOGIN)setResult.add(u.getUserName());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() == Event.LOGIN && isDateInRange(u.getDate(), after, before))
                        setResult.add(u.getUserName());
                }
            }
        }
        return setResult;
    }
    //должен возвращать пользователей, которые скачали плагин.
    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        setResult=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()==Event.DOWNLOAD_PLUGIN&&u.getStatus()==Status.OK)setResult.add(u.getUserName());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() == Event.DOWNLOAD_PLUGIN && u.getStatus() == Status.OK && isDateInRange(u.getDate(), after, before))
                        setResult.add(u.getUserName());
                }
            }
        }
        return setResult;
    }
    //должен возвращать пользователей, которые отправили сообщение.
    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        setResult=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if((u.getEvent()==Event.WRITE_MESSAGE)&&(u.getStatus()==Status.OK))setResult.add(u.getUserName());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if ((u.getEvent() == Event.WRITE_MESSAGE) && (u.getStatus() == Status.OK) && isDateInRange(u.getDate(), after, before))
                        setResult.add(u.getUserName());
                }
            }
        }
        return setResult;
    }
    //должен возвращать пользователей, которые решали любую задачу.
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        setResult=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()==Event.SOLVE_TASK)setResult.add(u.getUserName());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() == Event.SOLVE_TASK && isDateInRange(u.getDate(), after, before))
                        setResult.add(u.getUserName());
                }
            }
        }
        return setResult;
    }
    //должен возвращать пользователей, которые решали задачу с номером task.
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        setResult=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()==Event.SOLVE_TASK&&u.getNamberTask()==task)setResult.add(u.getUserName());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() == Event.SOLVE_TASK && isDateInRange(u.getDate(), after, before) && u.getNamberTask() == task)
                        setResult.add(u.getUserName());
                }
            }
        }
        return setResult;
    }
    //должен возвращать пользователей, которые решали любую задачу.
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        setResult=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()==Event.DONE_TASK)setResult.add(u.getUserName());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() == Event.DONE_TASK && isDateInRange(u.getDate(), after, before))
                        setResult.add(u.getUserName());
                }
            }
        }
        return setResult;
    }
    //должен возвращать пользователей, которые решали задачу с номером task.
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        setResult=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()==Event.DONE_TASK&&u.getNamberTask()==task)setResult.add(u.getUserName());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() == Event.DONE_TASK && isDateInRange(u.getDate(), after, before) && u.getNamberTask() == task)
                        setResult.add(u.getUserName());
                }
            }
        }
        return setResult;
    }
    //должен возвращать даты, когда определенный пользователь произвел определенное событие.
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date>result=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()==event&&u.getUserName().equals(user))result.add(u.getDate());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() == event && isDateInRange(u.getDate(), after, before) && u.getUserName().equals(user))
                        result.add(u.getDate());
                }
            }
        }
        return result;
    }
    //должен возвращать даты, когда любое событие не выполнилось (статус FAILED).
    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date>result=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getStatus()==Status.FAILED)result.add(u.getDate());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getStatus() == Status.FAILED && isDateInRange(u.getDate(), after, before))
                        result.add(u.getDate());
                }
            }
        }
        return result;
    }
    //должен возвращать даты, когда любое событие закончилось ошибкой (статус ERROR).
    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date>result=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getStatus()==Status.ERROR)result.add(u.getDate());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getStatus() == Status.ERROR && isDateInRange(u.getDate(), after, before))
                        result.add(u.getDate());
                }
            }
        }
        return result;
    }
    //должен возвращать дату, когда пользователь залогинился впервые за указанный период. Если такой даты в логах нет - null.
    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Date>result=new TreeSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getUserName().equals(user)&&u.getEvent()==Event.LOGIN)result.add(u.getDate());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getUserName().equals(user) && u.getEvent() == Event.LOGIN && isDateInRange(u.getDate(), after, before))
                        result.add(u.getDate());
                }
            }
        }
        if(result.size()>0){
            for (Date d:result) {
                return d;
            }
        }
        return null;
    }
    //должен возвращать дату, когда пользователь впервые попытался решить определенную задачу. Если такой даты в логах нет - null.
    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Set<Date>result=new TreeSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getUserName().equals(user)&&u.getEvent()==Event.SOLVE_TASK&&u.getNamberTask()==task)result.add(u.getDate());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getUserName().equals(user) && u.getEvent() == Event.SOLVE_TASK && isDateInRange(u.getDate(), after, before) && u.getNamberTask() == task)
                        result.add(u.getDate());
                }
            }
        }
        if(result.size()>0){
            for (Date d:result) {
                return d;
            }
        }
        return null;
    }
    //должен возвращать дату, когда пользователь впервые решил определенную задачу. Если такой даты в логах нет - null.
    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Set<Date>result=new TreeSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getUserName().equals(user)&&u.getEvent()==Event.DONE_TASK&&u.getNamberTask()==task)result.add(u.getDate());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getUserName().equals(user) && u.getEvent() == Event.DONE_TASK && isDateInRange(u.getDate(), after, before) && u.getNamberTask() == task)
                        result.add(u.getDate());
                }
            }
        }
        if(result.size()>0){
            for (Date d:result) {
                return d;
            }
        }
        return null;
    }
    //должен возвращать даты, когда пользователь написал сообщение
    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date>result=new TreeSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getUserName().equals(user)&&u.getEvent()==Event.WRITE_MESSAGE)result.add(u.getDate());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getUserName().equals(user) && u.getEvent() == Event.WRITE_MESSAGE && isDateInRange(u.getDate(), after, before))
                        result.add(u.getDate());
                }
            }
        }
        return result;
    }
    //должен возвращать даты, когда пользователь скачал плагин.
    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date>result=new TreeSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getUserName().equals(user)&&u.getEvent()==Event.DOWNLOAD_PLUGIN)result.add(u.getDate());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getUserName().equals(user) && u.getEvent() == Event.DOWNLOAD_PLUGIN && isDateInRange(u.getDate(), after, before))
                        result.add(u.getDate());
                }
            }
        }
        return result;
    }
    //должен возвращать количество событий за указанный период.
    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        Set<Event>set=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()!=null)set.add(u.getEvent());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() != null && isDateInRange(u.getDate(), after, before)) set.add(u.getEvent());
                }
            }
        }
        return set.size();
    }
    //должен возвращать все события за указанный период.
    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event>set=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()!=null)set.add(u.getEvent());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() != null && isDateInRange(u.getDate(), after, before)) set.add(u.getEvent());
                }
            }
        }
        return set;
    }
    //должен возвращать события, которые происходили с указанного IP.
    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event>set=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()!=null&&u.getIpAdress().equals(ip))set.add(u.getEvent());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() != null && isDateInRange(u.getDate(), after, before) && u.getIpAdress().equals(ip))
                        set.add(u.getEvent());
                }
            }
        }
        return set;
    }
    //должен возвращать события, которые инициировал определенный пользователь.
    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event>set=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()!=null&&u.getUserName().equals(user))set.add(u.getEvent());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() != null && isDateInRange(u.getDate(), after, before) && u.getUserName().equals(user))
                        set.add(u.getEvent());
                }
            }
        }
        return set;
    }
    //должен возвращать события, которые не выполнились.
    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event>set=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()!=null&&u.getStatus()==Status.FAILED)set.add(u.getEvent());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() != null && isDateInRange(u.getDate(), after, before) && u.getStatus() == Status.FAILED)
                        set.add(u.getEvent());
                }
            }
        }
        return set;
    }
    //должен возвращать события, которые завершились ошибкой.
    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event>set=new HashSet<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()!=null&&u.getStatus()==Status.ERROR)set.add(u.getEvent());
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() != null && isDateInRange(u.getDate(), after, before) && u.getStatus() == Status.ERROR)
                        set.add(u.getEvent());
                }
            }
        }
        return set;
    }
    //должен возвращать количество попыток решить определенную задачу.
    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int count=0;
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()==Event.SOLVE_TASK&&u.getNamberTask()==task)count++;
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() == Event.SOLVE_TASK && u.getNamberTask() == task && isDateInRange(u.getDate(), after, before))
                        count++;
                }
            }
        }
        return count;
    }
    //должен возвращать количество успешных решений определенной задачи.
    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int count=0;
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getEvent()==Event.DONE_TASK&&u.getNamberTask()==task)count++;
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getEvent() == Event.DONE_TASK && u.getNamberTask() == task && isDateInRange(u.getDate(), after, before))
                        count++;
                }
            }
        }
        return count;
    }
    //должен возвращать мапу (номер_задачи :количество_попыток_решить_ее).
    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer,Integer>map=new HashMap<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getNamberTask()!=0&&u.getEvent()==Event.SOLVE_TASK){
                        if(map.containsKey(u.getNamberTask())){
                            map.put(u.getNamberTask(),map.get(u.getNamberTask())+1);
                        }
                        else map.put(u.getNamberTask(),1);
                    }
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getNamberTask() != 0 && u.getEvent() == Event.SOLVE_TASK && isDateInRange(u.getDate(), after, before)) {
                        if (map.containsKey(u.getNamberTask())) {
                            map.put(u.getNamberTask(), map.get(u.getNamberTask()) + 1);
                        } else map.put(u.getNamberTask(), 1);
                    }
                }
            }
        }
        return map;
    }
    //должен возвращать мапу (номер_задачи :сколько_раз_ее_решили).
    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer,Integer>map=new HashMap<>();
        if(listUsers!=null&&!listUsers.isEmpty()){
            if(after==null&&before==null) {
                for (ParserLog.User u : listUsers) {
                    if(u.getNamberTask()!=0&&u.getEvent()==Event.DONE_TASK){
                        if(map.containsKey(u.getNamberTask())){
                            map.put(u.getNamberTask(),map.get(u.getNamberTask())+1);
                        }
                        else map.put(u.getNamberTask(),1);
                    }
                }
            }
            else {
                for (ParserLog.User u : listUsers) {
                    if (u.getNamberTask() != 0 && u.getEvent() == Event.DONE_TASK && isDateInRange(u.getDate(), after, before)) {
                        if (map.containsKey(u.getNamberTask())) {
                            map.put(u.getNamberTask(), map.get(u.getNamberTask()) + 1);
                        } else map.put(u.getNamberTask(), 1);
                    }
                }
            }
        }
        return map;
    }
}