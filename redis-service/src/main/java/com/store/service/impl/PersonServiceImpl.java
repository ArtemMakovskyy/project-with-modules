package com.store.service.impl;

import com.store.dto.PersonDto;
import com.store.mapper.PersonMapper;
import com.store.model.Person;
import com.store.repository.PersonRepository;
import com.store.service.PersonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private static final String CACHE_PERSONS = "persons";
    private static final String CACHE_PERSON_BY_ID = "personById";

    private final PersonRepository repository;
    private final PersonMapper mapper;

    @Override
    @Cacheable(cacheNames = CACHE_PERSONS)
    public List<PersonDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Cacheable(
            cacheNames = CACHE_PERSON_BY_ID,
            key = "#id",
            unless = "#result == null"       // ← ключевой фикс
    )
    public PersonDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    @Override
    @CacheEvict(cacheNames = {CACHE_PERSONS}, allEntries = true)
    public PersonDto create(PersonDto dto) {
        Person saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    @CacheEvict(cacheNames = {CACHE_PERSONS, CACHE_PERSON_BY_ID}, key = "#id")
    public PersonDto update(Long id, PersonDto dto) {
        return repository.findById(id)
                .map(person -> {
                    person.setFirstName(dto.getFirstName());
                    person.setLastName(dto.getLastName());
                    person.setAge(dto.getAge());
                    Person updated = repository.save(person);
                    return mapper.toDto(updated);
                })
                .orElse(null);
    }

    @Override
    @CacheEvict(cacheNames = {CACHE_PERSONS, CACHE_PERSON_BY_ID}, key = "#id")
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
