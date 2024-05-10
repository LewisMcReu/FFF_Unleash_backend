package be.faros.flags.domain;

public abstract class SingleColourLayer implements Layer {
    private String colour;//represented in hex

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
