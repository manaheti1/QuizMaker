package com.example.quizmaker.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizmaker.AddQuestionActivity;
import com.example.quizmaker.Models.Question;
import com.example.quizmaker.Models.Quiz;
import com.example.quizmaker.QuestionActivity;
import com.example.quizmaker.R;

import java.util.ArrayList;
import java.util.List;

public class QuizRecycleAdapter extends RecyclerView.Adapter<QuizRecycleAdapter.QuizViewHolder> {
    List<Quiz> list;

    public QuizRecycleAdapter(){
        list=new ArrayList<>();
    }

    public QuizRecycleAdapter(List<Quiz> list){
        this.list=list;
    }

    public void setList(List<Quiz> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuizRecycleAdapter.QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.questioncard,parent,false);
        return new QuizRecycleAdapter.QuizViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  QuizRecycleAdapter.QuizViewHolder holder, int position) {
        Quiz quiz = list.get(position);
        if(quiz!=null)
        {
            holder.idname.setText(quiz.getId()+"-"+quiz.getContext());
            holder.itemClickListener = new ItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(view.getContext(), QuestionActivity.class);
                    intent.putExtra("quiz",quiz);
                    view.getContext().startActivity(intent);
                }
            };
        }
    }

//    @Override
//    public void onBindViewHolder(@NonNull  QuestionRecycleAdapter.QuestionViewHolder holder, int position) {

//    }

    @Override
    public int getItemCount() {
        return (list!=null)?list.size():0;
    }

    public interface  ItemClickListener
    {
        public void onClick(View view , int position);
    }


    class QuizViewHolder extends RecyclerView.ViewHolder{
        public TextView idname;
        public View view;
        ItemClickListener itemClickListener;

        public QuizViewHolder(@NonNull  View itemView) {

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
