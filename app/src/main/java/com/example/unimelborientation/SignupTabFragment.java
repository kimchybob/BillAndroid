package com.example.unimelborientation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.unimelborientation.util.HttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class SignupTabFragment extends Fragment implements View.OnClickListener{

    private EditText username, email, phone, pass, confirm_pass;
    private Button signup;
    private float v = 0;
    private ProgressBar pb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_frag, container, false);
        initViews(root);
        return root;
    }

    private void initViews(ViewGroup root) {
        username = root.findViewById(R.id.username_signup);
        email=root.findViewById(R.id.email_signup);
        pass = root.findViewById(R.id.password_signup);
        phone = root.findViewById(R.id.mobile_signup);
        confirm_pass=root.findViewById(R.id.confirm_password);
        signup = root.findViewById(R.id.signup_btn);
        pb = root.findViewById(R.id.progress_signup);
        pb.setVisibility(View.GONE);

        username.setTranslationX(800);
        email.setTranslationX(800);
        pass.setTranslationX(800);
        phone.setTranslationX(800);
        confirm_pass.setTranslationX(800);
        signup.setTranslationX(800);

        username.setAlpha(v);
        email.setAlpha(v);
        pass.setAlpha(v);
        phone.setAlpha(v);
        confirm_pass.setAlpha(v);
        signup.setAlpha(v);

        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(150).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        confirm_pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        phone.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(100).start();
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.signup_btn) {
            pb.setVisibility(View.VISIBLE);
            signup();
        }



    }

    private void signup() {
        if (getAccount().isEmpty()) {
            showToast("Username is empty");
            pb.setVisibility(View.GONE);
            return;
        }

        if (getEmail().isEmpty()) {
            showToast("Password is empty");
            pb.setVisibility(View.GONE);
            return;
        }

        if (getPassword().isEmpty()) {
            showToast("Password is empty");
            pb.setVisibility(View.GONE);
            return;
        }

        if (getPhone().isEmpty()){
            showToast("Mobile is empty");
            pb.setVisibility(View.GONE);
            return;
        }

        if (!isConfirmed()){
            showToast("Inconsistent password");
            pb.setVisibility(View.GONE);
            return;
        }

        RequestParams params = new RequestParams();
        params.put("username", getAccount());
        params.put("email", getEmail());
        params.put("password", getPassword());
        params.put("mobile", getPhone());
        showToast("Attempt to signup...");
        //todo signup api
        HttpClient.post("api/login", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                pb.setVisibility(View.GONE);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                pb.setVisibility(View.GONE);

                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });



    }

    private String getEmail() {
        return email.getText().toString().trim();
    }

    private boolean isConfirmed() {
        return pass.getText().toString().equals(confirm_pass.getText().toString());

    }

    private String getPhone() {
        return phone.getText().toString().trim();
    }

    private String getPassword() {
        return pass.getText().toString().trim();
    }

    private String getAccount() {
        return username.getText().toString().trim();
    }

    public void showToast(String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
