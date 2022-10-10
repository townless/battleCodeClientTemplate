package com.acensi.bot.bot;

import com.acensi.bot.http.ApiResponse;
import com.acensi.bot.model.Game;
import com.acensi.bot.model.Round;
import com.acensi.bot.service.WebClientService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class AbstractBot implements Runnable, BotInterface {

    private String idPartie;

    private WebClientService webClientService;

    public AbstractBot(String idPartie, WebClientService webClientService) {
        this.idPartie = idPartie;
        this.webClientService = webClientService;
    }

    @Override
    public void run() {

        Game game = webClientService.getGame(idPartie).block();

        while (game == null || game.getState().equals("STARTED")) {

            try {

                boolean canPlay = false;
                while (!canPlay) {

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    ApiResponse response = webClientService.canPlay(idPartie).block();
                    assert response != null;
                    canPlay = "YOU CAN".equals(response.getContent());
                    log.info("partie {} Reponse CanPlay : {}", idPartie, response.getContent());
                }

                assert game != null;
                List<Round> roundList = game.getRoundList();

                webClientService.play(idPartie, choisirCoup(roundList)).block();

            } catch (Exception e) {
                log.error(e.getMessage());
            }

            game = webClientService.getGame(idPartie).block();
        }
    }
}
