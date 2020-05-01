package com.example.knight.dagger2demo;


import androidx.multidex.MultiDex;

import com.facebook.buck.android.support.exopackage.ExopackageApplication;

@SuppressWarnings("rawtypes")
public class AppShell extends ExopackageApplication {
    private static final String APP_NAME = "com.example.knight.dagger2demo.DaggerApplication";
    private final boolean mIsExopackageMode;

    public AppShell() {
        super(APP_NAME, BuildConfig.EXOPACKAGE_FLAGS != 0);
        mIsExopackageMode = BuildConfig.EXOPACKAGE_FLAGS != 0;
    }

    @Override
    protected void onBaseContextAttached() {
        if (!mIsExopackageMode) {
            MultiDex.install(this);
        }
    }
}
