package com.acensi.bot.http;


import com.acensi.bot.model.Action;
import com.acensi.bot.model.Round;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiGameResponse {

    @JsonProperty
    private String gameStatus;

    @JsonProperty
    private String gameId;

    @JsonProperty
    private Action playerOne;

    @JsonProperty
    private Action playerTwo;

    @JsonProperty
    private int roundNumber;

    public ApiGameResponse(String gameStatus, String gameId, Round round) {
        this.gameStatus = gameStatus;
        this.roundNumber = round.getNumber();
        this.gameId = gameId;
        this.playerOne = round.getActionPlayerOne();
        this.playerTwo = round.getActionPlayerTwo();
    }

}
