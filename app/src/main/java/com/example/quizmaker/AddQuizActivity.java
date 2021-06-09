package com.example.quizmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quizmaker.Models.SQLiteHelper;

public class AddQuizActivity extends AppCompatActivity {
    Button add,back;
    EditText name;
    SQLiteHelper sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);
        sql=new SQLiteHelper(AddQuizActivity.this);
        back=findViewById(R.id.Back);
        name=findViewById(R.id.name);
        add=findViewById(R.id.CreateQuiz);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddQuizActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql.addQuiz(name.getText().toString());
                Intent intent=new Intent(AddQuizActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}