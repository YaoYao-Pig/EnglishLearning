package com.example.englishlearning.DB;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.englishlearning.LoginActivity;

public class ConfigData {
    public static String SharedDataName = "configData";
    public static boolean isNight;
    public static String isNightName = "isNight";

    public static boolean getIsNight() {
        SharedPreferences preferences = LoginActivity.getContext().getSharedPreferences(SharedDataName, Context.MODE_PRIVATE);
        isNight = preferences.getBoolean(isNightName, false);
        return isNight;
    }

    public static void setIsNight(boolean isNight) {
        SharedPreferences.Editor editor = LoginActivity.getContext().getSharedPreferences(SharedDataName, Context.MODE_PRIVATE).edit();
        editor.putBoolean(isNightName, isNight);
        editor.apply();
    }
}
