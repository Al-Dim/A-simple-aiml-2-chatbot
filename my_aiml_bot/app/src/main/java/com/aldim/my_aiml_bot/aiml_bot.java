package com.aldim.my_aiml_bot;

import android.content.Context;

import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.Graphmaster;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.PCAIMLProcessorExtension;
import org.alicebot.ab.Timer;

public class aiml_bot {

    public Bot bot;
    public static Chat chat;

    public String aiml_bots_directory(Context context){
        ZipFileExtraction zzz = new ZipFileExtraction();
        return zzz.ExtractBot(context);
    }

    public void aiml_bot_init (String strBotName, Context context) {
        MagicStrings.root_path = aiml_bots_directory(context);
        System.out.println("Working Directory = " + MagicStrings.root_path);
        AIMLProcessor.extension =  new PCAIMLProcessorExtension();
        bot = new Bot(strBotName, MagicStrings.root_path, "chat");
        chat = new Chat(bot);
    }

    public String aiml_bot_request (String strMessage){
        MagicBooleans.trace_mode = false;
        Graphmaster.enableShortCuts = true;
        Timer timer = new Timer();
        return chat.multisentenceRespond(strMessage);
    }

}
