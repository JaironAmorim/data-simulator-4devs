package com.jaironamorim.datasimulator4devs.model.dto.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CNPJDto {
    private String cnpj;
    private Boolean isValid;
}
