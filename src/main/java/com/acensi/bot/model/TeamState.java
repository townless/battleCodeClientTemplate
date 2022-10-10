package com.acensi.bot.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamState {

    private String teamId;
    private int number;
    private int pointOfLife;
    private int nbBullets;
    private int shieldLife;
    private int scorePointWon;

    @Override
    public String toString() {
        return "TeamState{" +
                "teamId='" + teamId + '\'' +
                ", number=" + number +
                ", pointOfLife=" + pointOfLife +
                ", nbBullets=" + nbBullets +
                ", shieldLife=" + shieldLife +
                ", scorePointWon=" + scorePointWon +
                '}';
    }
}
