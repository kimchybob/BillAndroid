package com.example.unimelborientation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class LoginTabFragment extends Fragment {

    EditText email, pass;
    TextView forgetPass;
    CheckBox rememberMe, keepLoggedIn;
    Button login;
    float v = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_frag, container, false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.password);
        forgetPass = root.findViewById(R.id.forget_pass);
        login = root.findViewById(R.id.login);
        rememberMe = root.findViewById(R.id.checkBox_password_uni);
        keepLoggedIn = root.findViewById(R.id.checkBox_login_uni);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forgetPass.setTranslationX(800);
        login.setTranslationX(800);
        rememberMe.setTranslationX(800);
        keepLoggedIn.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);
        rememberMe.setAlpha(v);
        keepLoggedIn.setAlpha(v);


        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        rememberMe.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(550).start();
        keepLoggedIn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(550).start();

        return root;
    }
}
