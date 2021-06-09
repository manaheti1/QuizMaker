package com.example.quizmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizmaker.Models.Quiz;
import com.example.quizmaker.Models.SQLiteHelper;
import com.example.quizmaker.Models.Test;

public class ResultActivity extends AppCompatActivity {
    SQLiteHelper sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent=getIntent();
        Test test=(Test)intent.getSerializableExtra("test");
        Quiz quiz=(Quiz)intent.getSerializableExtra("quiz");
        Button back=findViewById(R.id.back);
        TextView result=findViewById(R.id.result);
        sql=new SQLiteHelper(this);
        sql.addRecord(test,quiz);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1=new Intent(ResultActivity.this,QuestionActivity.class);
                intent1.putExtra("quiz",quiz);
                startActivity(intent1);
            }
        });
        result.setText(test.getDung()+"/"+test.getSoluong());
    }
}