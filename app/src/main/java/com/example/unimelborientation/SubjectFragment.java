package com.example.unimelborientation;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.unimelborientation.databinding.SubjectFragmentBinding;
import com.example.unimelborientation.type.RowSubject;

import java.util.ArrayList;
import java.util.List;

public class SubjectFragment extends Fragment {

    private SubjectViewModel myViewModel;
    RecyclerAdapter recyclerAdapter;
    private final List<RowSubject> subjectsList = new ArrayList<>();
    private SubjectFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SubjectFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
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

        recyclerAdapter = new RecyclerAdapter(subjectsList);
        binding.recyclerView.setAdapter(recyclerAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        if (getActivity() != null){
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
            binding.recyclerView.addItemDecoration(dividerItemDecoration);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myViewModel = new ViewModelProvider(this).get(SubjectViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // TODO Add your menu entries here
        if (getActivity() != null){
            getActivity().getMenuInflater().inflate(R.menu.main_menu, menu);
        }
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

}