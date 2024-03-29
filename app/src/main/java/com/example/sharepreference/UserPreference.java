package com.example.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
    // digunakan untuk mendefinisikan key
    private static final String PREF_NAME= "user_pref";
    private static final String NAME="name";
    private static String EMAIL="email";
    private static final String PHONE_NUMBER="phone";
    private static final String LOVE_MU="love";
    private static final String AGE="age";

    // membuat sharefreperence
    private final SharedPreferences sharedPreferences;
    // membuat constructor dari sharefreference
    UserPreference(Context context){
        sharedPreferences= context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    // make setter sharefreference
    public void setUser(UserModel va){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(NAME, va.name);
        editor.putString(EMAIL, va.email);
        editor.putInt(AGE, va.age);
        editor.putString(PHONE_NUMBER, va.phoneNumber);
        editor.putBoolean(LOVE_MU,va.Love);
        // lakukan commit  dari object editor yang digunakan
        editor.commit();
    }



    // make getter shareeference
    UserModel getUser(){
        // call model
        UserModel userModel= new UserModel();
        // get sharefreference
        userModel.setName(sharedPreferences.getString(NAME, ""));
        userModel.setEmail(sharedPreferences.getString(EMAIL, ""));
        userModel.setAge(sharedPreferences.getInt(AGE, 0));
        userModel.setPhoneNumber(sharedPreferences.getString(PHONE_NUMBER,""));
        userModel.setLove(sharedPreferences.getBoolean(LOVE_MU, false));
        //return object model
        return userModel;
    }
}
