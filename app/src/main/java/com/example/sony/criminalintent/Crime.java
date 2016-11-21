package com.example.sony.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by sony on 09-11-2016.
 */

public class Crime {
    private UUID mId;
    private String mTitle;

    private Date mDate;
    private boolean mSolved;

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {

        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Crime(){
        mId= UUID.randomUUID();
        mDate=new Date();
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Date getDate() {
        return mDate;
    }
}
