package com.wordpress.oksareinaldi.servislaptop.Session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by abah on 07/05/18.
 */

public class SessionManager {
    public static final String SESSION_SERVIS = "sessionServis";

    public static final String SESSION_USERNAME = "sessionUsername";
    public static final String SESSION_STATUS = "SessionStatus";
     SharedPreferences session;
     SharedPreferences.Editor sessionEditor;

     public SessionManager(Context context){
         session = context.getSharedPreferences(SESSION_SERVIS, Context.MODE_PRIVATE);
         sessionEditor = session.edit();
     }

     public void saveStr(String keySP, String value){
         sessionEditor.putString(keySP, value);
         sessionEditor.commit();
     }

     public void saveBool(String keySP, boolean value){
         sessionEditor.putBoolean(keySP, value);
         sessionEditor.commit();
     }

    public Boolean getSessionStatus(){
         return session.getBoolean(SESSION_STATUS, false);

    }
    public String getSessionUsername(){
        return session.getString(SESSION_USERNAME, "");

    }


}
