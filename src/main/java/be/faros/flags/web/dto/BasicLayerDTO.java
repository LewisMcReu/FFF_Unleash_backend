package be.faros.flags.web.dto;

import be.faros.flags.domain.Type;

public class BasicLayerDTO extends SingleColourLayerDTO {
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
