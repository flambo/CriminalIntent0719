package com.bignerdranch.android.criminalintent0719;

import android.support.v4.app.Fragment;

/**
 * Created by 이임경 on 2016-07-20.
 */
public class CrimeListActivity extends  SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
