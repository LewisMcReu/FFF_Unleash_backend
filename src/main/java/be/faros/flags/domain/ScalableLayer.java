package be.faros.flags.domain;

public abstract class ScalableLayer extends SingleColourLayer {
    private double scale; //from .5 to 2

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}
