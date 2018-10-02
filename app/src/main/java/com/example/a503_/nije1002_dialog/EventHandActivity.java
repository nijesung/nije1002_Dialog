package com.example.a503_.nije1002_dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EventHandActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        Toast.makeText(
                this, "뷰를 소유한 클래스에 구현해서 사용", Toast.LENGTH_LONG).show();
    }

    // 화면을 터치했을 때 좌표를 기억할 변수 생성
    float initX;
    // 백 버튼을 눌렀을 때의 시간을 기억하기 위한 변수
    long initBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_hand);

        // 버튼 찾아오기
        Button btn01 = (Button)findViewById(R.id.btn01);

        // 현재 객체를 이벤트 핸들러로 지정
        btn01.setOnClickListener(this);

        // 별도로 만들어진 클래스를 이용해서 이벤트 처리
        // btn01.setOnClickListener(new EventHandler());

        /*
        // anonymous 클래스를 이용한 이벤트 처리
        btn01.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        EventHandActivity.this, "익명 클래스를 이용한 이벤트 처리",
                        Toast.LENGTH_LONG).show();
            }
        });
        */
    }

    // 이벤트 처리를 위한 클래스
        private class EventHandler implements View.OnClickListener{
        // getApplicationContext() 는 애플리케이션 전체에서 사용 가능한
        // Context
        @Override
        public void onClick(View v) {
            Toast.makeText(
                    getApplicationContext(), "별도의 클래스를 만들어서 이벤트 처리",
                    Toast.LENGTH_LONG).show();
        }
    }

    // 화면을 터치했을 때 호출되는 메소드
    // event 에 터치에 대한 정보(터치가 발생한 좌표와 동작)가 전부 저장되어 있다.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 처음 누를 때 수행
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            // 누른 곳의 x 좌표를 저장
            initX = event.getRawX();
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            if(event.getX() - initX >= 30){
                Toast.makeText(this, "오른쪽으로 스와이프", Toast.LENGTH_LONG).show();
            }else if(event.getX() - initX <= -30){
                Toast.makeText(this, "왼쪽으로 스와이프", Toast.LENGTH_LONG).show();
            }
        }
        return super.onTouchEvent(event);
    }

    // 키보드를 눌렀을 때 호출되는 메소드
    // 누른 키보드 값은 keyCode 에 저장되어 넘어온다.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 백 버튼을 눌렀다면
        if(keyCode == KeyEvent.KEYCODE_BACK){
            // initBack 과 누른 시간 차이가 3초 이상이면
            if(System.currentTimeMillis() - initBack > 3000){
                Toast.makeText(this, "3초안에 두번 누르면 종료된다.", Toast.LENGTH_LONG).show();
                initBack = System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
