package be.faros.flags.web.dto;

import be.faros.flags.domain.Emblem;

public class ChargeLayerDTO extends SingleColourLayerDTO {
    private Emblem emblem;

    public Emblem getEmblem() {
        return emblem;
    }

    public void setEmblem(Emblem emblem) {
        this.emblem = emblem;
    }
}
