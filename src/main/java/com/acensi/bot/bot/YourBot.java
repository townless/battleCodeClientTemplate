package com.acensi.bot.bot;

import com.acensi.bot.model.Round;
import com.acensi.bot.service.WebClientService;
import com.acensi.bot.utils.HitEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class YourBot extends AbstractBot {


    public YourBot(String idPartie, WebClientService webClientService) {
        super(idPartie, webClientService);
    }

    @Override
    public String choisirCoup(List<Round> roundList) {

        // Implementer votre logique ICI //

        Round lastRound = roundList.get(roundList.size() - 1);
        HitEnum hitApplied = lastRound.getActionPlayerOne().getHitApplied();
        HitEnum hitPlayed = lastRound.getActionPlayerOne().getHitPlayed();

        log.info("Lors du dernier round le joueur 1 à voulu jouer {} et le coup {} a été appliqué", hitPlayed, hitApplied);
        log.info("Etat du joueur 1 : {}", lastRound.getActionPlayerOne().getTeamState());
        return "HIDE";
    }


}
