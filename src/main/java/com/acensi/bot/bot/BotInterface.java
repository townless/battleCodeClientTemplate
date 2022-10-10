package com.acensi.bot.bot;

import com.acensi.bot.model.Round;

import java.util.List;

public interface BotInterface {


    default String choisirCoup(List<Round> roundList) {

        if (roundList.size() % 2 == 0) {
            return "SHOOT_UP";
        } else if (roundList.size() % 3 == 0) {
            return "HIDE";
        } else {
            return "RELOAD";
        }

    }
}
