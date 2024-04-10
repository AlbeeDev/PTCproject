package com.albeedev.ptcproject.controller;
import com.albeedev.ptcproject.dto.GarageItemDTO;
import com.albeedev.ptcproject.entity.Player;
import com.albeedev.ptcproject.service.DataService;
import com.albeedev.ptcproject.service.DiscordService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Controller
public class AccessController {
    @Autowired
    private DiscordService discordService;
    @Autowired
    private DataService dataService;

    Map<String, Object> userInfo = new HashMap<>();

    @GetMapping("/access/profile")
    public String showProfile(@AuthenticationPrincipal OAuth2User oauth2User, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        try {
            if (userInfo.isEmpty()) {
                CompletableFuture<Map<String, Object>> userInfoFuture = discordService.getUserInfo(oauth2User.getAttribute("id"));
                userInfo = userInfoFuture.get();
            }
            //?database

            String discordId = oauth2User.getAttribute("id");
            session.setAttribute("discordid", discordId);
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
            List<GarageItemDTO> plGarage = dataService.getAllGarageItemsForPlayer(pl.getUsername());
            model.addAttribute("carlist", plGarage);

            // ?adding the info for garage chart
            int playertotalcars = dataService.getTotalPlayerCars(pl.getUsername());
            int totalcars = dataService.getTotalCars();
            model.addAttribute("playertotalcars", playertotalcars);
            model.addAttribute("totalcars", totalcars);

            List<String> carnames = dataService.getAllCarNames();
            model.addAttribute("carNames", carnames);
            //?page
            model.addAttribute("username", userInfo.get("username"));
            String clubrole = (String) userInfo.get("clubrole");
            if (clubrole.equals("error")) {
                redirectAttributes.addFlashAttribute("error", "Your account doesnt have a valid club role");
                return "redirect:/home";
            }
            System.out.println(pl.getGameloftId());
            model.addAttribute("gameloftid", pl.getGameloftId());
            model.addAttribute("club", clubrole);
            model.addAttribute("manager", userInfo.get("manager"));
            model.addAttribute("avatar", userInfo.get("avatar"));
        } catch (InterruptedException | ExecutionException ignored) {
        }
        model.addAttribute("userId", oauth2User.getAttribute("id"));
        model.addAttribute("formData", new FormData());
        return "profile";
    }



    @GetMapping("/access/club")
    public String showClub(@AuthenticationPrincipal OAuth2User oauth2User, Model model, RedirectAttributes redirectAttributes) {
        return "club";
    }

    @GetMapping("/access/clash")
    public String showClash(@AuthenticationPrincipal OAuth2User oauth2User, Model model, RedirectAttributes redirectAttributes) {
        return "clash";
    }
}

