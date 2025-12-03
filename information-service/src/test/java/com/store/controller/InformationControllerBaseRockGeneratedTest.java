package com.store.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Timeout(10)
public class InformationControllerBaseRockGeneratedTest {

    private InformationController informationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        informationController = new InformationController();
        mockMvc = MockMvcBuilders.standaloneSetup(informationController).build();
    }

    @Test
    public void testConstructorCreatesNonNullInstance() {
        InformationController controller = new InformationController();
        assertThat(controller, notNullValue());
    }

    @Test
    public void testInfoTestReturnsHelloWorld() {
        String result = informationController.infoTest();
        assertThat(result, equalTo("Hello World"));
    }
}
