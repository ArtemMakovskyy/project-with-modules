package com.store.threads.service;

import com.store.threads.dto.PersonRequestDto;
import com.store.threads.dto.PersonResponseDto;
import com.store.threads.remote.RemotePersonService;
import java.util.UUID;

public class BlockingPersonService {

    private final RemotePersonService remotePersonService = new RemotePersonService();
    private final EmailService emailService = new EmailService();
    private final ActionLogService actionLogService = new ActionLogService();

    public boolean sendEmailToUserByUid(UUID uuid) {
        PersonResponseDto personResponseDto = remotePersonService.getPerson(buildPersonRequest(uuid),5000);
        String email = emailService.sendEmail(personResponseDto.email());
        boolean actionSaved = actionLogService.saveEmailSentAction(email);
        return actionSaved;
    }

    private PersonRequestDto buildPersonRequest(UUID uid) {
        return new PersonRequestDto(uid.toString());
    }

}
