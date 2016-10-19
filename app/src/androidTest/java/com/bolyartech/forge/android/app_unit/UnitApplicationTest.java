package com.bolyartech.forge.android.app_unit;

import android.app.Application;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.bolyartech.forge.android.utils.MyTestApp;
import com.bolyartech.forge.android.utils.UnitActivity3;
import com.bolyartech.forge.base.misc.TimeProvider;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(AndroidJUnit4.class)
public class UnitApplicationTest {
    @Test
    public void testCreationDefault() {
        MyTestApp app = (MyTestApp) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        app.onStart();

        assertTrue("UnitManager not initialized", app.getUnitManager() != null);
        assertTrue("TimeProvider not initialized", app.getTimeProvider() != null);
        assertTrue("ActivityLifecycleCallbacks not initialized", app.getActivityLifecycleCallbacks() != null);
    }

    @Test
    public void testCreationCustom() {
        MyTestApp app = (MyTestApp) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        assertTrue("UnitManager not initialized", app.getUnitManager() == null);
        assertTrue("TimeProvider not initialized", app.getTimeProvider() == null);
        assertTrue("ActivityLifecycleCallbacks not initialized", app.getActivityLifecycleCallbacks() == null);

        UnitManager um = mock(UnitManager.class);
        TimeProvider tp = mock(TimeProvider.class);

        app.setUnitManager(um);
        app.setTimeProvider(tp);

        // test if we really set the um and tp
        assertTrue("unexpected UnitManager", app.getUnitManager() == um);
        assertTrue("unexpected TimeProvider", app.getTimeProvider() == tp);

        app.onStart();

        // test if um and tp are not overrode by onStart()
        assertTrue("unexpected UnitManager", app.getUnitManager() == um);
        assertTrue("unexpected TimeProvider", app.getTimeProvider() == tp);
    }


    @Test
    public void testInterfacePaused() {
        MyTestApp app = (MyTestApp) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        TimeProvider tp = mock(TimeProvider.class);
        when(tp.getVmTime()).thenReturn(1L, 2L, 20_000L);

        app.setTimeProvider(tp);


        ResidentComponent res = mock(ResidentComponent.class);
        UnitActivity3 act = mock(UnitActivity3.class);
        when(act.createResidentComponent()).thenReturn(res);

        app.onStart();
        Application.ActivityLifecycleCallbacks alc = app.getActivityLifecycleCallbacks();
        alc.onActivityCreated(act, null);
        alc.onActivityResumed(act);
        alc.onActivityPaused(act);

        app.checkInterfacePaused();
        assertTrue("interface paused when should not be paused", !app.isOnInterfacePausedCalled());
        app.checkInterfacePaused();
        assertTrue("interface NOT paused when it should be paused", app.isOnInterfacePausedCalled());
    }


    @Test
    public void testInterfaceResumed() {
        MyTestApp app = (MyTestApp) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        TimeProvider tp = mock(TimeProvider.class);
        when(tp.getVmTime()).thenReturn(1L, 20_000L, 20_001L, 20_002L);

        app.setTimeProvider(tp);


        ResidentComponent res = mock(ResidentComponent.class);
        UnitActivity3 act = mock(UnitActivity3.class);
        when(act.createResidentComponent()).thenReturn(res);

        app.onStart();
        Application.ActivityLifecycleCallbacks alc = app.getActivityLifecycleCallbacks();
        alc.onActivityCreated(act, null);
        alc.onActivityResumed(act);
        alc.onActivityPaused(act);

        app.checkInterfacePaused();
        assertTrue("interface NOT paused when it should be paused", app.isOnInterfacePausedCalled());
        alc.onActivityResumed(act);
        app.checkInterfacePaused();
        assertTrue("interface paused when should not be paused", app.isOnInterfaceResumedCalled());
    }

}
