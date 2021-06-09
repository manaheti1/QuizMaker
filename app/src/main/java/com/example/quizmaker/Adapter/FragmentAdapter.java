package com.example.quizmaker.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.quizmaker.Fragment.MainFragment;
import com.example.quizmaker.Fragment.StatisticFragment;
import com.example.quizmaker.Fragment.TestFragment;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    int num=3;
    public Fragment[] list;
    public FragmentAdapter(@NonNull  FragmentManager fm, int behavior) {
        super(fm, behavior);
        list[0]=new  MainFragment();
        list[1]=new TestFragment();
        list[2]=new StatisticFragment();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return  list[0];
            case 1: return  list[1];
            case 2: return  list[2];
            default: return  list[0];
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
