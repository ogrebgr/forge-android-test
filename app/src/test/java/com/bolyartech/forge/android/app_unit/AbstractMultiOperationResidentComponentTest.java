package com.bolyartech.forge.android.app_unit;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;


public class AbstractMultiOperationResidentComponentTest {

    @Test
    public void testBusy() {
        TestAbstractMultiOperationResidentComponent res = new TestAbstractMultiOperationResidentComponent();
        res.switchToBusyState(Operation.OP1);
        assertTrue("Unexpected operation", res.getCurrentOperation() == Operation.OP1);
        res.switchToCompletedStateSuccess();
        res.ack();

        res.switchToBusyState(Operation.OP2);
        assertTrue("Unexpected operation", res.getCurrentOperation() == Operation.OP2);
    }


    private static class TestAbstractMultiOperationResidentComponent
            extends AbstractMultiOperationResidentComponent<Operation> {


    }


    private enum Operation {
        OP1,
        OP2
    }
}
