package com.jaironamorim.datasimulator4devs.model.dto.creditcard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreditCardDto {
    @JsonProperty("numero")
    private String number;
    @JsonProperty("cdSeguranca")
    private String cvv;
    @JsonProperty("dtValidade")
    private String expires;
    @JsonProperty("bandeira")
    private String flag;
    @JsonProperty("nmImpresso")
    private String owner;
    @JsonProperty("isValid")
    private Boolean isValid;
}
