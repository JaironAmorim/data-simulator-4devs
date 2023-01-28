package com.jaironamorim.datasimulator4devs.service;

import com.jaironamorim.datasimulator4devs.model.dto.entry.EntryDto;
import com.jaironamorim.datasimulator4devs.model.dto.people.PeopleDto;

public interface PeopleGeneratorService {
     PeopleDto generatePeople(EntryDto entryDto) throws Exception;

     String generatePeoplenName();
}
