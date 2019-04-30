package by.it.mazniou.parserLogs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserLog {
    private List<String>lineLog;
    private List<User>users;
    private Pattern parsePattern=Pattern.compile(
            "(?<ip>[\\d]+.[\\d]+.[\\d]+.[\\d]+)\\s" +
            "(?<name>[a-zA-Z ]+)\\s" +
            "(?<date>[\\d]+.[\\d]+.[\\d]+ [\\d]+:[\\d]+:[\\d]+)\\s" +
            "(?<event>[\\w]+)\\s?(" +
            "(?<taskNumber>[\\d]+)|)\\s" +
            "(?<status>[\\w]+)");

    public ParserLog(List<String> lineLog) {
        this.lineLog = lineLog;
    }
    public void parseLines(){
        if(lineLog!=null&&!lineLog.isEmpty()) {
            users=new ArrayList<>();
            for (String s : lineLog) {
                Matcher m=parsePattern.matcher(s);
                m.find();
                String ipAdress = m.group("ip").trim();
                String userName = m.group("name").trim();
                SimpleDateFormat dateformat=new SimpleDateFormat();
                dateformat.applyPattern("dd.MM.yyyy HH:mm:ss");
                Date date = null;
                try {
                    String line=m.group("date").trim();
                    date = dateformat.parse(line);
                } catch (ParseException e) {}
                Event e = null;
                switch (m.group("event").trim()) {
                    case "LOGIN": {
                        e = Event.LOGIN;
                        break;
                    }
                    case "DOWNLOAD_PLUGIN": {
                        e = Event.DOWNLOAD_PLUGIN;
                        break;
                    }
                    case "WRITE_MESSAGE": {
                        e = Event.WRITE_MESSAGE;
                        break;
                    }
                    case "SOLVE_TASK": {
                        e = Event.SOLVE_TASK;
                        break;
                    }
                    case "DONE_TASK": {
                        e = Event.DONE_TASK;
                        break;
                    }
                }
                Integer task = null;
                if (e == Event.SOLVE_TASK || e == Event.DONE_TASK) {
                    task = Integer.parseInt(m.group("taskNumber").trim());
                }
                Status status = null;
                switch (m.group("status").trim()) {
                    case "OK": {
                        status = Status.OK;
                        break;
                    }
                    case "FAILED": {
                        status = Status.FAILED;
                        break;
                    }
                    case "ERROR": {
                        status = Status.ERROR;
                        break;
                    }
                }
                users.add(new User(ipAdress,userName,date,e,task,status));
            }
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public class User {
        private String ipAdress;
        private String userName;
        private Date date;
        private Event event;
        private Integer namberTask;
        private Status status;

        public User(String ipAdress, String userName, Date date, Event event, Integer namberTask, Status status) {
            this.ipAdress = ipAdress;
            this.userName = userName;
            this.date = date;
            this.event = event;
            this.namberTask = namberTask;
            this.status = status;
        }

        public String getIpAdress() {
            return ipAdress;
        }

        public String getUserName() {
            return userName;
        }

        public Date getDate() {
            return date;
        }

        public Event getEvent() {
            return event;
        }

        public int getNamberTask() {
            return namberTask;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "User{" +
                    "ipAdress='" + ipAdress + '\'' +
                    ", userName='" + userName + '\'' +
                    ", date=" + date +
                    ", event=" + event +
                    ", namberTask=" + namberTask +
                    ", status=" + status +
                    '}';
        }
    }
}
