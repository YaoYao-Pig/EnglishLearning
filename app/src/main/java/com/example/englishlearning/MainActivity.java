package com.example.englishlearning;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.englishlearning.Controller.JsonController;
import com.example.englishlearning.Controller.UserController;
import com.example.englishlearning.Controller.WordPageController;
import com.example.englishlearning.DB.DataBaseController;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomBar;
    NavController navController;
    AppBarConfiguration appBarConfiguration;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomBar=findViewById(R.id.bottomNavigationView);

        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView3);
        navController = navHostFragment.getNavController();


        appBarConfiguration=new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationUI.setupActionBarWithNavController(MainActivity.this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(bottomBar,navController);


    }
}
