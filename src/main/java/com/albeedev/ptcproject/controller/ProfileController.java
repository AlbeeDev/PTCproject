package com.albeedev.ptcproject.controller;
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

    @GetMapping("/access/profile")
    public String showProfile(@AuthenticationPrincipal OAuth2User oauth2User, Model model, RedirectAttributes redirectAttributes) {
        CompletableFuture<Map<String, Object>> userInfoFuture = discordService.getUserInfo(oauth2User.getAttribute("id"));
        try {
            Map<String, Object> userInfo = userInfoFuture.get();
            model.addAttribute("username", userInfo.get("username"));
            String clubrole= (String) userInfo.get("clubrole");
            if (clubrole.equals("error")){
                redirectAttributes.addFlashAttribute("error", "Your account doesnt have a valid club role");
                return "redirect:/home";
            }

            model.addAttribute("club",clubrole);
            model.addAttribute("avatar",userInfo.get("avatar"));
        } catch (InterruptedException | ExecutionException ignored) {}
        model.addAttribute("userId", oauth2User.getAttribute("id"));
        return "profile";
    }
}