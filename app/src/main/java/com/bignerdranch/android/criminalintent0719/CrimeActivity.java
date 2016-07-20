package com.bignerdranch.android.criminalintent0719;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CrimeActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        //코드에서 프래그먼트를 액티비티에 추가하려면 액티비티의 FragementManager를 명시적으로
        //호출해야 한다. 이 때 제일먼저 FragmentManager 인스턴스를 얻는다.
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        //*19**FragmentManager로부터 CrimeFragment를 가져와야 할 때에는 컨테이너 뷰 id로 요청한다.

        if( fragment == null){
            fragment = new CrimeFragment();
            fm.beginTransaction() //FragmentManager.beginTransaction()메소드는 FragmentTransaction의 인스턴스를 생성하여 반환함.
                    .add(R.id.fragment_container,fragment)
                    .commit();
            // **23~25**새로운 프래그먼트 트랜잭션 인스턴스를 생성하고 그 인스턴스에 프래그먼트 객체를 추가한 후 커밋한다!!

            //여기서는 프래그먼트 트랜잭션을 생성하고 커밋한다.
            //프래그맨트 트랜잭션은 프래그먼트 리스트에 프래그먼트를 추가 삭제 첨부 분리 변경하는 데 사용된다.
            //프래그먼트를 사용해서 런타임 시에 화면을 구성 또는 재구성하는 방법이 프래그먼트 트랜잭션이다.
            //FragmentManager는 프래그먼트 트랜잭션의 back 스택을 유지 관리한다.
        }

    }
}
