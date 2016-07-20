package com.bignerdranch.android.criminalintent0719;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by 이임경 on 2016-07-20.
 */
public class CrimeFragment extends Fragment{

    //특정 범죄의 상세 내역을 보여주고 사용자가 수정한 상세 내역을 변경하는 것이 CrimeFragment의 역할이다.
    //Crime 인스턴스의 멤버 변수와 Fragment.onCreate(Bundle)의 구현코드를 추가하자.

    private  Crime mCrime;
    private EditText mTitleField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_crime,container,false);

        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                //Crime의 제목을 설정하기 위해 사용됨.
               mCrime.setTitle(s.toString());


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return  v;
    }
}
