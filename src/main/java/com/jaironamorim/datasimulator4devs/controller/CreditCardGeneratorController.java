package com.jaironamorim.datasimulator4devs.controller;

import com.jaironamorim.datasimulator4devs.model.dto.creditcard.CreditCardDto;
import com.jaironamorim.datasimulator4devs.service.CreditCardGenarateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/credit-card")
public class CreditCardGeneratorController {

    private final CreditCardGenarateService creditCardGenarateService;

    @GetMapping("/generate/{label}")
    public ResponseEntity<CreditCardDto> generateCreditCard( @PathVariable(value = "label") String label,
                                                             @RequestParam(value = "checkdigit", required = false)
                                                             String checkdigit) {

        CreditCardDto creditCardDto = creditCardGenarateService.generateCreditCard(label, checkdigit);

        return ResponseEntity.ok().body(creditCardDto);

    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateCard(@RequestParam(value = "numberOfCard") String numberOfCard){

        Boolean isvalid = creditCardGenarateService.isValidCreditCardNumber(numberOfCard);

        return ResponseEntity.ok().body(isvalid);

    }

}
