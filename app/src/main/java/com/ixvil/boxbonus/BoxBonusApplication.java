package com.ixvil.boxbonus;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by shipin_a on 02.10.2016.
 */

public class BoxBonusApplication extends Application {


    @Override
    public void onCreate() {
        VKSdk.initialize(this);
    }
}
