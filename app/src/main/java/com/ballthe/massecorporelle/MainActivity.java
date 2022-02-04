package com.ballthe.massecorporelle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MasseCorporelle";

    private EditText ET_saisiePoids;
    private EditText ET_saisieTaille;
    private Button BT_cancel;
    private Button BT_valider;
    private LinearLayout LayoutAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mainToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolBar);

        LayoutAbout = findViewById(R.id.main_layout_about);

        ET_saisiePoids = findViewById(R.id.main_poids_edittext);
        ET_saisieTaille = findViewById(R.id.main_taille_edittext);
        BT_cancel = findViewById(R.id.main_cancel_button);
        BT_valider = findViewById(R.id.main_validate_button);

        BT_valider.setEnabled(false);
    }

    @Override
    protected void onStart(){
        super.onStart();

        BT_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetFields();
            }
        });

        ET_saisiePoids.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!ET_saisiePoids.getText().toString().equals("") && !ET_saisieTaille.getText().toString().equals("")) {
                    BT_valider.setEnabled(true);
                } else {
                    BT_valider.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
        ET_saisieTaille.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!ET_saisiePoids.getText().toString().equals("") && !ET_saisieTaille.getText().toString().equals("")) {
                    BT_valider.setEnabled(true);
                } else {
                    BT_valider.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:

                break;
            case R.id.action_delete:
                resetFields();
                break;
            case R.id.action_setting:
                LayoutAbout.setVisibility(View.VISIBLE);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void clickActivity(View view) {
        LayoutAbout.setVisibility(View.INVISIBLE);
    }

    private void resetFields(){
        ET_saisiePoids.setText("");
        ET_saisiePoids.requestFocus();
        ET_saisieTaille.setText("");
    }

    public void calculerResultat(View view) {
        float poidsEnKg = Integer.parseInt(ET_saisiePoids.getText().toString());
        float tailleEnCm = Integer.parseInt(ET_saisieTaille.getText().toString());
        float IMCnumerique = poidsEnKg / ((tailleEnCm/100) * (tailleEnCm/100));
        String IMCphrase = "";

        if (IMCnumerique <= 16) {
            IMCphrase = "Vous êtes très maigre";
        } else if (IMCnumerique <= 18) {
            IMCphrase = "Vous êtes plutôt maigre";
        } else if (IMCnumerique <= 25) {
            IMCphrase = "Vous êtes de corpulence normale";
        } else if (IMCnumerique <= 30) {
            IMCphrase = "Vous êtes en surpoids";
        } else if (IMCnumerique <= 34) {
            IMCphrase = "Vous êtes en obésité modéré";
        } else if (IMCnumerique <= 40) {
            IMCphrase = "Vous êtes en obésité sévère ";
        } else if (IMCnumerique <= 70) {
            IMCphrase = "Vous êtes en obésité morbide";
        } else {
            IMCphrase = "Vous êtes un menteur (j'espère)";
        }

        Log.v(TAG, String.valueOf(IMCnumerique));
        Log.v(TAG, IMCphrase);

        Intent resultActivity = new Intent(this,ResultActivity.class);
        resultActivity.putExtra("phrase", IMCphrase);
        resultActivity.putExtra("numerique", IMCnumerique);
        startActivity(resultActivity);

    }


}