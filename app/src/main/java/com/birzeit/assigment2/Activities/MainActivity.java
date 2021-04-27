package com.birzeit.assigment2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.birzeit.assigment2.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public EditText firstname__edt;
    public EditText lastname__edt;
    public EditText address__edt;
    public EditText email__edt;
    public EditText phone__edt;
    public RadioGroup radioGroup;
    public RadioButton male_rdBtn;
    public RadioButton female_rdBtn;
    public Button next_btn;
    public Spinner spinner;

    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final String GENDER = "GENDER";
    public static final int LANGUAGES = 0;
    public static final String ADDRESS = "ADDRESS";
    public static final String EMAIL = "EMAIL";
    public static final String PHONE = "PHONE";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
        populateSpinner();
        setUpSharedPrefs();
        checkPrefs();
    }

    public RadioButton radio_button;
    private void setupView() {
        firstname__edt = findViewById(R.id.firstname__edt);
        lastname__edt = findViewById(R.id.lastname__edt);
        address__edt = findViewById(R.id.address__edt);
        email__edt = findViewById(R.id.email__edt);
        phone__edt = findViewById(R.id.phone__edt);
        radioGroup = findViewById(R.id.radioGroup);
        male_rdBtn = findViewById(R.id.male_rdBtn);
        female_rdBtn = findViewById(R.id.female_rdBtn);
        next_btn = findViewById(R.id.next_btn);
        spinner = findViewById(R.id.spinner);

        int radioId = radioGroup.getCheckedRadioButtonId();
        radio_button = findViewById(radioId);
    }

    private void populateSpinner() {

        ArrayList<String> languages = new ArrayList<>();

        languages.add("Arabic");languages.add("English");languages.add("French");languages.add("Russian");languages.add("Italian");languages.add("Japanese");
        languages.add("Turkish");languages.add("Hindi");

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);
        spinner.setAdapter(monthAdapter);
    }

    private void setUpSharedPrefs() {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }


    private void checkPrefs() {

        String FirstName = prefs.getString(FIRSTNAME, "");
        String LastName = prefs.getString(LASTNAME, "");
        String Gender = prefs.getString(GENDER, "");
        String Address = prefs.getString(ADDRESS, "");
        String Email = prefs.getString(EMAIL, "");
        String Phone = prefs.getString(PHONE, "");
        int languages = prefs.getInt(String.valueOf(LANGUAGES), 0);

        spinner.setSelection(languages);

        firstname__edt.setText(FirstName);
        lastname__edt.setText(LastName);

        if(Gender.equals("Male")){
            male_rdBtn.setChecked(true);
        }else if (Gender.equals("Female")){
            female_rdBtn.setChecked(true);
        }
        address__edt.setText(Address);
        email__edt.setText(Email);
        phone__edt.setText(Phone);
    }


    public void nextButtonAction(View view) {

        InputMethodManager mgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(),0);

        Intent intent = new Intent(this, MainActivity2.class);

        String FirstName = firstname__edt.getText().toString();
        String LastName = lastname__edt.getText().toString();

        String Name = FirstName + " " + LastName;

        String Gender = " ";
        if(male_rdBtn.isChecked()){
            Gender = "Male";
        }else if (female_rdBtn.isChecked()){
            Gender = "Female";
        }

        String MotherLang = spinner.getSelectedItem().toString();
        int lang = spinner.getSelectedItemPosition();

        String Address = address__edt.getText().toString();
        String Email = email__edt.getText().toString();
        String Phone = phone__edt.getText().toString();

        intent.putExtra("nameData",Name);
        intent.putExtra("genderData",Gender);
        intent.putExtra("languageData",MotherLang);
        intent.putExtra("addressData",Address);
        intent.putExtra("emailData",Email);
        intent.putExtra("phoneData",Phone);


        editor.putString(FIRSTNAME, FirstName);
        editor.putString(LASTNAME, LastName);
        editor.putString(GENDER, Gender);
        editor.putString(ADDRESS, Address);
        editor.putString(EMAIL, Email);
        editor.putString(PHONE, Phone);
        editor.putInt(String.valueOf(LANGUAGES), lang);


        editor.commit();

        startActivity(intent);
    }
}