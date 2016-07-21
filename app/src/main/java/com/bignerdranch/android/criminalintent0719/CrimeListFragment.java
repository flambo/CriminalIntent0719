package com.bignerdranch.android.criminalintent0719;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 이임경 on 2016-07-20.
 */
public class CrimeListFragment extends Fragment {



    //아직까지는 빈껍데기의 프래그먼트이다. 9장 뒤에서 함.
    //SingleFragmentActivity는 이 책 전반에 걸쳐 코드를 입력하는 노력과 시간을 많이 절약해줄 것이다.


    private RecyclerView mCrimeRecyclerView;
    private  CrimeAdapter mAdapter;



    private class CrimeHolder extends  RecyclerView.ViewHolder{
        private Crime mCrime;

        public void bindCrime(Crime crime){  //CrimeHolder에서 뷰와 데이터 결합하기
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public CrimeHolder(View itemView){
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_tem_crime_title_text_view);
            mDateTextView = ( TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox)itemView.findViewById(R.id.list_item_crime_solved_check_box);
            //우리의 ViewHolder가 생성되는 곳이 여기다. findViewById(int)메소드 실행은 지정된 ID를 갖는 뷰를 찾느라
            // 시간이 걸릴 수 있다. 따라서 여기서는 onCreateViewHolder에서만 crimeHolder 생성자를 호출한 후 결과로
            //반환되는 뷰 객체 참조를 변수에 저장한다.

        }
    }

    private class CrimeAdapter extends  RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        public CrimeAdapter(List <Crime>crimes){
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime,parent,false);//새로운 list_item_crime레이아웃을 사용하기 위해
            //CrimeListFragment의 내부 클래스인 CrimeAdapter를 변경한다.
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {

            Crime crime = mCrimes.get(position);
           // holder.mTitleTextView.setText(crime.getTitle());
            //CrimeHolder는 특정 범죄의 제목 TextView , 날짜 TextView, 해결 여부CheckBox를 항목 뷰에 변경할 것이다.
            //CimreHolder는 특정 범죄의 모든 데이터를 갖게 되므로 CrimeAdapter는 새로운 bindCrime()메소드를 사용할 필요가 있다.
            holder.bindCrime(crime);
          //CrimeAdapter를 CrimeHolder에 연결하기.
        }

        @Override
        public int getItemCount() {
          return mCrimes.size();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mCrimeRecyclerView = (RecyclerView)view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        updateUI();
        return view;
    }

    private  void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime>crimes = crimeLab.getCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }
}
