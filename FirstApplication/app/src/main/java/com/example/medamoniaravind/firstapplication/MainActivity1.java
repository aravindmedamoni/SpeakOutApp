package com.example.medamoniaravind.firstapplication;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.Locale;

public class MainActivity1 extends AppCompatActivity {
    String abhi;
    TextToSpeech mn_ttp;
    SeekBar mn_pitch;
    SeekBar mn_speed;
    Button mn_speak;
    EditText textmessage;
    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        textmessage = findViewById(R.id.entermessage);
        mn_pitch=findViewById(R.id.mn_skbr_pitch);
        mn_speed=findViewById(R.id.mn_skbr_speed);
        mn_speak=findViewById(R.id.m_speak);

        mn_ttp=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==TextToSpeech.SUCCESS){
                    int result= mn_ttp.setLanguage(Locale.US);

                    if (result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("tts","lang not supprted");
                    }else {
                        mn_speak.setEnabled(true);
                    }
                }else {
                    Log.e("tts","Intialization failed");
                }

            }
        });
        mn_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

    }
    private  void speak(){
        String msg=textmessage.getText().toString();
        float pitch=mn_pitch.getProgress()/50;
        if (pitch<0.1) pitch=0.2f;
        float speed=mn_speed.getProgress()/50;
        if (pitch<0.1) pitch=0.2f;

        mn_ttp.setPitch(pitch);
        mn_ttp.setSpeechRate(speed);
        mn_ttp.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onDestroy() {
        if (mn_ttp !=null){
            mn_ttp.stop();
            mn_ttp.shutdown();
        }
        super.onDestroy();
    }
/* public void btnclick(View view){
        String aravind=textmessage.getText().toString();// some text what u enter that  can be store  in a variable
        Intent intent=new Intent(MainActivity1.this,SecondActivity.class);
        intent.putExtra(abhi,aravind); //this is used get the same data in second acivity what did u enter in this activity
        startActivity(intent);
    }*/
}
