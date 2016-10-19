package com.bolyartech.forge.android.app_unit;

import com.bolyartech.forge.android.utils.UnitActivity1;
import com.bolyartech.forge.android.utils.UnitActivity2;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UnitManagerImplTest {
    @Test
    public void testNewActivity() {
        ResidentComponent res = mock(ResidentComponent.class);
        UnitActivity act = mock(UnitActivity.class);
        when(act.createResidentComponent()).thenReturn(res);

        UnitManagerImpl um = new UnitManagerImpl();
        um.onActivityCreated(act);

        assertTrue("Unexpected resident component", res == um.getActiveResidentComponent());

        assertTrue("Unexpected count", um.getResidentComponents().size() == 1);
    }


    @Test
    public void testReturningActivity() {
        ResidentComponent res1 = mock(ResidentComponent.class);
        UnitActivity1 act1 = mock(UnitActivity1.class);
        when(act1.createResidentComponent()).thenReturn(res1);

        ResidentComponent res2 = mock(ResidentComponent.class);
        UnitActivity2 act2 = mock(UnitActivity2.class);
        when(act2.createResidentComponent()).thenReturn(res2);

        UnitManagerImpl um = new UnitManagerImpl();
        um.onActivityCreated(act1);
        um.onActivityCreated(act2);
        um.onActivityResumed(act1);

        assertTrue("Unexpected resident component", res1 == um.getActiveResidentComponent());

        assertTrue("Unexpected count", um.getResidentComponents().size() == 2);
    }


    @Test
    public void testOnStop() {
        ResidentComponent res1 = mock(ResidentComponent.class);
        UnitActivity1 act1 = mock(UnitActivity1.class);
        when(act1.createResidentComponent()).thenReturn(res1);

        UnitManagerImpl um = new UnitManagerImpl();
        um.onActivityCreated(act1);
        um.onActivityStopped(act1);

        assertTrue("Unexpected resident component", um.getActiveResidentComponent() == res1);

        when(act1.isFinishing()).thenReturn(true);
        um.onActivityStopped(act1);
        assertTrue("Unexpected resident component", um.getActiveResidentComponent() == null);

        assertTrue("Unexpected count", um.getResidentComponents().size() == 0);
    }


    @Test
    public void testOnDestroyed() {
        ResidentComponent res1 = mock(ResidentComponent.class);
        UnitActivity1 act1 = mock(UnitActivity1.class);
        when(act1.createResidentComponent()).thenReturn(res1);

        UnitManagerImpl um = new UnitManagerImpl();
        um.onActivityCreated(act1);
        um.onActivityDestroyed(act1);

        assertTrue("Unexpected resident component", um.getActiveResidentComponent() == res1);

        when(act1.isFinishing()).thenReturn(true);
        um.onActivityDestroyed(act1);
        assertTrue("Unexpected resident component", um.getActiveResidentComponent() == null);

        assertTrue("Unexpected count", um.getResidentComponents().size() == 0);
    }


    @Test
    public void testOnResumed() {
        ResidentComponent res1 = mock(ResidentComponent.class);
        UnitActivity1 act1 = mock(UnitActivity1.class);
        when(act1.createResidentComponent()).thenReturn(res1);
        UnitManagerImpl um = new UnitManagerImpl();
        um.onActivityCreated(act1);
        um.onActivityResumed(act1);

        verify(res1, only()).onActivityResumed(act1);
    }


    @Test
    public void testOnPaused() {
        ResidentComponent res1 = mock(ResidentComponent.class);
        UnitActivity1 act1 = mock(UnitActivity1.class);
        when(act1.createResidentComponent()).thenReturn(res1);
        UnitManagerImpl um = new UnitManagerImpl();
        um.onActivityCreated(act1);
        um.onActivityResumed(act1);
        um.onActivityPaused(act1);

        verify(res1, times(1)).onActivityPaused();
    }
}
