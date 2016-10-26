package com.ixvil.boxbonus.Models;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.JsonObject;

public class User {


    public static User staticUser;
    private final Context context;
    public JsonObject userData;


    public String name;
    public String email;
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

        editor.putString("email", email);
        editor.putString("userData", String.valueOf(this.userData));

        boolean result = editor.commit();


        User.staticUser = this;

        return result;
    }

    /**
     * @param applicationContext
     * @return boolean
     */
    static public boolean logout(Context applicationContext) {
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove("email");
        editor.remove("userData");
        User.staticUser = null;
        boolean result = editor.commit();

        return result;
    }

    /**
     * Create User From Json Object
     *
     * @param jsonObject
     * @return User
     */
    static public User createUserFromJson(String email, JsonObject jsonObject, Context context) {

        Customer customer = new Customer();
        JsonObject jOAttributes = (JsonObject) jsonObject.get("attributes");
        JsonObject jOCustomer = (JsonObject) jOAttributes.get("customer");

        customer.id = jOCustomer.get("id").getAsInt();
        customer.balance = jOCustomer.get("balance").getAsInt();
        customer.walletId = jOCustomer.get("walletId").getAsInt();

        Wallet.setWalletId(customer.walletId);

        /**
         * TODO: test and debug getContext()
         */
        User user = new User(customer, context);
        user.id = jsonObject.get("id").getAsInt();
        user.email = email;
        user.name = jOAttributes.get("name").getAsString();
        user.userData = jsonObject;

        return user;
    }


}
