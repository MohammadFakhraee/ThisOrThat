package com.android.mohammadhf.greendaolib;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {

    public static void main(String[] args) throws Exception{
        Schema schema = new Schema(1, "com.android.mohammadhf.thisorthat.model");
        schema.enableKeepSectionsByDefault();
        addTables(schema);
        DaoGenerator dg = new DaoGenerator();
        dg.generateAll(schema, "./app/src/main/java");

    }

    private static void addTables(Schema schema) {
        Entity userTracker = addAnswered(schema);
    }

    public static Entity addAnswered(Schema shema){
        Entity answers = shema.addEntity("answer");
        answers.addIdProperty().primaryKey().autoincrement();
        answers.addStringProperty("choose_answer");
        answers.addStringProperty("other_answer");

        return answers;
    }
}
