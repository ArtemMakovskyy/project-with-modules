package com.store.service.impl;

import com.store.dto.PersonDto;
import com.store.mapper.PersonMapper;
import com.store.model.Person;
import com.store.repository.PersonRepository;
import com.store.service.PersonService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    @Override
    public List<PersonDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public PersonDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    @Override
    public PersonDto create(PersonDto dto) {
        Person saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    public PersonDto update(Long id, PersonDto dto) {
        return repository.findById(id)
                .map(person -> {
                    person.setFirstName(dto.getFirstName());
                    person.setLastName(dto.getLastName());
                    person.setAge(dto.getAge());
                    return mapper.toDto(repository.save(person));
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
