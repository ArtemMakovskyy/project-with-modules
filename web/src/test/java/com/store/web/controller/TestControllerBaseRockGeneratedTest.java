package com.store.web.controller;

import com.store.service.TestInformationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeast;

@Timeout(10)
public class TestControllerBaseRockGeneratedTest {

    @Mock
    private TestInformationService testInformationService;

    private TestController testController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testController = new TestController(testInformationService);
    }

    @Test
    public void testReturnsExpectedString() {
        String expectedResult = "test information";
        doReturn(expectedResult).when(testInformationService).getTestInformation();
        String result = testController.test();
        assertThat(result, equalTo(expectedResult));
        verify(testInformationService, atLeast(1)).getTestInformation();
    }

    @Test
    public void testReturnsNullWhenServiceReturnsNull() {
        doReturn(null).when(testInformationService).getTestInformation();
        String result = testController.test();
        assertThat(result, equalTo(null));
        verify(testInformationService, atLeast(1)).getTestInformation();
    }

    @Test
    public void testReturnsEmptyStringWhenServiceReturnsEmptyString() {
        String expectedResult = "";
        doReturn(expectedResult).when(testInformationService).getTestInformation();
        String result = testController.test();
        assertThat(result, equalTo(expectedResult));
        verify(testInformationService, atLeast(1)).getTestInformation();
    }

    @Test
    public void testControllerInstantiationIsNotNull() {
        assertThat(testController, notNullValue());
    }
}
