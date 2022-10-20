package com.example.unimelborientation;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.unimelborientation.databinding.BillFragmentBinding;
import com.example.unimelborientation.type.Bill;
import com.example.unimelborientation.type.Subject;
import com.example.unimelborientation.util.HttpClient;
import com.example.unimelborientation.util.SharedPreferencesUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class ProfileFragme extends Fragment {
    private TextView realname,username,emailtext,mobile;
    private ImageView touxiang;
    private String email;
    private Uri imagePath;







    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profile1_fragment, container, false);
        realname = (TextView) view.findViewById(R.id.realname);
        username=(TextView)view.findViewById(R.id.username1);
        emailtext = (TextView)view.findViewById(R.id.email);
        mobile=(TextView)view.findViewById(R.id.mobile);
        touxiang=(ImageView) view.findViewById(R.id.touxiang);

        Intent intent = getActivity().getIntent();
        email = intent.getStringExtra("email");
        initProfileData();

        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(Intent.ACTION_PICK);
                photoIntent.setType("image/*");
                startActivityForResult(photoIntent,1);
            }
        });


        //------------------------------------------------------------
        HttpClient.get("api/signup", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray data = (JSONArray) response.get("data");
                    for (int i =0; i<data.length(); i++){
                        JSONObject item = data.getJSONObject(i);
                        if(item.getString("name").equals("...")){
                            realname.setText(item.getString("name"));
                        }
                        else if(item.getString("type").equals("lab")){

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("getTrend", "onFailure: "+ responseString);
                Toast.makeText(getContext(),
                        responseString,
                        Toast.LENGTH_SHORT).show();
            }
        });


        //------------------------------------------------------------

        return view;

    }

    private void initProfileData() {
        realname.setText("");
        RequestParams params = new RequestParams();
        SharedPreferencesUtils local_setting = new SharedPreferencesUtils(getContext(), "setting");
        params.put("uid",local_setting.getInt("uid"));
        HttpClient.get("user/getUser",params,new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                showToast(responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.get("code").equals("0")) {
                        JSONObject user = response.getJSONObject("data");
                        username.setText((String)user.get("uname"));
                        emailtext.setText((String)user.get("email"));
                        mobile.setText((String)user.get("phone"));
                    } else {
                        System.out.println(response);
                        showToast(response.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode ==1 &&resultCode ==RESULT_OK&&data!=null){
            imagePath=data.getData();

            getImageInImageView();
        }
    }
    private void getImageInImageView() {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        touxiang.setImageBitmap(bitmap);
    }


}
