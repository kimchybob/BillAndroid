package com.example.unimelborientation;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unimelborientation.type.RowSubject;

import java.util.ArrayList;
import java.util.List;

public class SubjectFragment extends Fragment {

    private SubjectViewModel mViewModel;
    private View view;
    public RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    private List<RowSubject> subjectsList = new ArrayList<>();

    public static SubjectFragment newInstance() {
        return new SubjectFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.subject_fragment, container, false);
        initData();
        initRecyclerView();
        return view;

    }

    //faked data for test
    private void initData(){
        for(int i=0; i<100; i++){
            subjectsList.add(new RowSubject(
                            "Subject" + i,
                            "Comp9000" + i,
                            0,
                            0,
                            0
                    )
            );
        }

        //Todo get real data from our database???


    }

    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(subjectsList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SubjectViewModel.class);
        // TODO: Use the ViewModel
    }

}