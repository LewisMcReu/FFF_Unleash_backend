package be.faros.flags.domain;

public class BasicLayer extends SingleColourLayer {
    private BasicLayerType type;

    public BasicLayerType getType() {
        return type;
    }

    public void setType(BasicLayerType type) {
        this.type = type;
    }
}
