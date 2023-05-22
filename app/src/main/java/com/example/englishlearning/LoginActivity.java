package com.example.englishlearning;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.englishlearning.Controller.DownLoadController;
import com.example.englishlearning.Controller.JsonController;
import com.example.englishlearning.Controller.UserController;
import com.example.englishlearning.Controller.WordPageController;
import com.example.englishlearning.DB.ConfigData;
import com.example.englishlearning.DB.DataBaseController;
import com.example.englishlearning.enity.User;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static Context context;
    public static Context getContext() {
        return context;
    }

    private static final int MY_REQUEST_CODE = 1000;
    Button login;
    Button register;
    CheckBox checkBox;

    EditText username;
    EditText password;
    ImageView eye;
    DataBaseController dataBaseController;
    UserController userController;
    Boolean hasOpeneye=false;
    SharedPreferences sp = null;



    private String TAG = "Learning";

    private String[] permissions=new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_NETWORK_STATE,


    };

    private void initialize(){
        dataBaseController=new DataBaseController(LoginActivity.this);
        login=(Button) findViewById(R.id.loginButton);
        register=(Button) findViewById(R.id.registerButton);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);

        eye=findViewById(R.id.eye);
        checkBox=findViewById(R.id.checkBoxLogin);

        if (sp.getBoolean("checkboxBoolean", false)){
            username.setText(sp.getString("uname", null));
            password.setText(sp.getString("upswd", null));
            checkBox.setChecked(true);
        }

        userController=new UserController(dataBaseController);

        JsonController.assetManager=getAssets();
        WordPageController.dataBaseController=dataBaseController;
        eye.setOnClickListener(new myClick());




        initializePermission();
    }

    private void initializePermission(){

        PackageManager packageManager = this.getPackageManager();

        PermissionInfo permissionInfo = null;


        for (int i = 0; i < permissions.length; i++) {
            try {
                permissionInfo = packageManager.getPermissionInfo(permissions[i], 0);

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            CharSequence permissionName = permissionInfo.loadLabel(packageManager);
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                // 未获取权限
                Log.i(TAG, "您未获得【" + permissionName + "】的权限 ===>");
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                    Log.i(TAG, "您勾选了不再提示【" + permissionName + "】权限的申请");
                } else {
                    ActivityCompat.requestPermissions(this, permissions, MY_REQUEST_CODE);
                }
            } else {
                Log.i(TAG, "您已获得了【" + permissionName + "】的权限");
            }
        }

    }


    public void checkBox(){
        boolean CheckBoxLogin = checkBox.isChecked();
        if (CheckBoxLogin) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("uname", username.getText().toString());
            editor.putString("upswd", password.getText().toString());
            editor.putBoolean("checkboxBoolean", true);
            editor.commit();
        }
        else
        {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("uname", null);
            editor.putString("upswd", null);
            editor.putBoolean("checkboxBoolean", false);
            editor.commit();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);

        initialize();



        //listener
        login.setOnClickListener(new myClick());
        register.setOnClickListener(new myClick());



        context = getApplicationContext();
        LitePal.initialize(this);

        if (ConfigData.getIsNight()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void changeLoginToMain(){
        Bundle bundle=new Bundle();
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    class myClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.loginButton:
                    User user1 =new User(-1,username.getText().toString(),password.getText().toString());
                    if(userController.login(user1)){

                        checkBox();
                        changeLoginToMain();
                    }
                    else{
                        showDialog("Login error!","Don't have this user","confirm");
                    }
                    break;
                case R.id.registerButton:
                    startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

                case R.id.eye:
                    if(hasOpeneye){
                        hasOpeneye=false;
                        eye.setImageResource(R.drawable.eyeopen);
                        HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                        password.setTransformationMethod(method1);

                    }
                    else {
                        hasOpeneye=true;
                        eye.setImageResource(R.drawable.eyeclose);

                        TransformationMethod method = PasswordTransformationMethod.getInstance();
                        password.setTransformationMethod(method);
                    }

                    int index = password.getText().toString().length();
                    password.setSelection(index);
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PackageManager packageManager = this.getPackageManager();
        PermissionInfo permissionInfo = null;
        for (int i = 0; i < permissions.length; i++) {
            try {
                permissionInfo = packageManager.getPermissionInfo(permissions[i], 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            CharSequence permissionName = permissionInfo.loadLabel(packageManager);
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "您同意了【" + permissionName + "】权限");
            } else {
                Log.i(TAG, "您拒绝了【" + permissionName + "】权限");
            }
        }
    }


    private void showDialog(String title,String message,String buttonText){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(buttonText, null)
                .show();
    }
}