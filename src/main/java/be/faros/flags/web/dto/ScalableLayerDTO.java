package be.faros.flags.web.dto;

import be.faros.flags.domain.ScalableLayerType;

public abstract class ScalableLayerDTO extends SingleColourLayerDTO {
    private double scale; //from .5 to 2
    private ScalableLayerType type;

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public ScalableLayerType getType() {
        return type;
    }

    public void setType(ScalableLayerType type) {
        this.type = type;
    }
}
