package com.example.englishlearning;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.example.englishlearning.DB.DataBaseController;
import com.example.englishlearning.enity.User;

public class RegisterActivity extends Activity {

    private DataBaseController dataBaseController;
    private EditText username;
    private EditText password;
    private EditText repeatPassword;
    private Button register;

    private void showDialog(String title,String message,String buttonText){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(buttonText, null)
                .show();
    }
    private void register(){
        String un=username.getText().toString();
        String up=password.getText().toString();
        String rup=repeatPassword.getText().toString();
        if(un.isEmpty()){
            showDialog("Register error!","Username can't be empty","confirm");

        }
        if(up.isEmpty()){
            showDialog("Register error!","Password can't be empty","confirm");

        }
        if(rup.isEmpty()){
            showDialog("Register error!","Repeat Password can't be empty","confirm");
        }
        User user2 =new User(-1,un,up);
        Integer result=dataBaseController.registerUser(user2);
        if(result==Status.SUCCESS&&up.equals(rup)){
            new AlertDialog.Builder(this)
                    .setTitle("Register Success!")
                    .setMessage("Please login")
                    .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            finish();
                        }
                    })
                    .show();





        }
        else if(result==Status.HAS_EXIST){
            showDialog("Register error!","your account has been registered","confirm");
        }

    }

    private void initialize(){
        username=findViewById(R.id.re_username);
        password=findViewById(R.id.re_password);
        repeatPassword=findViewById(R.id.re_repeatpassword);
        register=findViewById(R.id.re_registerButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dataBaseController=new DataBaseController(RegisterActivity.this);
        initialize();
    }
}
