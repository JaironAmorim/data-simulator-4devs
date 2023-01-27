package com.jaironamorim.datasimulator4devs.service;

import com.jaironamorim.datasimulator4devs.model.dto.document.CNPJDto;

public interface CNPJGeneratorService {

    CNPJDto startGenerateCNPJ();

    boolean isValidCNPJ(String cnpj);

}
