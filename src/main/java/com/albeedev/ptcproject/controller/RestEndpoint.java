package com.albeedev.ptcproject.controller;

import com.albeedev.ptcproject.custom.FormData;
import com.albeedev.ptcproject.service.DataService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestEndpoint {
    @Autowired
    private DataService dataService;
    @PostMapping("/insertcar")
    public ResponseEntity<String> createResource(@RequestBody FormData formData, HttpSession session) {
        System.out.println(formData.getCarName());
        System.out.println(formData.getStars());
        System.out.println(formData.getCarRank());

        String discordid;
        discordid= (String) session.getAttribute("discordid");

        session.setAttribute("lastsection","garage");

        int playerid = dataService.getPlayerByDiscordId(discordid).getId();
        int carid = dataService.getIdFromCarName(formData.getCarName());
        dataService.setGarageItem(playerid, carid, formData.getStars(), formData.getCarRank());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
