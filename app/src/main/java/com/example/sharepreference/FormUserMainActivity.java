package com.example.sharepreference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FormUserMainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputNama;
    EditText inputEmail;
    EditText inputPhone;
    EditText inputAge;
    RadioGroup love;
    RadioButton radioYa;
    RadioButton radioNo;
    Button buttonSave;

    public static String EXTRA_TYPE_FORM= "extra_type_form";
    public static int REQUEST_CODE= 100;
    public static int TYPE_ADD= 1;
    public static int TYPE_EDIT= 2;
    public static String FIELD_REQUIRED="required file";
    int formType;

    UserModel userModel;

    final String FIELD_DIGIT_ONLY= "Hanya boleh terisi number";
    final String FIELD_EMAIL_NOT_FALID="Email tidak valid";
    public final static String EXTRA_RESULT="extra_result";
    public static final int RESULT_CODE=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user_main);
        inputNama= findViewById(R.id.input_name);
        inputEmail= findViewById(R.id.input_email);
        inputAge= findViewById(R.id.input_age);
        inputPhone= findViewById(R.id.input_phone);
        radioYa= findViewById(R.id.radio_ya);
        love= findViewById(R.id.radio_love);
        radioNo= findViewById(R.id.radio_no);
        Intent intent= getIntent();
        userModel= intent.getParcelableExtra("USER");
        buttonSave= findViewById(R.id.button_save);
        buttonSave.setOnClickListener(this);
        formType= getIntent().getIntExtra(EXTRA_TYPE_FORM, 0);
        String actionBarTitle= "";
        String buttonTitle="";
        switch (formType){
            case 1:
                actionBarTitle= "Tambah Baru";
                buttonTitle= "Simpan";
                break;
            case 2:
                actionBarTitle="Ubah";
                buttonTitle="Update";
                showPreferenceInForm();
        }
        if(getSupportActionBar()!= null){
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
        }
        buttonSave.setText(buttonTitle);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_save){
            String nama= inputNama.getText().toString().trim();
            String email= inputEmail.getText().toString().trim();
            String age= inputAge.getText().toString().trim();
            String phoneNo= inputPhone.getText().toString().trim();
            boolean loves= love.getCheckedRadioButtonId() == R.id.radio_ya;
            if(TextUtils.isEmpty(nama)){
                inputNama.setError(FIELD_REQUIRED);
                return;
            }
            if(TextUtils.isEmpty(email)){
                inputEmail.setError(FIELD_REQUIRED);
                return;
            }
            if(!isValidEmail(email)){
                inputEmail.setError(FIELD_EMAIL_NOT_FALID);
                return;
            }
            if(TextUtils.isEmpty(age)){
                inputAge.setError(FIELD_REQUIRED);
                return;
            }
            if(TextUtils.isEmpty(phoneNo)){
                inputPhone.setError(FIELD_REQUIRED);
                return;
            }
            if(!TextUtils.isDigitsOnly(phoneNo)){
                inputPhone.setError(FIELD_DIGIT_ONLY);
                return;
            }
            saveUser(nama, email, age, phoneNo, loves);
            Intent intent= new Intent();
            intent.putExtra(EXTRA_RESULT, userModel );
            setResult(RESULT_CODE, intent);
            finish();
        }
    }
    private boolean isValidEmail(CharSequence email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    void saveUser(String nama, String email, String age, String photoNo, boolean love){
        UserPreference userPreference= new UserPreference(this);
        userModel.setName(nama);
        userModel.setEmail(email);
        userModel.setAge(Integer.parseInt(age));
        userModel.setPhoneNumber(photoNo);
        userModel.setLove(love);

        userPreference.setUser(userModel);
        Toast.makeText(this, "data tersimpan", Toast.LENGTH_SHORT).show();
    }
    private void showPreferenceInForm(){
        inputNama.setText(userModel.getName());
        inputEmail.setText(userModel.getEmail());
        inputAge.setText(String.valueOf(userModel.getAge()));
        inputPhone.setText(userModel.getPhoneNumber());
        if(userModel.getLove()){
            radioYa.setChecked(true);
        }
        else{
            radioNo.setChecked(true);

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
