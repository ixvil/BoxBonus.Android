package com.ixvil.boxbonus;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.ixvil.boxbonus.models.User;

/**
 * Created by shipin_a on 02.10.2016.
 */

public class BoxBonusApplication extends Application {


    @Override
    public void onCreate() {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("account", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);

        if(null == email){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }


}
