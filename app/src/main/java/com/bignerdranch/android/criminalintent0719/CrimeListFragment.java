package com.bignerdranch.android.criminalintent0719;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 이임경 on 2016-07-20.
 */
public class CrimeListFragment extends Fragment {



    //아직까지는 빈껍데기의 프래그먼트이다. 9장 뒤에서 함.
    //SingleFragmentActivity는 이 책 전반에 걸쳐 코드를 입력하는 노력과 시간을 많이 절약해줄 것이다.

    private  static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
private  static final int REQUEST_CRIME = 1;
    private RecyclerView mCrimeRecyclerView;
    private  CrimeAdapter mAdapter;
    private boolean mSubtitleVisible; //서브타이틀의 가시성을 제어할 멤버 변수


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mCrimeRecyclerView = (RecyclerView)view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        if(savedInstanceState != null){
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }


        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE , mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if( mSubtitleVisible){
            subtitleItem.setTitle(R.string.hide_subtitle);
        }
        else{
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(),crime.getId());
                startActivity(intent);
                return true;

            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private  void updateSubtitle(){
        CrimeLab crimeLab =  CrimeLab.get(getActivity());
       // int crimeCount = crimeLab.getCrimes().size();
        int crimeSize = crimeLab.getCrimes().size();
        //String subtitle = getString(R.string.subtitle_format,crimeCount);//getString으로 서브타이틀 문자열 생성, 범죄 건수를 2번째 인자로 받음
        String subtitle = getResources()
                .getQuantityString(R.plurals.subtitle_plural, crimeSize, crimeSize);

        if( !mSubtitleVisible){
            subtitle = null; //툴바의 서브타이틀을 보여주거나 감추기 위해 mSubtitleVisible 변수의 값을 확인
        }
        AppCompatActivity activity = (AppCompatActivity) getActivity();

            activity.getSupportActionBar().setSubtitle(subtitle);

    }

    private  void updateUI(){

        UUID numId;
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime>crimes = crimeLab.getCrimes();

        if( mAdapter == null){
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }
        else{

            //어떤위치의 항목이 변경되었는지를 알아내고 그것을 다시 로드하는 것이 Ch10 챌린지이다!! ???
           /* for(int i=0;i<crimes.size();i++){

            }*/
            mAdapter.notifyDataSetChanged();

        }

        updateSubtitle();


    }


    private class CrimeHolder extends  RecyclerView.ViewHolder
    implements  View.OnClickListener{
        private Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public CrimeHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = ( TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox)itemView.findViewById(R.id.list_item_crime_solved_check_box);
            //우리의 ViewHolder가 생성되는 곳이 여기다. findViewById(int)메소드 실행은 지정된 ID를 갖는 뷰를 찾느라
            // 시간이 걸릴 수 있다. 따라서 여기서는 onCreateViewHolder에서만 crimeHolder 생성자를 호출한 후 결과로
            //반환되는 뷰 객체 참조를 변수에 저장한다.

        }

        public void bindCrime(Crime crime){  //CrimeHolder에서 뷰와 데이터 결합하기
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
           // mDateTextView.setText(mCrime.getDate().toString());
            Date now = new Date();
            mCrime.setDate(now);
            SimpleDateFormat sdf = new SimpleDateFormat("E요일,  MM dd,yyyy");
            mDateTextView.setText(sdf.format(now));
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }




        @Override
        public void onClick(View view) {
          /*  Intent intent = new Intent(getActivity(),CrimeActivity.class);*/
             //Crime객체 Id를 전달하는 newIntent()메소드를 사용하도록 CrimeHolder를 변경한다.
            //Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getId());

            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
            //startActivity(intent);
            startActivityForResult(intent,REQUEST_CRIME);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == REQUEST_CRIME){
           //결과 처리 코드
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




}
