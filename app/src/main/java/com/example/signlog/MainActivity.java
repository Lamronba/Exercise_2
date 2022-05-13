package com.example.signlog;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button signin,account;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main  );

        username = findViewById(R.id.username);
        repassword = findViewById(R.id.repassword);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signin);
        account = findViewById(R.id.account);
        DB= new DBHelper(this);

        signin.setOnClickListener(view -> {
           String user= username.getText().toString();
           String pass= password.getText().toString();
           String repass= repassword.getText().toString();

           if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
               Toast.makeText(MainActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
           else {
               if(pass.equals(repass)){
                   Boolean checkuser = DB.checkusername(user);
                   if(!checkuser){
                       Boolean insert = DB.insertData(user,pass);
                       if(insert){
                           Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(getApplicationContext(), HomeAct.class);
                           startActivity(intent);
                       }else {
                           Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                       }
                   }else {
                       Toast.makeText(MainActivity.this,"User already registered",Toast.LENGTH_SHORT).show();
                   }
               }else {
                   Toast.makeText(MainActivity.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
               }
           }
        });

        account.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), signlog2.class);
            startActivity(intent);

        });
    }
}