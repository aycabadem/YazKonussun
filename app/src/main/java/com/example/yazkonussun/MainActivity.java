package com.example.yazkonussun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextToSpeech t1;
    Button ayarlar, klnm;
    EditText editText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        ayarlar = (Button) findViewById(R.id.settingsBtn);
        klnm = (Button) findViewById(R.id.kullanimBtn);

        ayarlar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity2();
            }

        });

        klnm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openKlnmActivity();
            }

        });

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("tr", "TR"));;
                }
            }
        });



        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                boolean ikiVirgulMu = false;
                //System.out.println(arg0.toString());
                if (arg0.toString().isEmpty()) {

                } else if(arg0.toString().length()>1){
                    if (arg0.toString().substring(arg0.length() - 1).equals(".")) {
                        //System.out.println("son eleman nokta");
                        //Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();

                        //String toSpeak = editText.getText().toString();
                        //t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

                        String[] parcalar = editText.getText().toString().split(Pattern.quote("."));
                        System.out.println(parcalar[0]);
                        Log.d("tag", "dispatchKeyEvent: "+parcalar[0]);
                        String parca = parcalar[parcalar.length-1];
                        //String toSpeak = editText.getText().toString();
                        //Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                        t1.setPitch(1);
                        t1.speak(parca, TextToSpeech.QUEUE_FLUSH, null);
                    }
                    if (arg0.toString().substring(arg0.length() - 2).equals(",,")) {
                        ikiVirgulMu = true;
                    }
                }

                if(ikiVirgulMu==true) {
                    editText.setText("");
                }
                //System.out.println(arg0.toString().substring(arg0.length() - 1));
            }

        });



    }

    public void openKlnmActivity(){
        Intent intent = new Intent(this, KullanimActivity.class);
        startActivity(intent);
    }

    public void openActivity2(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
    /*
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("key pressed", String.valueOf(event.getKeyCode()));
        if(String.valueOf(event.getKeyCode()).equals("56")){
            String[] parcalar = editText.getText().toString().split(Pattern.quote("."));
            System.out.println(parcalar[0]);
            Log.d("tag", "dispatchKeyEvent: "+parcalar[0]);
            String parca = parcalar[parcalar.length-1];
            //String toSpeak = editText.getText().toString();
            //Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
            t1.setPitch(1);
            t1.speak(parca, TextToSpeech.QUEUE_FLUSH, null);
        }
        return super.dispatchKeyEvent(event);
    }
    */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 1) {
            finish();
            return true;
        }
        System.out.println("key code "+keyCode);
        System.out.println("event" +event);
        return super.onKeyDown(keyCode, event);
    }
    /*
    public void onPause() {
        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
    */
}
