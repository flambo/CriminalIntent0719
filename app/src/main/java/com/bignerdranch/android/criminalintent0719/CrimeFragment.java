package com.bignerdranch.android.criminalintent0719;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 이임경 on 2016-07-20.
 */
public class CrimeFragment extends Fragment{

    //특정 범죄의 상세 내역을 보여주고 사용자가 수정한 상세 내역을 변경하는 것이 CrimeFragment의 역할이다.
    //Crime 인스턴스의 멤버 변수와 Fragment.onCreate(Bundle)의 구현코드를 추가하자.

    private  Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

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

                //onTextChanged(...)에서는 사용자가 입력한 데이터 값을 갖고 있는
                //CharSequence객체의 toString()메소드를 호출한다. 그리고 이 메소드에서 반환하는
                //문자열은 Crime의 제목을 설정하기 위해 사용된다.

                mCrime.setTitle(s.toString());





            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDateButton = (Button)v.findViewById(R.id.crime_date);//버튼의 객체 참조를 얻고
     //   mDateButton.setText(mCrime.getDate().toString());//그것의 텍스트를 범죄 발생일자로 지정한 후
        mDateButton.setEnabled(false);//비활성화 한다.


        Date now = new Date();
        mCrime.setDate(now);

        SimpleDateFormat sdf = new SimpleDateFormat("E요일,  MM dd,yyyy");
        mDateButton.setText(sdf.format(now));

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //범죄 해결 여부 속성 값을 설정한다.
                mCrime.setSolved(isChecked);

            }
        });
        return  v;
    }
}
