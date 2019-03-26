package by.it.mazniou.chat.client;

import by.it.mazniou.chat.Connection;
import by.it.mazniou.chat.ConsoleHelper;
import by.it.mazniou.chat.Message;
import by.it.mazniou.chat.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected=false;

    protected String getServerAddress(){
        ConsoleHelper.writeMessage("Введите адрес сервера...");
        return ConsoleHelper.readString();
    }
    protected int getServerPort(){
        ConsoleHelper.writeMessage("Введите номер порта сервера...");
        return ConsoleHelper.readInt();
    }
    protected String getUserName() {
        ConsoleHelper.writeMessage("Введите ваше имя...");
        return ConsoleHelper.readString();
    }
    protected boolean shouldSendTextFromConsole(){return true;}
    protected SocketThread getSocketThread(){
        return new SocketThread();
    }
    protected void sendTextMessage(String text){
        try {
            connection.send(new Message(MessageType.TEXT,text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Во время отправки сообщения произошла ошибка!");
            clientConnected=false;
        }
    }

    public class SocketThread extends Thread{
        protected void processIncomingMessage(String message){ConsoleHelper.writeMessage(message);}
        protected void informAboutAddingNewUser(String userName){
            String text=String.format("Уастник с именем %s присоединился к чату",userName);
            ConsoleHelper.writeMessage(text);
        }
        protected void informAboutDeletingNewUser(String userName){
            String text=String.format("Участник с именем %s покинул чат",userName);
            ConsoleHelper.writeMessage(text);
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected=clientConnected;
            synchronized (Client.this){
                Client.this.notify();
            }
        }
        protected void clientHandshake() throws IOException, ClassNotFoundException{
            while (true) {
                Message message=connection.receive();
                if(message.getType()==MessageType.NAME_REQUEST){connection.send(new Message(MessageType.USER_NAME,getUserName()));}
                else {
                    if(message.getType()==MessageType.NAME_ACCEPTED)
                    {
                    notifyConnectionStatusChanged(true);
                    break;
                    }
                    else throw new IOException("Unexpected MessageType");
                }
            }
        }
        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            while(true){
                Message message=connection.receive();
                if(message.getType()==MessageType.TEXT)processIncomingMessage(message.getData());
                else{
                    if(message.getType()==MessageType.USER_ADDED)informAboutAddingNewUser(message.getData());
                    else{
                        if(message.getType()==MessageType.USER_REMOVED)informAboutDeletingNewUser(message.getData());
                        else throw new IOException("Unexpected MessageType");
                    }
                }
            }
        }
        @Override
        public void run() {
            try {
                Socket socket = new Socket(getServerAddress(), getServerPort());
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }catch(IOException | ClassNotFoundException e){notifyConnectionStatusChanged(false);}
        }
    }

    public static void main(String[] args) {
        Client client=new Client();
        client.run();
    }

    public void run(){
        SocketThread socketThread=getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Во время ожидания клиента возникла ошибка");
            }
        }
        if(clientConnected){ ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");}
        else ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        while (clientConnected) {
            String text = ConsoleHelper.readString();
            if (text.equals("exit")) break;
            if (shouldSendTextFromConsole()) sendTextMessage(text);
        }
    }
}
