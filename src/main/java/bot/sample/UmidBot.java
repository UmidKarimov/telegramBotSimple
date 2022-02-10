package bot.sample;

import org.telegram.telegrambots.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

public class UmidBot extends TelegramLongPollingBot {


    @Override
    public String getBotUsername() {
        return "umid_test_1_bot";
    }

    @Override
    public String getBotToken() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\Umidk\\IdeaProjects\\telegramBot\\src\\main\\resources\\config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("token");
    }

    @Override
    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();
        User user = update.getMessage().getFrom();
        SendMessage sendMessage = new SendMessage();

        if (command.equals("/myname")) {
            sendMessage.setText(user.getFirstName());
        } else if (command.equals("/mylastname")) {
            sendMessage.setText(user.getLastName());
        } else if (command.equals("/myfullname")) {
            sendMessage.setText(user.getLastName() + " " + user.getFirstName());
        } else if (command.equals("/time")) {
            sendMessage.setText(getTime());
        } else if (command.equals("/date")) {
            sendMessage.setText(getDate());
        } else if (command.equals("/datetime")) {
            sendMessage.setText(getDate() + " " + getTime());
        } else {
            sendMessage.setText("Unrecognized command");
        }

        sendMessage.setChatId(update.getMessage().getChatId());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String getDate() {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date());
    }

    private String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}
