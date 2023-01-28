package com.jaironamorim.datasimulator4devs.service.impl;

import com.jaironamorim.datasimulator4devs.model.dto.document.CPFDto;
import com.jaironamorim.datasimulator4devs.service.CPFGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
@Slf4j
@Service
@RequiredArgsConstructor
public class CPFGeneratorServiceImpl implements CPFGeneratorService {
    @Override
    public CPFDto startGenerateCPF(){

        log.info("Gerando cpf");
        final String cpf = this.generateCPF();

        return CPFDto.builder()
                .cpf(cpf)
                .isValid(this.isValidCPF(cpf))
                .build();

    }
    private String generateCPF() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            sb.append(random.nextInt(10));
        }
        int d1 = getDigit(sb.toString());
        int d2 = getDigit(sb.append(d1).toString());
        sb.append(d2);
        return sb.toString();
    }

    private int getDigit(String s) {
        int sum = 0;
        for (int i = s.length() - 1, digit; i >= 0; i--) {
            digit = Integer.parseInt(s.substring(i, i + 1));
            sum += digit * (s.length() + 1 - i);
        }
        sum = 11 - (sum % 11);
        return sum > 9 ? 0 : sum;
    }
    @Override
    public boolean isValidCPF(String cpf) {
        if (cpf == null) {
            return false;
        }
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11) {
            return false;
        }
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = cpf.charAt(i) - '0';
        }
        int firstDigit = getDigit(digits, 11);
        int secondDigit = getDigit(digits, 12);
        return (digits[9] == firstDigit) && (digits[10] == secondDigit);
    }

    private int getDigit(int[] digits, int digit) {
        int sum = 0;
        for (int i = 0; i < digit - 2; i++) {
            sum += digits[i] * (digit - i - 1);
        }
        sum = sum % 11;
        return sum < 2 ? 0 : 11 - sum;
    }

}
