package be.faros.flags.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BasicLayerDTO.class, name = "basic"),
        @JsonSubTypes.Type(value = BasicScalableLayerDTO.class, name = "basic-scalable"),
        @JsonSubTypes.Type(value = ChargeLayerDTO.class, name = "charge"),
        @JsonSubTypes.Type(value = TribandLayerDTO.class, name = "triband")}
)
public interface LayerDTO {
}
