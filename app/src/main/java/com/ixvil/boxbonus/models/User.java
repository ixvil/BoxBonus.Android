package com.ixvil.boxbonus.models;

import android.accounts.Account;

import android.accounts.AccountManager;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.JsonObject;


public class User {


    public static int userId;
    private final Context context;
    public JsonObject userData;

    public String name;
    public int id;

    public Customer customer;

    Account account;

    /**
     * Constructor
     *
     * @param customer
     * @param context
     */
    public User(Customer customer, Context context) {
        this.customer = customer;
        this.context = context;

    }

    /**
     * Put our account into account manager
     *
     * @param email
     * @param password
     * @return boolean
     */
    public boolean saveToAccountManager(String email, String password) {
        this.account = new Account(email, "boxbonus.auth");

        SharedPreferences sharedPreferences = this.context.getSharedPreferences("account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("email", email );
        boolean result = editor.commit();


//        AccountManager am = AccountManager.get(this.context);
//        boolean result = am.addAccountExplicitly(account, password, null);

        return result;
    }


    /**
     * Create User From Json Object
     *
     * @param jsonObject
     * @return User
     */
    static public User createUserFromJson(JsonObject jsonObject, Context context) {

        Customer customer = new Customer();
        JsonObject jOAttributes = (JsonObject) jsonObject.get("attributes");
        JsonObject jOCustomer = (JsonObject) jOAttributes.get("customer");

        customer.id = jOCustomer.get("id").getAsInt();
        customer.balance = jOCustomer.get("balance").getAsInt();

        /**
         * TODO: test and debug getContext()
         */
        User user = new User(customer, context);
        user.id = jsonObject.get("id").getAsInt();
        user.userData = jsonObject;

        return user;
    }


}
