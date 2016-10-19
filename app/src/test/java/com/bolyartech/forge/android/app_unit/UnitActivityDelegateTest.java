package com.bolyartech.forge.android.app_unit;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;


public class UnitActivityDelegateTest {
    @Test
    public void test() {
        ResidentComponent res = mock(ResidentComponent.class);
        UnitActivityDelegate ud = new UnitActivityDelegate();

        ud.setResident(res);
        assertTrue("not same resident", ud.getResident() == res);
        assertTrue("not same resident", ud.getRes() == res);
    }

}
