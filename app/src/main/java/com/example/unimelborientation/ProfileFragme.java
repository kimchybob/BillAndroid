package com.example.unimelborientation;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.unimelborientation.databinding.BillFragmentBinding;
import com.example.unimelborientation.type.Bill;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileFragme extends Fragment implements AdapterView.OnItemSelectedListener{
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
        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
