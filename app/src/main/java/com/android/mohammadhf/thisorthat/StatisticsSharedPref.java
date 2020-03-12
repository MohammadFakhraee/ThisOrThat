package com.android.mohammadhf.thisorthat;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.mohammadhf.thisorthat.model.UserStatistic;

public class StatisticsSharedPref {
    private static final String USER_ANSWERS_STATISTICS = "user_statistics_sharedpref";
    private SharedPreferences preferences;

    private static final String KEY_KOL = "kol";
    private static final String KEY_KHAS = "khas";
    private static final String KEY_NORMAL = "normal";
    private static final String KEY_BI_ASAR = "bi_asar";

    public StatisticsSharedPref(Context context) {
        preferences = context.getSharedPreferences(USER_ANSWERS_STATISTICS, Context.MODE_PRIVATE);
    }

    public void saveUserAnswersStatistics(UserStatistic statistics) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_KOL,statistics.getKol());
        editor.putInt(KEY_KHAS,statistics.getKhas());
        editor.putInt(KEY_NORMAL,statistics.getNormal());
        editor.putInt(KEY_BI_ASAR,statistics.getBiAsar());
        editor.apply();
    }

    public UserStatistic getUserAnswersStatistics(){
        UserStatistic statistic = new UserStatistic();
        statistic.setKol(preferences.getInt(KEY_KOL,0));
        statistic.setKol(preferences.getInt(KEY_KHAS,0));
        statistic.setKol(preferences.getInt(KEY_NORMAL,0));
        statistic.setKol(preferences.getInt(KEY_BI_ASAR,0));
        return statistic;
    }
}
