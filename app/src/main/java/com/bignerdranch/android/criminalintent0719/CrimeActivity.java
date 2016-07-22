package com.bignerdranch.android.criminalintent0719;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity{


    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent0719.crime_id";

    public  static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent  = new Intent(packageContext,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
        //여기서는 명시적인 인텐트를 생성한 후에 문자열 키와 그 키의 값 (Crime 객체 Id)을 인자로 전달하여 putExtra()를 호출한다.
    }
    @Override
    protected Fragment createFragment() {
      UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);

    }
}
