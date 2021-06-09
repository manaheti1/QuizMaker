package com.example.quizmaker.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.quizmaker.Adapter.QuestionRecycleAdapter;
import com.example.quizmaker.AddQuestionActivity;
import com.example.quizmaker.Models.Question;
import com.example.quizmaker.Models.SQLiteHelper;
import com.example.quizmaker.QuestionActivity;
import com.example.quizmaker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    SQLiteHelper sql;
    RecyclerView list;
    FloatingActionButton add;
    QuestionRecycleAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter= new QuestionRecycleAdapter();
        QuestionActivity activity = (QuestionActivity) getActivity();
        sql=new SQLiteHelper(activity);
        list=view.findViewById(R.id.RecycleQuest);

        list.setAdapter(adapter);
        add=view.findViewById(R.id.addQuest);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(layoutManager);

//        q.set
//        String a[]={"2","1","3","4"};
//        q.setAnswerList(a);
//        q.setAnswer(0);
        List<Question> listq=sql.getAllQuestByQuiz(activity.getQuiz());
//        listq.add(q);
        adapter.setList(listq);
        System.out.println(listq.size()+"cacs");
        adapter.notifyDataSetChanged();
        adapter.quiz=activity.getQuiz();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AddQuestionActivity.class);
                intent.putExtra("method","POST");
//                intent.putExtra("question",question);
                intent.putExtra("quiz",activity.getQuiz());
                view.getContext().startActivity(intent);
            }
        });
    }
}