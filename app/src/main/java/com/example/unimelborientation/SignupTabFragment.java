package com.example.unimelborientation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.unimelborientation.util.HttpClient;
import com.example.unimelborientation.util.SharedPreferencesUtils;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class SignupTabFragment extends Fragment implements View.OnClickListener{

    private EditText username, email, pass, confirm_pass;
    private Button signup;
    private Button CameraSelfie;
    private float v = 0;
    private ProgressBar pb;
    private SharedPreferencesUtils local_setting;
    private ImageView imagePreview;

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
        confirm_pass=root.findViewById(R.id.confirm_password);
        CameraSelfie= root.findViewById(R.id.selfie_btn);
        imagePreview = root.findViewById(R.id.imagePreview);
        signup = root.findViewById(R.id.signup_btn);
        pb = root.findViewById(R.id.progress_signup);
        pb.setVisibility(View.GONE);

        username.setTranslationX(800);
        email.setTranslationX(800);
        pass.setTranslationX(800);
        confirm_pass.setTranslationX(800);
        signup.setTranslationX(800);

        username.setAlpha(v);
        email.setAlpha(v);
        pass.setAlpha(v);
        confirm_pass.setAlpha(v);
        signup.setAlpha(v);

        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        confirm_pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(100).start();
        signup.setOnClickListener(this);
        CameraSelfie.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.signup_btn) {
            pb.setVisibility(View.VISIBLE);
            signup();
        }
        if(view.getId() == R.id.selfie_btn) {
             Intent intent = new Intent();
             intent.putExtra("camerasensortype",2);
             intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
             startActivityForResult(intent, 1);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
// 获取相机返回的数据，并转换为Bitmap图片格式
            Bitmap bitmap = (Bitmap) bundle.get("data");
            FileOutputStream b = null;
            File file = new File("/storage/testImage");
            if (!file.exists())
                file.mkdirs();
            try {
                b = new FileOutputStream("/storage/testImage/mySelfie.jpg");
// 把数据写入文件
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    b.flush();
                    b.close();
                    b = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            imagePreview.setImageBitmap(bitmap);
        }
    }

    private void signup() {
        if (getAccount().isEmpty()) {
            showToast("Username is empty");
            pb.setVisibility(View.GONE);
            return;
        }

        if (getEmail().isEmpty()) {
            showToast("Email is empty");
            pb.setVisibility(View.GONE);
            return;
        }

        if (getPassword().isEmpty()) {
            showToast("Password is empty");
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
        showToast("Attempt to signup...");
        System.out.println(getAccount());
        //todo signup api
        HttpClient.post("api/signup", params, new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                pb.setVisibility(View.GONE);
                System.out.println(responseString);
                showToast(responseString);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                pb.setVisibility(View.GONE);
                String msg = null;
                try {
                    msg = (String) response.get("msg");
                    System.out.println(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (msg.equals("Successfully signup!")){
                    showToast(msg);
                    local_setting = new SharedPreferencesUtils(getContext(), "setting");
                    local_setting.putValues(
                            new SharedPreferencesUtils.ContentValue("rememberPassword", true),
                            new SharedPreferencesUtils.ContentValue("name", getAccount()),
                            new SharedPreferencesUtils.ContentValue("password", getPassword())
                    );

                    startActivity(new Intent(getContext(), UniLoginActivity.class));
                    getActivity().finish();
                }else{
                    showToast(msg);
                }

            }
        });



    }

    private String getEmail() {
        return email.getText().toString().trim();
    }

    private boolean isConfirmed() {
        return pass.getText().toString().equals(confirm_pass.getText().toString());

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
                Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
            }
        });

    }
}
