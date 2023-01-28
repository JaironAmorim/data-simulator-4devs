package com.jaironamorim.datasimulator4devs.controller;

import com.jaironamorim.datasimulator4devs.model.dto.document.CNPJDto;
import com.jaironamorim.datasimulator4devs.model.dto.document.CPFDto;
import com.jaironamorim.datasimulator4devs.service.CNPJGeneratorService;
import com.jaironamorim.datasimulator4devs.service.CPFGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/document")
public class DocumentGeneratorController {

    private final CNPJGeneratorService cnpjGeneratorService;

    private final CPFGeneratorService cpfGeneratorService;

    @GetMapping("/generate/cpf")
    public ResponseEntity<CPFDto> generateCPF() {

        return ResponseEntity.ok().body(cpfGeneratorService.startGenerateCPF());

    }

    @GetMapping("/validate/cpf/{cpf}")
    public ResponseEntity<Boolean> validateCPF(@PathVariable(value = "cpf") String cpf){

        return ResponseEntity.ok().body(cpfGeneratorService.isValidCPF(cpf));

    }

    @GetMapping("/generate/cnpj")
    public ResponseEntity<CNPJDto> generateCNPJ() {

        return ResponseEntity.ok().body(cnpjGeneratorService.startGenerateCNPJ());

    }

    @GetMapping("/validate/cnpj/{cnpj}")
    public ResponseEntity<Boolean> validateCNPJ(@PathVariable(value = "cnpj") String cnpj){

        return ResponseEntity.ok().body(cnpjGeneratorService.isValidCNPJ(cnpj));

    }

}
