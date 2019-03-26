package by.it.mazniou.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap=new ConcurrentHashMap<>();

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage(String.format("Установлено новое соединение с удаленным адресом %s",socket.getRemoteSocketAddress()));
            String userName=null;
            try(Connection connection = new Connection(socket)){
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            }
            catch(IOException | ClassNotFoundException e){ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");}
            if(userName!=null){
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED,userName));
            }
            ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто.");
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            while(true){
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message=connection.receive();
                if(message.getType()==MessageType.USER_NAME&&!message.getData().isEmpty()&&!connectionMap.containsKey(message.getData())){
                    connectionMap.put(message.getData(),connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    return message.getData();
                }
            }
        }
        private void notifyUsers(Connection connection, String userName) throws IOException{
            for (String key:connectionMap.keySet()) {
                if(!key.equals(userName)){
                    connection.send(new Message(MessageType.USER_ADDED,key));
                }
            }
        }
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    String text = String.format("%s: %s", userName, message.getData());
                    sendBroadcastMessage(new Message(MessageType.TEXT, text));
                } else {
                    ConsoleHelper.writeMessage("Это не TEXT");
                }
            }
        }
    }
    public static void sendBroadcastMessage(Message message){
        for (String key :connectionMap.keySet()) {
            try {
                connectionMap.get(key).send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка.Сообщение не отправилось!");
            }
        }
    }

    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Введите номер порта...");
        int port = ConsoleHelper.readInt();
        try(ServerSocket serverSocket=new ServerSocket(port)){
            ConsoleHelper.writeMessage("Сервер запущен!");
            while (true){
                Socket socket=serverSocket.accept();
                Handler handler=new Handler(socket);
                handler.start();
            }
        }catch(IOException e){}
    }
}

