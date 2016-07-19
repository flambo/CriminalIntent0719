package com.bignerdranch.android.criminalintent0719;

import java.util.UUID;

/**
 * Created by 이임경 on 2016-07-19.
 */
public class Crime {

    private UUID mId;
    private  String mTitle;

    public Crime(){
        //고유한 식별자를 생성한다.
        mId = UUID.randomUUID();
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
}
