package com.store.service;

import com.store.dto.PersonDto;
import java.util.List;

public interface PersonService {
    List<PersonDto> findAll();
    PersonDto findById(Long id);
    PersonDto create(PersonDto dto);
    PersonDto update(Long id, PersonDto dto);
    void delete(Long id);
}