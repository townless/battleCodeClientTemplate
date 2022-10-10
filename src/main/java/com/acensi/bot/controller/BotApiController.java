package com.acensi.bot.controller;

import com.acensi.bot.bot.YourBot;
import com.acensi.bot.http.ApiResponse;
import com.acensi.bot.service.WebClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BotApiController {


    WebClientService webClientService;

    public BotApiController(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @PostMapping("/start/trainning/{level}")
    ResponseEntity<ApiResponse> startTrainning(@PathVariable Integer level) {

        ApiResponse apiResponse = webClientService.startGameTrainning(level).block();
        assert apiResponse != null;
        String gameId = apiResponse.getContent();
        YourBot yourBot = new YourBot(gameId, webClientService);
        yourBot.run();

        return ResponseEntity.status(200).body(new ApiResponse("Game " + gameId + " started"));
    }

    @PostMapping("/play/game/{gameId}")
    ResponseEntity<ApiResponse> playGame(@PathVariable String gameId) {

        YourBot yourBot = new YourBot(gameId, webClientService);
        yourBot.run();
        return ResponseEntity.status(200).body(new ApiResponse("Game " + gameId + " started"));
    }

}
