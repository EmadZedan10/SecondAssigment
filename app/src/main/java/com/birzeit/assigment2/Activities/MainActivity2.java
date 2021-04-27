package com.birzeit.assigment2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.birzeit.assigment2.Models.CV;
import com.birzeit.assigment2.R;

public class MainActivity2 extends AppCompatActivity {

    public EditText skills_edt;
    public EditText workExp_edt;
    public Button saveBtn;

    public static final String WORKEXP = "WORKEXP";
    public static final String SKILLS = "SKILLS";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setupView();
        setUpSharedPrefs();
        checkPrefs();
    }

    private void setupView() {
        skills_edt = findViewById(R.id.skills_edt);
        workExp_edt = findViewById(R.id.workExp_edt);
        saveBtn = findViewById(R.id.saveBtn);
    }

    private void setUpSharedPrefs() {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }


    private void checkPrefs() {

        String workexp = prefs.getString(WORKEXP, "");
        String skills = prefs.getString(SKILLS, "");

        workExp_edt.setText(workexp);
        skills_edt.setText(skills);
    }

    public void saveButtonAction(View view) {

        InputMethodManager mgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(),0);

        String workexp = workExp_edt.getText().toString();
        String skills = skills_edt.getText().toString();


        editor.putString(WORKEXP, workexp);
        editor.putString(SKILLS, skills);

        editor.commit();

        CV[] cvs = new CV[2];

        Intent intent = getIntent();
        String Name = intent.getStringExtra("nameData");
        String Gender = intent.getStringExtra("genderData");
        String MotherLang = intent.getStringExtra("languageData");
        String Address = intent.getStringExtra("addressData");
        String Email = intent.getStringExtra("emailData");
        String Phone = intent.getStringExtra("phoneData");

        cvs[0] = new CV(Name, Gender, MotherLang, Address, Email, Phone, workexp, skills);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String cvString = gson.toJson(cvs);

        editor.putString("123", cvString);
        editor.commit();
        Toast.makeText(this, "Data Saved:\n" + cvString, Toast.LENGTH_SHORT).show();
    }
}