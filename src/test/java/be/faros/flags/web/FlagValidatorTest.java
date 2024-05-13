package be.faros.flags.web;

import be.faros.flags.FeatureFlags;
import be.faros.flags.domain.ChargeEmblem;
import be.faros.flags.exceptions.FlagException;
import be.faros.flags.web.dto.ChargeLayerDTO;
import be.faros.flags.web.dto.FlagDTO;
import io.getunleash.Unleash;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlagValidatorTest {

    @Mock
    Unleash unleash;

    @InjectMocks
    private FlagValidator validator;

    @Test
    void validateGuardBothTrue() {
        var mockedDto = mock(FlagDTO.class);

        when(unleash.isEnabled(FeatureFlags.CHARGE)).thenReturn(true);
        when(unleash.isEnabled(FeatureFlags.NASA)).thenReturn(true);

        assertDoesNotThrow(() -> validator.validate(mockedDto));
    }

    @Test
    void validateNasaEmblemIsOff() {
        var mockedDto = mock(FlagDTO.class);
        var layerDTO = new ChargeLayerDTO();
        layerDTO.setEmblem(ChargeEmblem.NASA);
        when(mockedDto.getLayers()).thenReturn(List.of(layerDTO));

        when(unleash.isEnabled(FeatureFlags.CHARGE)).thenReturn(true);
        when(unleash.isEnabled(FeatureFlags.NASA)).thenReturn(false);

        assertThatThrownBy(() -> validator.validate(mockedDto))
                .isInstanceOf(FlagException.class)
                .hasMessage("Flag nasa_emblem toggled off");
    }


    @ParameterizedTest
    @CsvSource({"false,true", "false,false"})
    void validate(boolean charge, boolean nasa) {
        var mockedDto = mock(FlagDTO.class);
        var layerDTO = new ChargeLayerDTO();
        layerDTO.setEmblem(ChargeEmblem.NASA);

        when(mockedDto.getLayers()).thenReturn(List.of(layerDTO));


        when(unleash.isEnabled(FeatureFlags.CHARGE)).thenReturn(charge);
        when(unleash.isEnabled(FeatureFlags.NASA)).thenReturn(nasa);

        assertThatThrownBy(() -> validator.validate(mockedDto))
                .isInstanceOf(FlagException.class)
                .hasMessage("Flag charge_layer toggled off")
        ;


    }


}