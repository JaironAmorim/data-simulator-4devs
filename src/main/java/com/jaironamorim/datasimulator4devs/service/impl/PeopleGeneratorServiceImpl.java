package com.jaironamorim.datasimulator4devs.service.impl;

import com.jaironamorim.datasimulator4devs.client.RestTemplate4Devs;
import com.jaironamorim.datasimulator4devs.model.dto.creditcard.CreditCardDto;
import com.jaironamorim.datasimulator4devs.model.dto.entry.EntryDto;
import com.jaironamorim.datasimulator4devs.model.dto.people.PeopleDto;
import com.jaironamorim.datasimulator4devs.service.CreditCardGenaratorService;
import com.jaironamorim.datasimulator4devs.service.PeopleGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class PeopleGeneratorServiceImpl implements PeopleGeneratorService {

    private final RestTemplate4Devs restTemplate4Devs;

    private final CreditCardGenaratorService creditCardGenaratorService;

    public PeopleDto generatePeople(EntryDto entryDto) throws Exception {

        log.info("Gerando Pessoa");
        List<PeopleDto> peopleDtoLst = restTemplate4Devs.postData(entryDto);

        if (peopleDtoLst.isEmpty()){
            throw new Exception("Nenhum resultado retornada!");
        }

        PeopleDto people = peopleDtoLst.get(0);

        if(entryDto.getGaraCartao()) {

            CreditCardDto creditCard = creditCardGenaratorService
                    .generateCreditCard(entryDto.getBandeiraCartao(), entryDto.getDigitoVerificador());

            creditCard.setOwner(people.getNome());
            people.setCartaoDeCredito(creditCard);
        }

        return people;
    }

    @Override
    public String generatePeoplenName(){

        String name;
        try {
            name = generatePeople(new EntryDto()).getNome();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return name;
    }

}
