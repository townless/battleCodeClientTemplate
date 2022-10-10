package com.acensi.bot.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Team {
    private String teamId;
    private String apiKey;

    private int score;

}
