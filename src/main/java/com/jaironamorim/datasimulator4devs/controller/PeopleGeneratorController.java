package com.jaironamorim.datasimulator4devs.controller;

import com.jaironamorim.datasimulator4devs.model.dto.entry.EntryDto;
import com.jaironamorim.datasimulator4devs.model.dto.people.PeopleDto;
import com.jaironamorim.datasimulator4devs.service.PeopleGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/people")
public class PeopleGeneratorController {

    private final PeopleGeneratorService peopleGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<PeopleDto> generateCreditCard(@RequestBody EntryDto entryDto) throws Exception {

        PeopleDto pessoa = peopleGeneratorService.generatePeople(entryDto);
        return ResponseEntity.ok().body(pessoa);

    }

}
