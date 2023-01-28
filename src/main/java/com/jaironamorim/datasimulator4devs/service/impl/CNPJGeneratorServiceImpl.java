package com.jaironamorim.datasimulator4devs.service.impl;

import com.jaironamorim.datasimulator4devs.model.dto.document.CNPJDto;
import com.jaironamorim.datasimulator4devs.service.CNPJGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class CNPJGeneratorServiceImpl implements CNPJGeneratorService {

    @Override
    public CNPJDto startGenerateCNPJ(){

        log.info("Gerando CNPJ");
        final String cnpj = this.generateCNPJ();

        return CNPJDto.builder()
                .cnpj(cnpj)
                .isValid(this.isValidCNPJ(cnpj))
                .build();

    }
    private String generateCNPJ() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10));
        }
        int d1 = getDigit(sb.toString(), 5);
        int d2 = getDigit(sb.append(d1).toString(), 6);
        sb.append(d2);
        return sb.toString();
    }

    private int getDigit(String s, int digit) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += Integer.parseInt(s.substring(i, i + 1)) * digit;
            digit = digit == 2 ? 9 : digit - 1;
        }
        sum = 11 - (sum % 11);
        return sum > 9 ? 0 : sum;
    }

    public boolean isValidCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");
        if (cnpj.length() != 14) {
            return false;
        }
        int size = cnpj.length() - 2;
        String numbers = cnpj.substring(0, size);
        String digits = cnpj.substring(size);
        int sum = 0;
        int pos = size - 7;
        for (int i = size; i >= 1; i--) {
            sum += Integer.parseInt(numbers.substring(size - i, size - i + 1)) * pos--;
            if (pos < 2) {
                pos = 9;
            }
        }
        int result = sum % 11 < 2 ? 0 : 11 - sum % 11;
        if (result != Integer.parseInt(digits.substring(0, 1))) {
            return false;
        }
        size = size + 1;
        numbers = cnpj.substring(0, size);
        sum = 0;
        pos = size - 7;
        for (int i = size; i >= 1; i--) {
            sum += Integer.parseInt(numbers.substring(size - i, size - i + 1)) * pos--;
            if (pos < 2) {
                pos = 9;
            }
        }
        result = sum % 11 < 2 ? 0 : 11 - sum % 11;
        if (result != Integer.parseInt(digits.substring(1))) {
            return false;
        }
        return true;
    }

}
