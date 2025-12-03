package com.store.controller;

import com.store.dto.PersonDto;
import com.store.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Timeout(10)
public class PersonServiceControllerBaseRockGeneratedTest {

    @Mock
    private PersonService mockPersonService;

    private PersonServiceController personServiceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personServiceController = new PersonServiceController(mockPersonService);
    }

    @Test
    void testGetAllReturnsListFromService() {
        PersonDto person1 = new PersonDto(1L, "John", "Doe", 30);
        PersonDto person2 = new PersonDto(2L, "Jane", "Smith", 25);
        List<PersonDto> expectedPersons = new ArrayList<>();
        expectedPersons.add(person1);
        expectedPersons.add(person2);
        doReturn(expectedPersons).when(mockPersonService).findAll();
        List<PersonDto> result = personServiceController.getAll();
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(equalTo(2)));
        assertThat(result, is(sameInstance(expectedPersons)));
        verify(mockPersonService, atLeast(1)).findAll();
    }

    @Test
    void testGetAllReturnsEmptyList() {
        List<PersonDto> emptyList = new ArrayList<>();
        doReturn(emptyList).when(mockPersonService).findAll();
        List<PersonDto> result = personServiceController.getAll();
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(equalTo(0)));
        assertThat(result, is(sameInstance(emptyList)));
        verify(mockPersonService, atLeast(1)).findAll();
    }

    @Test
    void testGetByIdReturnsOkWhenPersonFound() {
        Long personId = 1L;
        PersonDto expectedPerson = new PersonDto(personId, "John", "Doe", 30);
        doReturn(expectedPerson).when(mockPersonService).findById(eq(personId));
        ResponseEntity<PersonDto> result = personServiceController.getById(personId);
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat(result.getBody(), is(notNullValue()));
        assertThat(result.getBody(), is(sameInstance(expectedPerson)));
        verify(mockPersonService, atLeast(1)).findById(eq(personId));
    }

    @Test
    void testGetByIdReturnsNotFoundWhenPersonNotFound() {
        Long personId = 999L;
        doReturn(null).when(mockPersonService).findById(eq(personId));
        ResponseEntity<PersonDto> result = personServiceController.getById(personId);
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.NOT_FOUND)));
        assertThat(result.getBody(), is(nullValue()));
        verify(mockPersonService, atLeast(1)).findById(eq(personId));
    }

    @Test
    void testCreateReturnsOkWithCreatedPerson() {
        PersonDto inputPerson = new PersonDto(null, "John", "Doe", 30);
        PersonDto createdPerson = new PersonDto(1L, "John", "Doe", 30);
        doReturn(createdPerson).when(mockPersonService).create(eq(inputPerson));
        ResponseEntity<PersonDto> result = personServiceController.create(inputPerson);
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat(result.getBody(), is(notNullValue()));
        assertThat(result.getBody(), is(sameInstance(createdPerson)));
        verify(mockPersonService, atLeast(1)).create(eq(inputPerson));
    }

    @Test
    void testUpdateReturnsOkWhenPersonUpdated() {
        Long personId = 1L;
        PersonDto updatePerson = new PersonDto(personId, "John", "Smith", 31);
        PersonDto updatedPerson = new PersonDto(personId, "John", "Smith", 31);
        doReturn(updatedPerson).when(mockPersonService).update(eq(personId), eq(updatePerson));
        ResponseEntity<PersonDto> result = personServiceController.update(personId, updatePerson);
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat(result.getBody(), is(notNullValue()));
        assertThat(result.getBody(), is(sameInstance(updatedPerson)));
        verify(mockPersonService, atLeast(1)).update(eq(personId), eq(updatePerson));
    }

    @Test
    void testUpdateReturnsNotFoundWhenPersonNotFound() {
        Long personId = 999L;
        PersonDto updatePerson = new PersonDto(personId, "John", "Smith", 31);
        doReturn(null).when(mockPersonService).update(eq(personId), eq(updatePerson));
        ResponseEntity<PersonDto> result = personServiceController.update(personId, updatePerson);
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.NOT_FOUND)));
        assertThat(result.getBody(), is(nullValue()));
        verify(mockPersonService, atLeast(1)).update(eq(personId), eq(updatePerson));
    }

    @Test
    void testDeleteReturnsNoContent() {
        Long personId = 1L;
        ResponseEntity<Void> result = personServiceController.delete(personId);
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.NO_CONTENT)));
        assertThat(result.getBody(), is(nullValue()));
        verify(mockPersonService, atLeast(1)).delete(eq(personId));
    }
}
