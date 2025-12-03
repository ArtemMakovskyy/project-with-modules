// file: redis-service/src/main/java/com/store/service/impl/PersonServiceImpl.java
package com.store.service.impl;

import com.store.dto.PersonDto;
import com.store.mapper.PersonMapper;
import com.store.model.Person;
import com.store.repository.PersonRepository;
import com.store.service.PersonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public List<PersonDto> findAll() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::toDto)
                .toList();
    }

    @Override
    public PersonDto findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found: " + id));

        return personMapper.toDto(person);
    }

    @Override
    public PersonDto create(PersonDto personDto) {
        Person saved = personRepository.save(personMapper.toEntity(personDto));
        return personMapper.toDto(saved);
    }

    @Override
    public PersonDto update(Long id, PersonDto personDto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found: " + id));

        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setAge(personDto.getAge());

        Person updated = personRepository.save(person);
        return personMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
