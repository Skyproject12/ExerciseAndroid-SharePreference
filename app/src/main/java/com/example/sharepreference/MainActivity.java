package com.example.sharepreference;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textNama;
    private TextView textAge;
    private TextView textPhone;
    private TextView textEmail;
    private TextView textLove;
    private Button buttonSave;
    private UserPreference userPreference;

    private boolean isPreferenceEmpty= false;
    private UserModel userModel;

    private final int REQUEST_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNama= findViewById(R.id.text_nama);
        textAge= findViewById(R.id.text_age);
        textEmail= findViewById(R.id.text_email);
        textPhone= findViewById(R.id.text_phone);
        textLove= findViewById(R.id.text_love);
        buttonSave= findViewById(R.id.button_save);
        buttonSave.setOnClickListener(this);


        // set action bar
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("My User Preference");
        }

        // make preference
        userPreference= new UserPreference(this);
        showExistingPreference();
    }
    private void showExistingPreference(){

        // make variable to get pengguna from freference
        userModel= userPreference.getUser();

        // setText text with freference
        populateView(userModel);

        // custom button
        checkForm(userModel);
    }

    // settext text if data kosong maka akan menampilkan tidak ada jika data tidak kosong maka akan menmapilkan data
    private void populateView(UserModel userModel){
        textNama.setText(userModel.getName().isEmpty() ? "Tidak Ada" : userModel.getName());
        textAge.setText(String.valueOf(userModel.getAge()).isEmpty()?"Tidak Ada": String.valueOf(userModel.getAge()));
        textLove.setText(userModel.getLove()? "Ya" : "Tidak");
        textEmail.setText(userModel.getEmail().isEmpty() ? "Tidak Ada ":userModel.getEmail());
        textPhone.setText(userModel.getPhoneNumber().isEmpty() ? "Tidak Ada" : userModel.getPhoneNumber());

    }

    // custom button
    private void checkForm(UserModel userModel){
        if(!userModel.getName().isEmpty()){
            buttonSave.setText("Ubah");
            isPreferenceEmpty= false;
        }
        else {
            buttonSave.setText("simpan");
            isPreferenceEmpty= true;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_save){
            // intent to formuser
            Intent intent = new Intent(MainActivity.this, FormUserMainActivity.class);

            // check freferenceEmpty true false
            if(isPreferenceEmpty){
                // send value typeAdd value 1
                intent.putExtra(FormUserMainActivity.EXTRA_TYPE_FORM, FormUserMainActivity.TYPE_ADD);
                // send userModel
                intent.putExtra("USER", userModel);

            }
            else {
                // send value typeChage value 2
                intent.putExtra(FormUserMainActivity.EXTRA_TYPE_FORM, FormUserMainActivity.TYPE_EDIT);
                // send usermodel
                intent.putExtra("USER", userModel);
            }
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode== REQUEST_CODE){
            // get data from intent parcelable
            if(requestCode== FormUserMainActivity.REQUEST_CODE){
                userModel= data.getParcelableExtra(FormUserMainActivity.EXTRA_RESULT);

                // melakukan settext kembali untuk membuat ketika ada perubahan pada data user tidak perlu melakukan buka tutup aplikasi
                populateView(userModel);
                checkForm(userModel);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
