package com.albeedev.ptcproject.controller;
import com.albeedev.ptcproject.entity.Player;
import com.albeedev.ptcproject.service.DataService;
import com.albeedev.ptcproject.service.DiscordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Controller
public class ProfileController {
    @Autowired
    private DiscordService discordService;
    @Autowired
    private DataService dataService;

    @GetMapping("/access/profile")
    public String showProfile(@AuthenticationPrincipal OAuth2User oauth2User, Model model, RedirectAttributes redirectAttributes) {
        CompletableFuture<Map<String, Object>> userInfoFuture = discordService.getUserInfo(oauth2User.getAttribute("id"));
        try {
            Map<String, Object> userInfo = userInfoFuture.get();
            //?database

            String discordId = oauth2User.getAttribute("id");
            System.out.println((String) discordId);
            Player pl = dataService.getPlayerByDiscordId(discordId);
            if (pl == null) {
                pl = new Player();
                pl.setDiscordId(discordId);
                System.out.println("user not found");
                //pl.setCurrentClub(); //hard intersecting query WIp
                pl.setGameloftId("");
                pl.setUsername((String) userInfo.get("username"));
                dataService.savePlayer(pl);
            } else {
                System.out.println("user found: " + pl.getUsername());
            }


            //?page
            model.addAttribute("username", userInfo.get("username"));
            String clubrole= (String) userInfo.get("clubrole");
            if (clubrole.equals("error")){
                redirectAttributes.addFlashAttribute("error", "Your account doesnt have a valid club role");
                return "redirect:/home";
            }
            System.out.println(pl.getGameloftId());
            model.addAttribute("gameloftid",pl.getGameloftId());
            model.addAttribute("club",clubrole);
            model.addAttribute("manager",userInfo.get("manager"));
            model.addAttribute("avatar",userInfo.get("avatar"));
        } catch (InterruptedException | ExecutionException ignored) {}
        model.addAttribute("userId", oauth2User.getAttribute("id"));
        return "profile";
    }
}