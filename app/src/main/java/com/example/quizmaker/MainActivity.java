package com.example.quizmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.QuickViewConstants;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.quizmaker.Adapter.QuizRecycleAdapter;
import com.example.quizmaker.Models.Question;
import com.example.quizmaker.Models.Quiz;
import com.example.quizmaker.Models.SQLiteHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton button;
    RecyclerView recycle;
    QuizRecycleAdapter adapter;
    SQLiteHelper sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycle=findViewById(R.id.RecycleQuiz);
        sql=new SQLiteHelper(MainActivity.this);
        adapter=new QuizRecycleAdapter(sql.getAllQuiz());
        recycle.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recycle.setAdapter(adapter);

        button=findViewById(R.id.addQuiz);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddQuizActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.btnSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Quiz> listOrders = sql.search(newText);
                adapter.setList(listOrders);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}