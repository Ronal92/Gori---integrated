package goriproject.ykjw.com.myapplication;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ApplyActivity extends AppCompatActivity {

    Apply_1Fragment ap1;
    Apply_2Fragment ap2;
    Apply_3Fragment ap3;
    Apply_4Fragment ap4;
    Apply_5Fragment ap5;
    PhoneCheckFragment pc;
    FragmentManager manager;
    Talent talent;

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        ap1 = new Apply_1Fragment();
        ap2 = new Apply_2Fragment();
        ap3 = new Apply_3Fragment();
        ap4 = new Apply_4Fragment();
        ap5 = new Apply_5Fragment();
        pc = new PhoneCheckFragment();

        ap1.setActivity(this);
        ap2.setActivity(this);
        ap3.setActivity(this);
        ap4.setActivity(this);
        ap5.setActivity(this);
        pc.setActivity(this);

        // 프래그먼트 매니저 가져오기
        manager = getSupportFragmentManager();

        img = (ImageView)findViewById(R.id.apply_profile1);
        Glide.with(this).load(R.drawable.profile_dummy).into(img);

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");

        for(Talent data : TalentLoader.talent_datas) {
            if(data.getTalent_id() == id) {
                talent = data;
                break;
            }
        }

    }

    public void click_next(View view) {
// 1. 프래그먼트를 실행하기위한 트랜잭션 시작
        FragmentTransaction transaction = manager.beginTransaction();
        // 2. 프래그먼트를 레이아웃에 add 한다
        transaction.replace(R.id.fragmentbatang, ap1);
        // 최초 호출되는 프래그먼트는 addToBackStack 을 사용하지 않는다
        transaction.addToBackStack(null);
        // 3. git 의 commit 과 같은 기능
        transaction.commit();
    }


    // 리스트에서 상세로 이동할때
    public void goAp2(){
        // 1. 프래그먼트를 실행하기위한 트랜잭션 시작
        FragmentTransaction transaction = manager.beginTransaction();
        // 2. 프래그먼트를 레이아웃에 add 한다
        transaction.replace(R.id.fragmentbatang, ap2);
        // 3. 커밋전에 트랜잭션 전체를 stack 에 저장을 합니다. 뒤로가기시 스택관리를 할 수 있습니다.
        transaction.addToBackStack(null);
        // 4. git 의 commit 과 같은 기능
        transaction.commit();
    }

    public void goAp3(){
        // 1. 프래그먼트를 실행하기위한 트랜잭션 시작
        FragmentTransaction transaction = manager.beginTransaction();
        // 2. 프래그먼트를 레이아웃에 add 한다
        transaction.replace(R.id.fragmentbatang, ap3);
        // 3. 커밋전에 트랜잭션 전체를 stack 에 저장을 합니다. 뒤로가기시 스택관리를 할 수 있습니다.
        transaction.addToBackStack(null);
        // 4. git 의 commit 과 같은 기능
        transaction.commit();
    }

    public void goAp4(){
        // 1. 프래그먼트를 실행하기위한 트랜잭션 시작
        FragmentTransaction transaction = manager.beginTransaction();
        // 2. 프래그먼트를 레이아웃에 add 한다
        transaction.replace(R.id.fragmentbatang, ap4);
        // 3. 커밋전에 트랜잭션 전체를 stack 에 저장을 합니다. 뒤로가기시 스택관리를 할 수 있습니다.
        transaction.addToBackStack(null);
        // 4. git 의 commit 과 같은 기능
        transaction.commit();
    }

    public void goAp5(){
        // 1. 프래그먼트를 실행하기위한 트랜잭션 시작
        FragmentTransaction transaction = manager.beginTransaction();
        // 2. 프래그먼트를 레이아웃에 add 한다
        transaction.replace(R.id.fragmentbatang, ap5);
        // 3. 커밋전에 트랜잭션 전체를 stack 에 저장을 합니다. 뒤로가기시 스택관리를 할 수 있습니다.
        transaction.addToBackStack(null);
        // 4. git 의 commit 과 같은 기능
        transaction.commit();
    }

    public void goPc(){
        // 1. 프래그먼트를 실행하기위한 트랜잭션 시작
        FragmentTransaction transaction = manager.beginTransaction();
        // 2. 프래그먼트를 레이아웃에 add 한다
        transaction.replace(R.id.fragmentbatang, pc);
        // 3. 커밋전에 트랜잭션 전체를 stack 에 저장을 합니다. 뒤로가기시 스택관리를 할 수 있습니다.
        transaction.addToBackStack(null);
        // 4. git 의 commit 과 같은 기능
        transaction.commit();
    }

    // Detail 프래그먼트에서 List 로 돌아갈때
    public void backToList(){
        // 뒤로가기로 스택을 빼내면 된다 - popBackStack 에 해당하는 함수이다.
        super.onBackPressed();
//        // 1. 프래그먼트를 실행하기위한 트랜잭션 시작
//        FragmentTransaction transaction = manager.beginTransaction();
//        // 2. detail 프래그먼트를 스택에서 제거한다
//        transaction.remove(detail);
//        // 3. 커밋
//        transaction.commit();
    }


}
