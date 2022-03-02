package com.aldim.my_aiml_bot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtBot;
    EditText txtUser;
    aiml_bot aibot = new aiml_bot();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBot = findViewById(R.id.textView);
        txtUser = findViewById(R.id.editTextTextPersonName);

        aibot.aiml_bot_init("super",this);

    }

    public void user_send (View view) {
        String strBot;
        strBot = aibot.aiml_bot_request(txtUser.getText().toString());
        txtBot.setText(strBot);
        txtUser.setText("");
    }

}