package com.example.unimelborientation;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.loopj.android.http.Base64;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class SignupTabFragment extends Fragment implements View.OnClickListener{

    private EditText username, email, mobile, unit, pass, confirm_pass;
    private Button signup,picture;
    private CheckBox landlord;
    private ImageView avatar;
    private float v = 0;
    private ProgressBar pb;
    private SharedPreferencesUtils local_setting;
    private Uri imagePath;
    Bitmap bitmap = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_frag, container, false);
        initViews(root);
        return root;
    }

    private void initViews(ViewGroup root) {
        avatar = root.findViewById(R.id.avatar);
        username = root.findViewById(R.id.username_signup);
        email=root.findViewById(R.id.email_signup);
        mobile = root.findViewById(R.id.mobile_signup);
        pass = root.findViewById(R.id.password_signup);
        confirm_pass=root.findViewById(R.id.confirm_password);
        signup = root.findViewById(R.id.signup_btn);
        landlord = root.findViewById(R.id.checkBox_landlord);
        unit = root.findViewById(R.id.unit_signup);
        picture = root.findViewById(R.id.selfie_btn);
        pb = root.findViewById(R.id.progress_signup);
        pb.setVisibility(View.GONE);

        username.setTranslationX(800);
        email.setTranslationX(800);
        mobile.setTranslationX(800);
        pass.setTranslationX(800);
        confirm_pass.setTranslationX(800);
        signup.setTranslationX(800);
        landlord.setTranslationX(800);
        unit.setTranslationX(800);
        picture.setTranslationX(800);

        username.setAlpha(v);
        email.setAlpha(v);
        mobile.setAlpha(v);
        pass.setAlpha(v);
        confirm_pass.setAlpha(v);
        signup.setAlpha(v);
        landlord.setAlpha(v);
        unit.setAlpha(v);
        picture.setAlpha(v);

        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        mobile.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1100).start();
        confirm_pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1300).start();
        landlord.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1500).start();
        unit.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        picture.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(100).start();
        picture.setOnClickListener(this);
        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1700).start();
        signup.setOnClickListener(this);

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(Intent.ACTION_PICK);
                photoIntent.setType("image/*");
                startActivityForResult(photoIntent,1);
            }
        });
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
            showToast("Email is empty");
            pb.setVisibility(View.GONE);
            return;
        }

        if (getMobile().isEmpty()) {
            showToast("Mobile is empty");
            pb.setVisibility(View.GONE);
            return;
        }

        if (getUnit().isEmpty()) {
            showToast("Unit Number is empty");
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
        if(bitmap==null){
            showToast("No Picture yet");
            pb.setVisibility(View.GONE);
            return;
        }

        try {
            JSONObject params = new JSONObject();
            params.put("uname", getAccount());
            params.put("email", getEmail());
            params.put("phone", getMobile());
            params.put("password", getPassword());
            params.put("room",Integer.parseInt(getUnit()));
            params.put("landlord",getLandlord()?1:0);
            params.put("sourceImage",getImage());
            showToast("Attempt to signup...");
            StringEntity entity = new StringEntity(params.toString());
            //todo signup api
            HttpClient.post("user/register", entity, new JsonHttpResponseHandler(){
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
                    try {
                        if (response.get("code").equals("0")){
                            System.out.println("hello world");
                            showToast(msg);
                            local_setting = new SharedPreferencesUtils(getContext(), "setting");
                            try {
                                local_setting.putValues(
                                        new SharedPreferencesUtils.ContentValue("rememberPassword", true),
                                        new SharedPreferencesUtils.ContentValue("name", getAccount()),
                                        new SharedPreferencesUtils.ContentValue("password", getPassword()),
                                        new SharedPreferencesUtils.ContentValue("uid", response.getJSONObject("data").get("uid"))
                                );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            startActivity(new Intent(getContext(), UniLoginActivity.class));
                            getActivity().finish();
                        }else{
                            showToast(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
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

    private String getMobile() {
        return mobile.getText().toString().trim();
    }

    private String getUnit() {
        return unit.getText().toString().trim();
    }

    private Boolean getLandlord() {
        return landlord.isChecked();
    }

    private String getAccount() {
        return username.getText().toString().trim();
    }

    private String getImage(){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream);
        byte[] imageByteArray=stream.toByteArray();
        String img_str = Base64.encodeToString(imageByteArray, 0);
        return img_str;
    }
    public void showToast(String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode ==1 &&resultCode ==RESULT_OK&&data!=null){
            imagePath=data.getData();

            getImageInImageView();
        }
    }
    private void getImageInImageView() {
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        avatar.setImageBitmap(bitmap);
    }
}
