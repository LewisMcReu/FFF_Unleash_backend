package be.faros.flags.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BasicLayer.class, name = "basic"),
        @JsonSubTypes.Type(value = ChargeLayer.class, name = "charge"),
        @JsonSubTypes.Type(value = TribandLayer.class, name = "triband")}
)
public interface Layer {
}
