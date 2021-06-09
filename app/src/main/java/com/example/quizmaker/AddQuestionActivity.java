package com.example.quizmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.quizmaker.Models.Question;
import com.example.quizmaker.Models.Quiz;
import com.example.quizmaker.Models.SQLiteHelper;

public class AddQuestionActivity extends AppCompatActivity {
    EditText name,context,ans1,ans2,ans3,ans4;
    Button Done,Del,Back;
    Spinner spinner;
    SQLiteHelper sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
//        System.out.println("dc roi");
        Intent intent=getIntent();
        sql=new SQLiteHelper(this);
        name = findViewById(R.id.name);
        context = findViewById(R.id.context);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        Done = findViewById(R.id.done);
        Back = findViewById(R.id.back);
        Del = findViewById(R.id.del);
        spinner = findViewById(R.id.spinner);
        String method=(String)intent.getStringExtra("method");
        Quiz quiz = (Quiz) intent.getSerializableExtra("quiz");
        System.out.println(quiz.getContext());
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddQuestionActivity.this,QuestionActivity.class);
                intent.putExtra("quiz",quiz);
                startActivity(intent);
            }
        });

        if (method.equals("PUT")) {
            Question q = (Question) intent.getSerializableExtra("question");
//            name.setText(q.getName());
            context.setText(q.getContext());
            String[] a = q.getAnswerList();
            ans1.setText(a[0]);
            ans2.setText(a[1]);
            ans3.setText(a[2]);
            ans4.setText(a[3]);
            spinner.setSelection(q.getAnswer());
            Del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sql.delQuestion(q.getId());
                }
            });
            Done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("PUT DONE");
                    q.setContext(context.getText().toString());
                    q.setAnswer(spinner.getSelectedItemPosition());
                    String AnsList[]={ans1.getText().toString(),ans2.getText().toString(),ans3.getText().toString(),ans4.getText().toString()};
                    q.setAnswerList(AnsList);
                    sql.updateQuestion(q);
                }
            });
        }else{
            Del.setClickable(false);
            Done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Question o=new Question();
                    o.setContext(context.getText().toString());
                    o.setAnswer(spinner.getSelectedItemPosition());
                    String AnsList[]={ans1.getText().toString(),ans2.getText().toString(),ans3.getText().toString(),ans4.getText().toString()};
                    System.out.println(AnsList.length+"oke");
                    o.setAnswerList(AnsList);
                    o.setQuizid(quiz.getId());
                    sql.addQuestion(o);
                }
            });
        }
    }
}