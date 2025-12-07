package com.store.threads.remote;

import com.google.gson.Gson;
import com.store.threads.dto.PersonRequestDto;
import com.store.threads.dto.PersonResponseDto;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.springframework.stereotype.Service;

/**
 * Pseudo remote service
 */
@Service
public class RemotePersonService {

    private final String filePath = "src/main/resources/person.json";
    private final Gson gson = new Gson();

    public PersonResponseDto getPerson(PersonRequestDto requestDto) {
        try {
            Thread.sleep(5000);
            PersonResponseDto personFromFile = gson.fromJson(
                    new FileReader(filePath), PersonResponseDto.class);
            return new PersonResponseDto(
                    requestDto.uid(),
                    personFromFile.firstName(),
                    personFromFile.lastName(),
                    requestDto.uid().concat("@mail.com")
            );
        } catch (FileNotFoundException | InterruptedException e) {
            return new PersonResponseDto(
                    requestDto.uid(),
                    "John",
                    "Doe",
                    requestDto.uid().concat("@mail.com"));
        }
    }
}
