package com.example.quizmaker.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizmaker.Models.Question;
import com.example.quizmaker.Models.Quiz;
import com.example.quizmaker.Models.SQLiteHelper;
import com.example.quizmaker.Models.Test;
import com.example.quizmaker.QuestionActivity;
import com.example.quizmaker.R;
import com.example.quizmaker.TestActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticFragment extends Fragment {
    BarChart barChart;
    List<Test> list;
    SQLiteHelper sql;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatisticFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatisticFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticFragment newInstance(String param1, String param2) {
        StatisticFragment fragment = new StatisticFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        QuestionActivity activity = (QuestionActivity) getActivity();
        barChart=activity.findViewById(R.id.BarChart);
        Quiz quiz=activity.getQuiz();
        sql=new SQLiteHelper(activity);
        list=sql.getRecordByQuiz(quiz);
        BarData barData=new BarData();
        BarDataSet barDataSet1=new BarDataSet(dataValues1(),"Data 1");
        barData.addDataSet(barDataSet1);
        barChart.setData(barData);
        barChart.invalidate();

    }
    private ArrayList<BarEntry> dataValues1(){
        ArrayList<BarEntry> dataVal=new ArrayList<>();
        for (int i=0;i<list.size();i++){
//            System.out.println(list.get(i).getDung()+"-"+i);
            dataVal.add(new BarEntry(i,list.get(i).getDung()*100/list.get(i).getSoluong()));
        }
        return dataVal;
    }
}