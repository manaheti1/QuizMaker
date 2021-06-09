package com.example.quizmaker.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizmaker.AddQuestionActivity;
import com.example.quizmaker.Models.Question;
import com.example.quizmaker.Models.Quiz;
import com.example.quizmaker.Models.SQLiteHelper;
import com.example.quizmaker.R;

import java.util.List;

public class QuestionRecycleAdapter extends RecyclerView.Adapter<QuestionRecycleAdapter.QuestionViewHolder> {
    List<Question> list;
    SQLiteHelper sql;
    public Quiz quiz;
    public QuestionRecycleAdapter() {
    }

    public List<Question> getList() {
        return list;
    }

    public void setList(List<Question> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.questioncard,parent,false);
        sql=new SQLiteHelper(v.getContext());
        return new QuestionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  QuestionRecycleAdapter.QuestionViewHolder holder, int position) {
        Question question = list.get(position);
        if(question!=null)
        {
            holder.idname.setText(question.getId()+". "+question.getContext());
            holder.itemClickListener = new ItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(view.getContext(), AddQuestionActivity.class);
                    intent.putExtra("question",question);
                    intent.putExtra("method","PUT");
                    intent.putExtra("quiz",quiz);
                    view.getContext().startActivity(intent);
                }
            };
        }
    }

    @Override
    public int getItemCount() {
        return (list!=null)?list.size():0;
    }

    public interface  ItemClickListener
    {
        public void onClick(View view , int position);
    }
    class QuestionViewHolder extends RecyclerView.ViewHolder{
        public TextView idname;
        public View view;

        ItemClickListener itemClickListener;

        public QuestionViewHolder(@NonNull  View itemView) {

            super(itemView);
            idname=itemView.findViewById(R.id.txtIdName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(v,getAdapterPosition());
                }
            });
        }
    }



}
