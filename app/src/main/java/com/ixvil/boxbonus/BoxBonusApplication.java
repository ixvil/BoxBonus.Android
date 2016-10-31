package com.ixvil.boxbonus;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import com.google.gson.JsonObject;
import com.ixvil.boxbonus.Activities.LoginActivity;
import com.ixvil.boxbonus.Models.User;


/**
 * Created by shipin_a on 02.10.2016.
 */

public class BoxBonusApplication extends Application {


    @Override
    public void onCreate() {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("account", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);
        String textUserData = sharedPreferences.getString("userData", null);

        Context context = this.getApplicationContext();

        if (null == email || null == textUserData) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {

            JsonObject jsonObject = (new Gson()).fromJson(textUserData, JsonObject.class);
            User.staticUser = User.createUserFromJson(email, jsonObject, context);

        }

    }


}
