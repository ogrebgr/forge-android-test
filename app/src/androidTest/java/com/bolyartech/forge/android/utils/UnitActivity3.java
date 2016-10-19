package com.bolyartech.forge.android.utils;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.bolyartech.forge.android.app_unit.ResidentComponent;
import com.bolyartech.forge.android.app_unit.UnitActivity;


public class UnitActivity3 extends Activity implements UnitActivity {

    @NonNull
    @Override
    public ResidentComponent createResidentComponent() {
        return null;
    }


    @Override
    public void setResident(@NonNull ResidentComponent ri) {

    }


    @NonNull
    @Override
    public ResidentComponent getResident() {
        return null;
    }


    @NonNull
    @Override
    public ResidentComponent getRes() {
        return null;
    }
}
