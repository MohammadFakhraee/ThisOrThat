package com.android.mohammadhf.thisorthat.model;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "ANSWER".
 */
@Entity
public class answer {

    @Id(autoincrement = true)
    private Long id;
    private String choose_answer;
    private String other_answer;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public answer() {
    }

    public answer(Long id) {
        this.id = id;
    }

    @Generated
    public answer(Long id, String choose_answer, String other_answer) {
        this.id = id;
        this.choose_answer = choose_answer;
        this.other_answer = other_answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChoose_answer() {
        return choose_answer;
    }

    public void setChoose_answer(String choose_answer) {
        this.choose_answer = choose_answer;
    }

    public String getOther_answer() {
        return other_answer;
    }

    public void setOther_answer(String other_answer) {
        this.other_answer = other_answer;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
