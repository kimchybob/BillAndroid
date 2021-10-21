package com.example.unimelborientation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SignupTabFragment extends Fragment {

    EditText email, phone, pass, confirm_pass;
    Button signup;
    float v = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_frag, container, false);

        email=root.findViewById(R.id.email_signup);
        pass = root.findViewById(R.id.password_signup);
        phone = root.findViewById(R.id.mobile_signup);
        confirm_pass=root.findViewById(R.id.confirm_password);
        signup = root.findViewById(R.id.signup_btn);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        phone.setTranslationX(800);
        confirm_pass.setTranslationX(800);
        signup.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        phone.setAlpha(v);
        confirm_pass.setAlpha(v);
        signup.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        confirm_pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        phone.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(100).start();





        return root;
    }
}
