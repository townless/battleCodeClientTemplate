package com.acensi.bot.model;

import com.acensi.bot.utils.HitEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Round implements Serializable {

    @Serial
    private static final long serialVersionUID = -8560033431500349240L;
    private Action actionPlayerOne;
    private Action actionPlayerTwo;

    private String idPartie;

    private Long idRound;
    private int number;




}
