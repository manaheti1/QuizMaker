package com.example.quizmaker.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="CC5.db";
    private static final int DATABASE_VERSION=1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String Createsql =
                "CREATE TABLE Quiz(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "context TEXT " +
                ");";
        db.execSQL(Createsql);
                Createsql="CREATE TABLE Question(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "context TEXT ," + "correct INTEGER ," +
                 "quizID INTEGER,"+
                        "attemp INTEGER ," +
                        "answerlist TEXT ," +
                        "answer INTEGER,"+
                        "correct INTEGER,"+
                        "attemp INTEGER,"+
                        "FOREIGN KEY(quizID) REFERENCES Quiz(id)"+
                ");";
        db.execSQL(Createsql);
        Createsql="CREATE TABLE Record(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "quizID INTEGER,"+
                "correct INTEGER ," +
                "total INTEGER,"+
                "FOREIGN KEY(quizID) REFERENCES Quiz(id)"+
                ");";
        db.execSQL(Createsql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addQuiz(String name){
        ContentValues v = new ContentValues();
        v.put("context",name);
        SQLiteDatabase statement = getWritableDatabase();
        return statement.insert("Quiz",null,v);
    }

    public long delQuestion(int  id){
        SQLiteDatabase st = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs={Integer.toString(id)};
        return st.delete("Question",whereClause,whereArgs);
    }

    public List<Quiz> search(String s){
        List<Quiz> orderList = new ArrayList<>();
        String whereClause="context LIKE ?";
        String[] whereArgs={"%"+s+"%"};
        SQLiteDatabase st =getReadableDatabase();
        Cursor rs =st.query("Quiz",null,whereClause,whereArgs,
                null,null,null);
        while (rs!= null && rs.moveToNext()){
            int stt = rs.getInt(0);
            String context = rs.getString(1);

            Quiz donor = new Quiz(stt,context);
            orderList.add(donor);
        }
        return orderList;
    }

    public List<Quiz> getAllQuiz(){
        List<Quiz> listDonor = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        Cursor cursor=statement.query("Quiz",null,null,
                null,null,null,null);
        while (cursor!=null && cursor.moveToNext())
        {
            int stt = cursor.getInt(0);
            String context = cursor.getString(1);

            Quiz donor = new Quiz(stt,context);
            listDonor.add(donor);
        }
        return listDonor;
    }




    public List<Question> getAllQuestByQuiz(Quiz quiz){
        List<Question> listDonor = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        String whereClause="quizID=?";
        String[] whereArgs={Integer.toString(quiz.getId())};
        Cursor cursor=statement.query("Question",null,whereClause,
                whereArgs,null,null,null);

        while (cursor!=null && cursor.moveToNext())
        {
            int stt = cursor.getInt(0);
            String context = cursor.getString(1);
            String Answers=cursor.getString(5);
            int answer=cursor.getInt(6);
            String[] Answerlist=Answers.split("-");
            Question donor = new Question();
            donor.setId(stt);
            donor.setContext(context);
            donor.setAnswer(answer);
            donor.setAnswerList(Answerlist);
            listDonor.add(donor);
        }
        return listDonor;
    }

    public int updateQuestion(Question o){
        ContentValues v = new ContentValues();
        v.put("correct", o.getContext());
        String[] stringArray=o.getAnswerList();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < stringArray.length; i++) {
            sb.append(stringArray[i]+"-");
        }
        v.put("answerlist", sb.toString());
        v.put("answer", o.getAnswer());
        SQLiteDatabase st = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs={Integer.toString(o.getId())};
        System.out.println(o.getId()+"id o day");
        return st.update("Question",v,whereClause,whereArgs);
    }



    public long addQuestion(Question o){
        ContentValues v = new ContentValues();
        v.put("context", o.getContext());
        String[] stringArray=o.getAnswerList();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < stringArray.length; i++) {
            sb.append(stringArray[i]+"-");
        }
        v.put("answerlist", sb.toString());
        v.put("answer", o.getAnswer());
        v.put("quizid",o.getQuizid());
        SQLiteDatabase statement = getWritableDatabase();
        System.out.println("Added");
        return statement.insert("Question",null,v);
    }



    public long addRecord(Test o,Quiz q){
        ContentValues v = new ContentValues();
        System.out.println(o.getDung()+"get Dung");
        v.put("correct", o.getDung());
        v.put("total", o.getSoluong());
        v.put("quizid", q.getId());
        SQLiteDatabase statement = getWritableDatabase();
        return statement.insert("Record",null,v);
    }
    public List<Test> getRecordByQuiz(Quiz quiz){
        List<Test> listDonor = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        String whereClause="quizID=?";
        String[] whereArgs={Integer.toString(quiz.getId())};
        Cursor cursor=statement.query("Record",null,whereClause,
                whereArgs,null,null,null);

        while (cursor!=null && cursor.moveToNext())
        {
            Test donor=new Test();
            int stt = cursor.getInt(0);
            int dung=cursor.getInt(2);
            int total=cursor.getInt(3);
            donor.setId(stt);
            donor.setDung(dung);
            donor.setSoluong(total);
            listDonor.add(donor);
        }
        return listDonor;
    }

}
