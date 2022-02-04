package com.ballthe.massecorporelle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView TV_numerique;
    private TextView TV_alphanumerique;
    String alphanumerique = "";
    float numerique = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle b = getIntent().getExtras();
        alphanumerique = b.getString("phrase");
        numerique = b.getFloat("numerique");
        TV_alphanumerique = findViewById(R.id.tv_alphanumerique);
        TV_numerique = findViewById(R.id.tv_numerique);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TV_alphanumerique.setText(alphanumerique);
        TV_numerique.setText(String.valueOf(numerique));
    }
}