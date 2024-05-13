package be.faros.flags.web;

import be.faros.flags.FeatureFlags;
import be.faros.flags.domain.ChargeEmblem;
import be.faros.flags.exceptions.FlagException;
import be.faros.flags.web.dto.ChargeLayerDTO;
import be.faros.flags.web.dto.FlagDTO;
import be.faros.flags.web.dto.LayerDTO;
import io.getunleash.Unleash;
import org.springframework.stereotype.Component;

@Component
public class FlagValidator {
    private final Unleash unleash;

    public FlagValidator(Unleash unleash) {
        this.unleash = unleash;
    }

    public void validate(FlagDTO flag) {
        boolean allowChargeLayers = unleash.isEnabled(FeatureFlags.CHARGE);
        boolean allowNasaEmblem = unleash.isEnabled(FeatureFlags.NASA);
        if (allowChargeLayers && allowNasaEmblem) {
            return;
        }

        for (LayerDTO layer : flag.getLayers()) {
            if (layer instanceof ChargeLayerDTO cl) {
                if (!allowChargeLayers) {
                    throw new FlagException(FeatureFlags.CHARGE);
                } else if (cl.getEmblem() == ChargeEmblem.NASA && !allowNasaEmblem) {
                    throw new FlagException(FeatureFlags.NASA);
                }
            }
        }
    }
}
