package com.bolyartech.forge.android.app_unit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.bolyartech.forge.android", appContext.getPackageName());

        UnitApplication ua = new UnitApplication() {

        };

        ua.onStart();

        assertTrue("UnitManager not initialized", ua.getUnitManager() != null);
        assertTrue("TimeProvider not initialized", ua.getTimeProvider() != null);
        assertTrue("ActivityLifecycleCallbacks not initialized", ua.getActivityLifecycleCallbacks() != null);
    }
}
