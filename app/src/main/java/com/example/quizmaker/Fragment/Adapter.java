package com.example.quizmaker.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Adapter extends FragmentStatePagerAdapter {
    int num=4;

    public Adapter(@NonNull  FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new MainFragment();
            case 1: return new TestFragment();
            case 2: return new StatisticFragment();
            case 3: return new ReminderFragment();
            default: return new MainFragment();
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
