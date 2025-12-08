package com.store.threads.controller;

import com.store.threads.runner.AppRunnerSingleBlockingThread;
import com.store.threads.runner.BlockingMultipleAppRunner;
import com.store.threads.runner.NonBlockingAppRunner;
import com.store.threads.runner.VirtualThreadsAppRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/threads")
public class ThreadController {

    private final AppRunnerSingleBlockingThread blockingThread;
    private final BlockingMultipleAppRunner blockingMultipleAppRunner;
    private final NonBlockingAppRunner nonBlockingAppRunner;
    private final VirtualThreadsAppRunner virtualThreadsAppRunner;

    @GetMapping("/blocking/single")
    public void runBlockingThread() {
        blockingThread.runBlockingThread();
    }

    @GetMapping("/blocking/multiple")
    public void runMultipleBlockingThread() {
        blockingMultipleAppRunner.runMultipleBlockingThread();
    }

    @GetMapping("/nonblocking/multiple")
    public void runNonBlockingAppRunner() {
        nonBlockingAppRunner.runNonBlockingAppRunner();
    }

    @GetMapping("/virtual/multiple")
    public void runVirtualThreadsAppRunner() {
        virtualThreadsAppRunner.runVirtualThreadsAppRunner();
    }
}
