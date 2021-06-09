package com.example.quizmaker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.quizmaker.Fragment.Adapter;
import com.example.quizmaker.Models.Question;
import com.example.quizmaker.Models.Quiz;
import com.example.quizmaker.Models.SQLiteHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    BottomNavigationView Navigator;
    ViewPager vp;
    Adapter adapter;
    Quiz quiz;
    public SQLiteHelper sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        vp=findViewById(R.id.viewPager);
//        sql=new SQLiteHelper(this);

        Navigator=findViewById(R.id.nav);
        Intent intent=getIntent();
        quiz=(Quiz)intent.getSerializableExtra("quiz");
        System.out.println(quiz.getContext()+"VIP");
        Bundle bundle = new Bundle();

        adapter=new Adapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vp.setAdapter(adapter);
//        adapter.getItem()
        Navigator.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Main: vp.setCurrentItem(0); break;
                    case R.id.Test: vp.setCurrentItem(1); break;
                    case R.id.Set: vp.setCurrentItem(2); break;
                    case R.id.Reminder: vp.setCurrentItem(3); break;
                    default: vp.setCurrentItem(0); break;
                }
                return true;
            }
        });
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: Navigator.getMenu().findItem(R.id.Main).setChecked(true); break;
                    case 1: Navigator.getMenu().findItem(R.id.Test).setChecked(true); break;
                    case 2: Navigator.getMenu().findItem(R.id.Set).setChecked(true); break;
                    case 3: Navigator.getMenu().findItem(R.id.Reminder).setChecked(true); break;
                    default: Navigator.getMenu().findItem(R.id.Main).setChecked(true); break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public Quiz getQuiz(){
        return quiz;
    }


}