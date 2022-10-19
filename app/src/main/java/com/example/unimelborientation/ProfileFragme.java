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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

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

        realname.setText("Qingying Zhao");
        username.setText("Chloe");
        emailtext.setText("710870305@qq.com");
        mobile.setText("0404282847");

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
