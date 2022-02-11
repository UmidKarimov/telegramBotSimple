package bot.inline;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.InlineQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineQueryResultArticle;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UmidBotInline {
    private final TelegramBot bot = new TelegramBot(getToken());

    private String getToken() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\umidk\\IdeaProjects\\telegramBotSimple\\src\\main\\resources\\config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("token");
    }

    public void serve() {

        bot.setUpdatesListener(upd -> {

            upd.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }

    private void process(Update update) {
        Message message = update.message();
        CallbackQuery callbackQuery = update.callbackQuery();
        InlineQuery inlineQuery = update.inlineQuery();
        BaseRequest request = null;

        if (inlineQuery != null){
            InlineQueryResultArticle pipka = new InlineQueryResultArticle("pipka","Pipka","Ready for work!");
        }
        else if (message != null){
            long chatId = message.chat().id();
            request = new SendMessage(chatId, "Hello!");
        }

        if (request != null){
            bot.execute(request);
        }


    }
}
