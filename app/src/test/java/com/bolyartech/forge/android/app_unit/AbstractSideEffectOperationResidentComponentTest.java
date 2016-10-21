package com.bolyartech.forge.android.app_unit;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;


public class AbstractSideEffectOperationResidentComponentTest {
    @Test
    public void testResult() {
        TestAbstractSideEffectOperationResidentComponent res = new TestAbstractSideEffectOperationResidentComponent();
        res.switchToBusyState();
        Result result = new Result();
        res.switchToCompletedStateSuccess(result);
        assertTrue("Unexpected result", result == res.getLastResult());
    }


    @Test
    public void testError() {
        TestAbstractSideEffectOperationResidentComponent res = new TestAbstractSideEffectOperationResidentComponent();
        res.switchToBusyState();
        Error error = new Error();
        res.switchToCompletedStateFail(error);
        assertTrue("Unexpected result", error == res.getLastError());
    }


    private static class TestAbstractSideEffectOperationResidentComponent extends
            AbstractSideEffectOperationResidentComponent<Result, Error> {


    }


    private static class Result {

    }


    private static class Error {

    }
}
