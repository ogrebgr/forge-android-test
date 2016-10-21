package com.bolyartech.forge.android.app_unit;


import com.bolyartech.forge.android.utils.TestAbstractOperationResidentComponent;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class AbstractOperationResidentComponentTest {
    @Test
    public void testInitialStatus() {
        TestAbstractOperationResidentComponent res = new TestAbstractOperationResidentComponent();
        assertTrue("Successful before even operated", !res.isCompletedSuccessfully());

        assertTrue("Not in IDLE", res.isInIdleState());
        assertTrue("Not in IDLE", res.isIdle());
    }


    @Test
    public void testAddRemoveListener() {
        TestAbstractOperationResidentComponent res = new TestAbstractOperationResidentComponent();
        TestUnitActivity act = mock(TestUnitActivity.class);


        // tests if listener is set and removed when it should
        res.onActivityResumed(act);
        assertTrue("Listener not set", res.getListener() != null);
        res.onActivityPaused();
        assertTrue("Listener not nullified", res.getListener() == null);

    }


    @Test
    public void testDontAddAsListener() {
        TestAbstractOperationResidentComponent res = new TestAbstractOperationResidentComponent();
        UnitActivity act = mock(UnitActivity.class);
        // tests if listener is not set when it should not
        res.onActivityResumed(act);
        assertTrue("Listener set", res.getListener() == null);
    }


    @Test
    public void testStateChangeNotified() {
        TestAbstractOperationResidentComponent res = new TestAbstractOperationResidentComponent();
        TestUnitActivity act = mock(TestUnitActivity.class);

        res.onActivityResumed(act);
        res.switchToBusyState();
        verify(act, times(1)).onResidentOperationStateChanged();
    }


    @Test
    public void testSwitchToBusy() {
        TestAbstractOperationResidentComponent res = new TestAbstractOperationResidentComponent();
        res.switchToBusyState();

        assertTrue("Not in busy state", res.getOpState() == OperationResidentComponent.OpState.BUSY);
    }


    @Test
    public void testSwitchToCompletedSuccess() {
        TestAbstractOperationResidentComponent res = new TestAbstractOperationResidentComponent();
        res.switchToBusyState();
        res.switchToCompletedStateSuccess();

        assertTrue("Not in completed state", res.getOpState() == OperationResidentComponent.OpState.COMPLETED);
        assertTrue("Not successful", res.isSuccess());
    }


    @Test
    public void testSwitchToCompletedFail() {
        TestAbstractOperationResidentComponent res = new TestAbstractOperationResidentComponent();
        res.switchToBusyState();
        res.switchToCompletedStateFail();

        assertTrue("Not fail", !res.isSuccess());
    }


    @Test
    public void testAck() {
        TestAbstractOperationResidentComponent res = new TestAbstractOperationResidentComponent();
        res.switchToBusyState();
        res.switchToCompletedStateFail();
        res.ack();

        assertTrue("Not fail", res.getOpState() == OperationResidentComponent.OpState.IDLE);
    }


    @Test(expected = IllegalStateException.class)
    public void testInvalidStateSwitch() {
        TestAbstractOperationResidentComponent res = new TestAbstractOperationResidentComponent();
        res.switchToCompletedStateFail();
    }


    @Test
    public void testAbort() {
        TestAbstractOperationResidentComponent res = new TestAbstractOperationResidentComponent();
        res.switchToBusyState();
        res.abort();
        assertTrue("Not fail", res.getOpState() == OperationResidentComponent.OpState.IDLE);
    }


    private interface TestUnitActivity extends UnitActivity, OperationResidentComponent.Listener {

    }
}
