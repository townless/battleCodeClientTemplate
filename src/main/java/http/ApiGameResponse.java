package http;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import model.Action;
import model.Round;

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
