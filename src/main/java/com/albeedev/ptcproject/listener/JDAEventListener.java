package com.albeedev.ptcproject.listener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JDAEventListener extends ListenerAdapter {

    private static JDA jda;

    @Value("${discord.bot.token}")
    private String token;

    @Override
    public void onReady(ReadyEvent event) {
        jda = event.getJDA();
    }

    public static JDA getJDA() {
        return jda;
    }

    public String getToken() {
        return token;
    }
}