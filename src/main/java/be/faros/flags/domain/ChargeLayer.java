package be.faros.flags.domain;

public class ChargeLayer extends SingleColourLayer {
    private ChargeEmblem emblem;
    private float horizontalOffsetPercentage;
    private float verticalOffsetPercentage;
    private float scaling;


    public ChargeEmblem getEmblem() {
        return emblem;
    }

    public void setEmblem(ChargeEmblem emblem) {
        this.emblem = emblem;
    }

    public float getHorizontalOffsetPercentage() {
        return horizontalOffsetPercentage;
    }

    public void setHorizontalOffsetPercentage(float horizontalOffsetPercentage) {
        this.horizontalOffsetPercentage = horizontalOffsetPercentage;
    }

    public float getVerticalOffsetPercentage() {
        return verticalOffsetPercentage;
    }

    public void setVerticalOffsetPercentage(float verticalOffsetPercentage) {
        this.verticalOffsetPercentage = verticalOffsetPercentage;
    }

    public float getScaling() {
        return scaling;
    }

    public void setScaling(float scaling) {
        this.scaling = scaling;
    }
}
