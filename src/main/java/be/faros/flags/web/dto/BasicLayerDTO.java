package be.faros.flags.web.dto;

import be.faros.flags.domain.BasicLayerType;

public class BasicLayerDTO extends SingleColourLayerDTO {
    private BasicLayerType type;

    public BasicLayerType getType() {
        return type;
    }

    public void setType(BasicLayerType type) {
        this.type = type;
    }
}
