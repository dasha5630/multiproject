package com.tdv.tech.multiproject;

import com.tdv.tech.multiproject.config.BotConfig;
import com.tdv.tech.multiproject.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private static final Logger LOG = LoggerFactory.getLogger(TelegramBot.class);

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
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText){
                case "/start" -> startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                case "/currency_list" -> currencyList(chatId);
                case "/help" -> help(chatId);
                default -> {
                    try {
                        sendMessage(chatId, CurrencyService.getCurrencyRate(messageText));
                    } catch (java.io.IOException | java.lang.InterruptedException e) {
                        sendMessage(chatId, "того что вы ищете нет");
                        LOG.info(e.getMessage());
                    }
                }
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

    private void help(Long chatId) {
        String answer = "/currency_list command";

        sendMessage(chatId, answer);
    }

    private void currencyList(Long chatId) {
        String answer = """
                                AUD
                                AZN
                                GBP
                                AMD
                                BYN
                                BGN
                                BRL
                                HUF
                                VND
                                HKD
                                GEL
                                DKK
                                AED
                                USD
                                EUR
                                EGP
                                INR
                                IDR
                                KZT
                                CAD
                                QAR
                                KGS
                                CNY
                                MDL
                                NZD
                                NOK
                                PLN
                                RON
                                XDR
                                SGD
                                TJS
                                THB
                                TRY
                                TMT
                                UZS
                                UAH
                                CZK
                                SEK
                                CHF
                                RSD
                                ZAR
                                KRW
                                JPY\
                """;
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error(String.format("Telegram api error %s", e));
        }
    }
}