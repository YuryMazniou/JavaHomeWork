package by.it.mazniou.chat.client;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class ClientGuiModel {
    private final Set<String>allUserNames=new ConcurrentSkipListSet<>();
    private String newMessage;

    public Set<String> getAllUserNames() {
        return Collections.unmodifiableSet(allUserNames);
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }
    public void addUser(String newUserName){
        allUserNames.add(newUserName);
    }
    public void deleteUser(String userName){
        allUserNames.remove(userName);
    }
}
