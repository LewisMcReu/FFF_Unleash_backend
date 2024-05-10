package be.faros.flags.web.dto;

public abstract class SingleColourLayerDTO implements LayerDTO {
    private String colour;//represented in hex

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
