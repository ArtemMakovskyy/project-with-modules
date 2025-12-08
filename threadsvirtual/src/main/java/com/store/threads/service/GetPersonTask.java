package com.store.threads.service;

import com.store.threads.dto.PersonRequestDto;
import com.store.threads.dto.PersonResponseDto;
import com.store.threads.remote.RemotePersonService;

import java.util.UUID;
import java.util.concurrent.Callable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetPersonTask implements Callable<PersonResponseDto> {

    private final RemotePersonService remotePersonService;
    private final UUID uid;

    @Override
    public PersonResponseDto call() {
        long buildPersonRequestStart = System.nanoTime();
        PersonRequestDto personRequestDto = buildPersonRequest(uid);
        long buildPersonRequestDuration = System.nanoTime() - buildPersonRequestStart;

        long getPersonStart = System.nanoTime();
        PersonResponseDto responseDto = remotePersonService.getPerson(personRequestDto,5000);
        long getPersonDuration = System.nanoTime() - getPersonStart;

        System.out.println("buildPersonRequestDuration: " + buildPersonRequestDuration + " ns");
        System.out.println("getPersonDuration: " + getPersonDuration + " ns");

        return responseDto;
    }

    private PersonRequestDto buildPersonRequest(UUID uid) {
        return new PersonRequestDto(uid.toString());
    }
}
