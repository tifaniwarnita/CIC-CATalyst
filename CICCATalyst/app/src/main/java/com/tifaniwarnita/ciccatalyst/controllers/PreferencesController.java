package com.tifaniwarnita.ciccatalyst.controllers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tifani on 4/9/2016.
 */
public class PreferencesController {
    private static final String CIC_CATALYST_SETTINGS = "com.tifaniwarnita.ciccatalyst.cicsettings";
    private static final String ID = "id";
    private static final String NAME = "name";

    public static void setUser(Context context, String id, String name) {
        SharedPreferences settings = context.getSharedPreferences(CIC_CATALYST_SETTINGS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(ID, id);
        editor.putString(NAME, name);
        editor.commit();
    }

    public static String getId(Context context) {
        SharedPreferences settings = context.getSharedPreferences(CIC_CATALYST_SETTINGS, 0);
        String id = settings.getString(ID, null);
        return id;
    }
    public static String getName(Context context) {
        SharedPreferences settings = context.getSharedPreferences(CIC_CATALYST_SETTINGS, 0);
        String name = settings.getString(NAME, null);
        return name;
    }

    public static boolean isLoggedIn(Context context) {
        if (getName(context) != null && getId(context) != null) {
            return true;
        } else {
            return false;
        }
    }

}
