package be.faros.flags.web.dto;

import be.faros.flags.domain.ScalableLayerType;

public class BasicScalableLayerDTO extends ScalableLayerDTO {
    private ScalableLayerType type;

    public ScalableLayerType getType() {
        return type;
    }

    public void setType(ScalableLayerType type) {
        this.type = type;
    }
}
