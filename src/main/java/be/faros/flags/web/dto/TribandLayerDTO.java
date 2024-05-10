package be.faros.flags.web.dto;

import be.faros.flags.domain.Orientation;

public class TribandLayerDTO implements LayerDTO {
    private String hoistColour;//represented in hex
    private String paleColour;//represented in hex
    private String flyColour;//represented in hex

    private Orientation orientation;

    public String getHoistColour() {
        return hoistColour;
    }

    public void setHoistColour(String hoistColour) {
        this.hoistColour = hoistColour;
    }

    public String getFlyColour() {
        return flyColour;
    }

    public void setFlyColour(String flyColour) {
        this.flyColour = flyColour;
    }

    public String getPaleColour() {
        return paleColour;
    }

    public void setPaleColour(String paleColour) {
        this.paleColour = paleColour;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
