package com.example.quizmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizmaker.Models.Question;
import com.example.quizmaker.Models.Quiz;
import com.example.quizmaker.Models.SQLiteHelper;
import com.example.quizmaker.Models.Test;

import java.util.List;

public class TestActivity extends AppCompatActivity {
    TextView countdown,context;
    Button Ans[]=new Button[4];
    Button Quit;
    Quiz quiz;
    List<Question> list;
    int cur=0;
    int dung=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        countdown=findViewById(R.id.Countdown);
        context=findViewById(R.id.Context);
        Ans[0]=findViewById(R.id.Ans1);
        Ans[1]=findViewById(R.id.Ans2);
        Ans[2]=findViewById(R.id.Ans3);
        Ans[3]=findViewById(R.id.Ans4);
        Intent intent=getIntent();
        Test test= (Test) intent.getSerializableExtra("test");
        quiz=(Quiz) intent.getSerializableExtra("quiz");
        list=test.getQuestionList();
        reset();




    }
    public void reset(){
        Question q=list.get(cur);
        context.setText(q.getContext());
        CountDownTimer timer=new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                countdown.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                cur++;
                if (cur==list.size()){
                    Intent intent=new Intent(TestActivity.this,ResultActivity.class);
                    intent.putExtra("test",new Test(list,cur,dung));
                    intent.putExtra("quiz",quiz);
                    startActivity(intent);
                }else
                    reset();
            }
        }.start();
        for (int i=0;i<4;i++){
            Ans[i].setText(q.getAnswerList()[i]);
            if (i==q.getAnswer()){
                Ans[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timer.cancel();
                        dung++;
                        cur++;
                        if (cur==list.size()){
                            Intent intent=new Intent(TestActivity.this,ResultActivity.class);
                            intent.putExtra("test",new Test(list,cur,dung));
                            intent.putExtra("quiz",quiz);
                            startActivity(intent);
                        }else
                        reset();
                    }
                });
            }else{
                Ans[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timer.cancel();
                        cur++;
                        if (cur==list.size()){
                            Intent intent=new Intent(TestActivity.this,ResultActivity.class);
                            intent.putExtra("test",new Test(list,cur,dung));
//                            intent.putExtra("test",new Test(list,cur,dung));
                            intent.putExtra("quiz",quiz);
                            startActivity(intent);
                        }else
                            reset();
                    }
                });
            }


        }
    }
}