package com.albeedev.ptcproject.service;

import com.albeedev.ptcproject.listener.JDAEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class DiscordService {

    @Value("${discord.guildid}")
    String guildId;

    public CompletableFuture<Map<String, Object>> getUserInfo(String userId) {
        CompletableFuture<Map<String, Object>> future = new CompletableFuture<>();
        Map<String, Object> userInfo = new HashMap<>();
        List<String> clubRoleIdsToCheck = Arrays.asList("1218961836309741669",
                "1218963246531346483",
                "1218963403192926249",
                "1218963450294960199",
                "1218963619182674060");
        String adminRoleId="1219342102748598332";
        try {
            JDA jda = JDAEventListener.getJDA();
            Guild guild = jda.getGuildById(guildId);
            if (guild != null) {
                guild.retrieveMemberById(userId).onSuccess(member -> {
                    try {
                        userInfo.put("username",member.getEffectiveName());
                        List<Role> roles = member.getRoles();
                        userInfo.put("clubrole", "error");

                        for (Role role : roles) {
                            if(adminRoleId.equals(role.getId())){
                                userInfo.put("manager",true);
                            }
                            if (clubRoleIdsToCheck.contains(role.getId())) {
                                userInfo.put("clubrole",role.getName());
                            }
                        }
                        userInfo.putIfAbsent("manager", false);
                        userInfo.put("avatar",member.getEffectiveAvatar().getUrl());
                        future.complete(userInfo);
                    } catch (Exception e) {
                        future.completeExceptionally(e);
                    }
                }).queue();
            }
        } catch (Exception e) {
            future.completeExceptionally(e);
        }
        return future;
    }
}