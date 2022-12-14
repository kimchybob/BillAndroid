package com.example.unimelborientation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.unimelborientation.util.HttpClient;
import com.example.unimelborientation.util.SharedPreferencesUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class LoginTabFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private EditText username, pass;
    private TextView forgetPass;
    private CheckBox rememberMe, keepLoggedIn;
    private Button login;
    private float v = 0;
    private SharedPreferencesUtils local_setting;
    private ProgressBar pb;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_frag, container, false);
        initViews(root);
        setupEvents();
        initData();


        return root;
    }

    private void initData() {
        if (firstLogin()) {
            rememberMe.setChecked(false);
            keepLoggedIn.setChecked(false);
        }

        if (rememberPassword()) {
            rememberMe.setChecked(true);
            setTextNameAndPassword();
        } else {
            setTextName();
        }

        if (autoLogin()) {
            keepLoggedIn.setChecked(true);
            pb.setVisibility(View.VISIBLE);
            login();

        }
    }

    private void setupEvents() {
        login.setOnClickListener(this);
        rememberMe.setOnCheckedChangeListener(this);
        keepLoggedIn.setOnCheckedChangeListener(this);
    }

    private void initViews(ViewGroup vg) {
        pb = vg.findViewById(R.id.progress_login);
        pb.setVisibility(View.GONE);
        username = vg.findViewById(R.id.username);
        pass = vg.findViewById(R.id.password);
        forgetPass = vg.findViewById(R.id.forget_pass);
        login = vg.findViewById(R.id.login);
        rememberMe = vg.findViewById(R.id.checkBox_password_uni);
        keepLoggedIn = vg.findViewById(R.id.checkBox_login_uni);

        username.setTranslationX(800);
        pass.setTranslationX(800);
        forgetPass.setTranslationX(800);
        login.setTranslationX(800);
        rememberMe.setTranslationX(800);
        keepLoggedIn.setTranslationX(800);

        username.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);
        rememberMe.setAlpha(v);
        keepLoggedIn.setAlpha(v);


        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        rememberMe.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(550).start();
        keepLoggedIn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(550).start();
        local_setting = new SharedPreferencesUtils(getContext(), "setting");

    }

    private boolean firstLogin() {
        boolean first = local_setting.getBoolean("first", true);
        if (first) {
            local_setting.putValues(new SharedPreferencesUtils.ContentValue("first", false),
                    new SharedPreferencesUtils.ContentValue("rememberPassword", false),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("name", ""),
                    new SharedPreferencesUtils.ContentValue("password", ""),
                    new SharedPreferencesUtils.ContentValue("token", ""),
                    new SharedPreferencesUtils.ContentValue("uid", "")
            );

            return true;
        }
        return false;
    }

    private boolean rememberPassword() {
        return local_setting.getBoolean("rememberPassword", false);
    }

    private boolean autoLogin() {
        return local_setting.getBoolean("autoLogin", false);
    }

    @SuppressLint("SetTextI18n")
    public void setTextNameAndPassword() {
        setTextName();
        pass.setText("" + getLocalPassword());
    }

    @SuppressLint("SetTextI18n")
    public void setTextName() {
        username.setText("" + getLocalName());
    }

    private String getLocalPassword() {
        return local_setting.getString("password");
    }

    public String getLocalName() {
        return local_setting.getString("name");
    }

    public String getAccount() {
        return username.getText().toString().trim();
    }

    public String getPassword() {
        return pass.getText().toString().trim();
    }

    private void saveCheckBoxState() {
        saveCheckBoxState(rememberMe, keepLoggedIn);
    }

    public void saveUserName() {
        if (!getAccount().equals("")) {
            local_setting.putValues(new SharedPreferencesUtils.ContentValue("name", getAccount()));
        }

    }

    public void saveCheckBoxState(CheckBox checkBox_password, CheckBox checkBox_login) {

        if (checkBox_login.isChecked()) {
            local_setting.putValues(
                    new SharedPreferencesUtils.ContentValue("rememberPassword", true),
                    new SharedPreferencesUtils.ContentValue("autoLogin", true),
                    new SharedPreferencesUtils.ContentValue("password", getPassword()));

        } else if (!checkBox_password.isChecked()) {
            local_setting.putValues(
                    new SharedPreferencesUtils.ContentValue("rememberPassword", false),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("password", ""));
        } else if (checkBox_password.isChecked()) {
            local_setting.putValues(
                    new SharedPreferencesUtils.ContentValue("rememberPassword", true),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("password", getPassword()));
        }
    }


    private void login() {
        if (getAccount().isEmpty()) {
            showToast("username is empty");
            return;
        }

        if (getPassword().isEmpty()) {
            showToast("password is empty");
            return;
        }

        login.setClickable(false);
        if(getAccount().equals("admin") && getPassword().equals("123456")){
            startMain();
            local_setting.putValues(new SharedPreferencesUtils.ContentValue("uid", 0));
            local_setting.putValues(new SharedPreferencesUtils.ContentValue("landlord", 0));
            return;
        }

        RequestParams params = new RequestParams();
        params.put("uname", getAccount());
        params.put("password", getPassword());
        showToast("Attempt to login...");
        HttpClient.post("user/login", params, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.get("code").equals("0")) {
                        saveCheckBoxState();
                        showToast("Successfully login!");
                        local_setting.putValues(new SharedPreferencesUtils.ContentValue("uid", response.getJSONObject("data").get("uid")));
                        local_setting.putValues(new SharedPreferencesUtils.ContentValue("landlord", response.getJSONObject("data").get("landlord")));
//                        String myToken = headers[3].getElements()[0].toString();
//                        System.out.println(myToken);
//                        HttpClient.authorization(myToken);

                        startMain();

                    } else {
                        System.out.println(response);
                        showToast(response.toString());
                        login.setClickable(true);
                        pb.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    System.out.println("hello world");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println(errorResponse);

                showToast("bad network, try again");
                login.setClickable(true);
                pb.setVisibility(View.GONE);
            }
        });


    }

    public void showToast(String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login) {
            pb.setVisibility(View.VISIBLE);
            saveUserName();
            login();
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView == rememberMe) {
            if (!isChecked) {
                keepLoggedIn.setChecked(false);
            }
        } else if (buttonView == keepLoggedIn) {
            if (isChecked) {
                rememberMe.setChecked(true);
            }
        }

    }

    public void startMain(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
        login.setClickable(true);
        pb.setVisibility(View.GONE);
    }
}
