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
import java.util.UUID;

/**
 * Created by 이임경 on 2016-07-20.
 */
public class CrimeFragment extends Fragment{

    //특정 범죄의 상세 내역을 보여주고 사용자가 수정한 상세 내역을 변경하는 것이 CrimeFragment의 역할이다.
    //Crime 인스턴스의 멤버 변수와 Fragment.onCreate(Bundle)의 구현코드를 추가하자.

    private  static final String ARG_CRIME_ID="crime_id";
    private  Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;



    public static CrimeFragment newInstance(UUID crimeId){

        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID,crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
        //이제부터는 CrimeActivity에서 CrimeFragment.newInstance(UUID)를 호출하여 CrimeFragment를 생성할 것이다.
        //그리고 이 때 자신의 엑스트라에서 가져온 UUID를 메서드 인자로 전달한다.
        //이런 처리를 하도록 CrimeActivity.java의  CreateFragment() 메소드를 변경한다.
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UUID crimeId = (UUID) getActivity().getIntent()
//                .getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);

        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_crime,container,false);

        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
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
        mSolvedCheckBox.setChecked(mCrime.isSolved());
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
