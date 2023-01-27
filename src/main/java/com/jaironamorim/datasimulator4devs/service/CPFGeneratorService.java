package com.jaironamorim.datasimulator4devs.service;

import com.jaironamorim.datasimulator4devs.model.dto.document.CPFDto;

public interface CPFGeneratorService {
    CPFDto startGenerateCPF();

    boolean isValidCPF(String cpf);

}
