package be.faros.flags.domain;

public class BasicScalableLayer extends ScalableLayer {
    private ScalableLayerType type;

    public ScalableLayerType getType() {
        return type;
    }

    public void setType(ScalableLayerType type) {
        this.type = type;
    }
}
