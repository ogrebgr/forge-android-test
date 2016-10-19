package com.bolyartech.forge.android.utils;


import com.bolyartech.forge.android.MyApp;


public class MyTestApp extends MyApp {
    private boolean mOnInterfacePausedCalled = false;
    private boolean mOnInterfaceResumedCalled = false;


    @Override
    public void onCreate() {
        useManualOnStartCall();
        super.onCreate();
    }


    @Override
    protected void onInterfacePaused() {
        super.onInterfacePaused();
        mOnInterfacePausedCalled = true;
    }


    public boolean isOnInterfacePausedCalled() {
        return mOnInterfacePausedCalled;
    }


    @Override
    protected void onInterfaceResumed() {
        super.onInterfaceResumed();
        mOnInterfaceResumedCalled = true;
    }


    public boolean isOnInterfaceResumedCalled() {
        return mOnInterfaceResumedCalled;
    }
}
