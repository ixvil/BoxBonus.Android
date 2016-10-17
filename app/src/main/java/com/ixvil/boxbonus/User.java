package com.ixvil.boxbonus;

import android.app.Fragment;
import android.content.Context;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by shipin_a on 02.10.2016.
 */

public class User {
    public static JsonObject userData;
    private Context context;
    public static int userId = 0;

    public User(Context context) {
        this.context = context;

    }


    public void vkAuth(String vkId) {

        Ion.with(context)
                .load("http://52.32.2.151/json/getcustomer/1")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        User.userData = result;

                        // do stuff with the result or error
                    }
                });
    }


}
