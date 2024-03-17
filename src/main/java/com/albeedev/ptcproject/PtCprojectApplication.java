package com.albeedev.ptcproject;

import com.albeedev.ptcproject.listener.JDAEventListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PtCprojectApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(PtCprojectApplication.class, args);
        JDAEventListener eventListener = context.getBean(JDAEventListener.class);

        JDABuilder builder = JDABuilder.createDefault(eventListener.getToken());
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.addEventListeners(eventListener);
        builder.build();
    }


}
