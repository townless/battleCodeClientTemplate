package com.acensi.bot.service;

import com.acensi.bot.http.ApiResponse;
import com.acensi.bot.model.Game;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {

    private final WebClient webClient;

    private String authorization;

    private String botId;

    public WebClientService(@Value("${battlecode.game.server.url}") String urlBattleCodeServer,
                            @Value("${your.api.id}")  String botId,
                            @Value("${your.api.key}") String token) {
        this.authorization = token;
        this.botId = botId;

        this.webClient =  WebClient.builder()
                .baseUrl(urlBattleCodeServer)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<Game> getGame(String idGame) {
        return this.webClient.get().uri("/game/" + idGame + "/allRounds")
                .header("Authorization", authorization)
                .retrieve()
                .bodyToMono(Game.class);
    }

    public Mono<ApiResponse> play(String idGame, String coup) {
        return this.webClient.post().uri("/game/duel/" + idGame + "/" + botId + "/play/" + coup)
                .header("Authorization", authorization)
                .retrieve()
                .bodyToMono(ApiResponse.class);
    }

    public Mono<ApiResponse> canPlay(String idGame) {
        return this.webClient.get().uri("/game/duel/" + idGame + "/" + botId + "/canPlay/")
                .header("Authorization", authorization)
                .retrieve()
                .bodyToMono(ApiResponse.class);
    }


    public Mono<ApiResponse> startGameTrainning(int level) {
        return this.webClient.get().uri("/trainning/new/1/" + level)
                .header("Authorization", authorization)
                .retrieve()
                .bodyToMono(ApiResponse.class);
    }



}
