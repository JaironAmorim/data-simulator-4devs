package com.jaironamorim.datasimulator4devs.model.dto.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryDto {
    private String sexo;
    @NonNull
    @NotBlank
    private Boolean garaCartao;
    private String digitoVerificador;
    private String bandeiraCartao;

}
