package com.store.threads.runner;

import com.store.threads.service.GetPersonTask;
import com.store.threads.dto.PersonResponseDto;
import com.store.threads.remote.RemotePersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class AppRunnerSingleBlockingThread {

    private final RemotePersonService remotePersonService;

    public void runBlockingThread() {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            UUID uid = UUID.randomUUID();
            GetPersonTask task = new GetPersonTask(remotePersonService, uid);
            PersonResponseDto personResponseDto = executorService.submit(task).get();
            System.out.println(personResponseDto);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
