package com.acensi.bot.model;

import com.acensi.bot.utils.HitEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Action implements Serializable {

    @Serial
    private static final long serialVersionUID = -1832575266469435944L;
    private HitEnum hitPlayed;
    private HitEnum hitApplied;
    private String message;
    private TeamState teamState;
}
