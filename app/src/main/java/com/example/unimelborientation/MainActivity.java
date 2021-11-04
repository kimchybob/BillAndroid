package com.example.unimelborientation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.unimelborientation.databinding.ActivityMainBinding;
import com.example.unimelborientation.util.SharedPreferencesUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;
    private SharedPreferencesUtils local_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        local_setting = new SharedPreferencesUtils(this, "setting");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//bottom nav
        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, configuration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                local_setting.putValues(
                        new SharedPreferencesUtils.ContentValue("rememberPassword", false),
                        new SharedPreferencesUtils.ContentValue("autoLogin", false));

                startActivity(new Intent(view.getContext(), UniLoginActivity.class));
            }
        }


        );

    }

}