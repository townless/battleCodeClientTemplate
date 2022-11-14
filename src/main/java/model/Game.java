package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
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

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    Date creationDateTime;

}
