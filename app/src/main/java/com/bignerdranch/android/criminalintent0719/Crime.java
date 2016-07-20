package com.bignerdranch.android.criminalintent0719;

import java.util.Date;
import java.util.UUID;

/**
 * Created by 이임경 on 2016-07-19.
 */
public class Crime {

    private UUID mId;
    private  String mTitle;
    private Date mDate;//범죄가 발생한 날짜
    private  boolean mSolved;//범죄가 해결되었는지 여부


    public Crime(){
        //고유한 식별자를 생성한다.
        mId = UUID.randomUUID();
        mDate = new Date();
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

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
