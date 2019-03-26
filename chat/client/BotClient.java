package by.it.mazniou.chat.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class BotClient extends Client {
    @Override
    protected SocketThread getSocketThread() {return new BotSocketThread();}

    @Override
    protected boolean shouldSendTextFromConsole() {return false;}

    @Override
    protected String getUserName() { return String.format("date_bot_%d", (int) (Math.random() * 100));}

    public class BotSocketThread extends SocketThread{
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            super.processIncomingMessage(message);
            String [] text;
            if(message.contains(": ")){
                text=message.split(": ");
                if(text[1].equals("дата")){
                    String inf=new SimpleDateFormat("d.MM.YYYY").format(new GregorianCalendar().getTime());
                    sendTextMessage(String.format("Информация для %s: %s",text[0],inf));
                }
                if(text[1].equals("день")){
                    String inf=new SimpleDateFormat("d").format(new GregorianCalendar().getTime());
                    sendTextMessage(String.format("Информация для %s: %s",text[0],inf));
                }
                if(text[1].equals("месяц")){
                    String inf=new SimpleDateFormat("MMMM").format(new GregorianCalendar().getTime());
                    sendTextMessage(String.format("Информация для %s: %s",text[0],inf));
                }
                if(text[1].equals("год")){
                    sendTextMessage(new SimpleDateFormat("YYYY").format(new GregorianCalendar().getTime()));
                    String inf=new SimpleDateFormat("MMMM").format(new GregorianCalendar().getTime());
                    sendTextMessage(String.format("Информация для %s: %s",text[0],inf));
                }
                if(text[1].equals("время")){
                    String inf=new SimpleDateFormat("H:mm:ss").format(new GregorianCalendar().getTime());
                    sendTextMessage(String.format("Информация для %s: %s",text[0],inf));
                }
                if(text[1].equals("час")){
                    String inf=new SimpleDateFormat("H").format(new GregorianCalendar().getTime());
                    sendTextMessage(String.format("Информация для %s: %s",text[0],inf));
                }
                if(text[1].equals("минуты")){
                    String inf=new SimpleDateFormat("m").format(new GregorianCalendar().getTime());
                    sendTextMessage(String.format("Информация для %s: %s",text[0],inf));
                }
                if(text[1].equals("секунды")){
                    String inf=new SimpleDateFormat("s").format(new GregorianCalendar().getTime());
                    sendTextMessage(String.format("Информация для %s: %s",text[0],inf));
                }
            }
        }
    }

    public static void main(String[] args) {
        BotClient botClient=new BotClient();
        botClient.run();
    }
}
