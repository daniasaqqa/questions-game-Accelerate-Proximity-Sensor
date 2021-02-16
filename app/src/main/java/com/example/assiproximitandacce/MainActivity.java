package com.example.assiproximitandacce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etAddQuestion;
    Button btnAddQuestion;
    Button btnStartGame;
    TextView tvNumquestion;
    ArrayList<String> questionsArr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAddQuestion= findViewById(R.id.add_question);
        btnAddQuestion=findViewById(R.id.btn_add_question);
        btnStartGame=findViewById(R.id.btn_start_game);
        tvNumquestion=findViewById(R.id.number_of_question);
        questionsArr=new ArrayList<String>();

        btnAddQuestion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (etAddQuestion.getText().toString().trim().length()==0){
                    Toast.makeText(MainActivity.this,"الصندوق فارغ يرجى كتابة السؤال",Toast.LENGTH_LONG).show();
                }else{
                    String question2=etAddQuestion.getText().toString();
                    questionsArr.add(question2);
                    tvNumquestion.setText(questionsArr.size()+"");
                    etAddQuestion.setText("");
                }
            }
        });

        btnStartGame.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (questionsArr.size()<2){
                    Toast.makeText(MainActivity.this, "يجب إضافة سؤالين أو أكثر", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent= new Intent(getApplicationContext(),ChangeQeustion.class);
                    intent.putExtra("questions",questionsArr);
                    Log.d("dddd2" ,questionsArr.toString() );

                    startActivity(intent);
                }
            }
        });
    }
}