package com.ixvil.boxbonus;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;

import com.ixvil.boxbonus.models.User;

/**
 * Created by shipin_a on 02.10.2016.
 */

public class BoxBonusApplication extends Application {


    @Override
    public void onCreate() {

        AccountManager accountManager = AccountManager.get(this);
//        Account[] accounts = accountManager.getAccountsByType('com.ixvil.boxbonus');

    }


}
