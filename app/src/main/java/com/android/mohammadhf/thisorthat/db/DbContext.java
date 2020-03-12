package com.android.mohammadhf.thisorthat.db;

import android.content.Context;
import com.android.mohammadhf.thisorthat.model.DaoMaster;
import com.android.mohammadhf.thisorthat.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class DbContext {

    public static final String DATABASE_NAME = "dbContactAndSangh.sqlite";
    public static final int DATABASE_VERSION = 1;

    private static DaoMaster.DevOpenHelper sDevOpenHelper;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static final boolean ENCRYPTED = true;

    private static Database database;

    public DbContext(Context context) {
        sDevOpenHelper = new DaoMaster.DevOpenHelper(context,DATABASE_NAME ,null); //The users-db here is the name of our database.
        database = sDevOpenHelper.getWritableDb();
        daoMaster = new DaoMaster(database);

    }

    public static DaoSession newSession() {
        if (daoMaster == null) {
            throw new RuntimeException("sDaoMaster is null.");
        }
        return daoMaster.newSession();
    }

}
