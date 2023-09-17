package com.tdv.tech.multiproject;

import com.tdv.tech.multiproject.config.BotConfig;
import com.tdv.tech.multiproject.model.CurrencyModel;
import com.tdv.tech.multiproject.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        CurrencyModel currencyModel = new CurrencyModel();
        String currency = "";

        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText){
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/currency_list":
                    currencyList(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/help":
                    help(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    try {
                        currency = CurrencyService.getCurrencyRate(messageText);

                    } catch (Exception e) {
                        sendMessage(chatId, "того что вы ищете нет");
                        throw new RuntimeException(e);
                    }
                    sendMessage(chatId, currency);
            }
        }

    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Hi, " + name + ", nice to meet you!" + "\n" +
                "Enter the currency whose official exchange rate" + "\n" +
                "you want to know in relation to RUB." + "\n" +
                "For example: USD";
        sendMessage(chatId, answer);
    }

    private void help(Long chatId, String name) {
        String answer = "/currency_list command";

        sendMessage(chatId, answer);
    }

    private void currencyList(Long chatId, String name) {
        String answer = "                AUD\n" +
                "                AZN\n" +
                "                GBP\n" +
                "                AMD\n" +
                "                BYN\n" +
                "                BGN\n" +
                "                BRL\n" +
                "                HUF\n" +
                "                VND\n" +
                "                HKD\n" +
                "                GEL\n" +
                "                DKK\n" +
                "                AED\n" +
                "                USD\n" +
                "                EUR\n" +
                "                EGP\n" +
                "                INR\n" +
                "                IDR\n" +
                "                KZT\n" +
                "                CAD\n" +
                "                QAR\n" +
                "                KGS\n" +
                "                CNY\n" +
                "                MDL\n" +
                "                NZD\n" +
                "                NOK\n" +
                "                PLN\n" +
                "                RON\n" +
                "                XDR\n" +
                "                SGD\n" +
                "                TJS\n" +
                "                THB\n" +
                "                TRY\n" +
                "                TMT\n" +
                "                UZS\n" +
                "                UAH\n" +
                "                CZK\n" +
                "                SEK\n" +
                "                CHF\n" +
                "                RSD\n" +
                "                ZAR\n" +
                "                KRW\n" +
                "                JPY";
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }
}