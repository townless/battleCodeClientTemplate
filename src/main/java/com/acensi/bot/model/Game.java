package com.acensi.bot.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
@NoArgsConstructor
public class Game {

    String gameId;
    String idTeamOne;
    String idTeamTwo;
    String idWinner;

    int scorePlayerOne;
    int scorePlayerTwo;

    List<Round> roundList;
    String state;

}
