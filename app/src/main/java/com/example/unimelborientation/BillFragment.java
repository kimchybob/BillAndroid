package com.example.unimelborientation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.unimelborientation.databinding.BillFragmentBinding;
import com.example.unimelborientation.databinding.SubjectFragmentBinding;
import com.example.unimelborientation.type.Bill;
import com.example.unimelborientation.type.Subject;
import com.example.unimelborientation.util.HttpClient;
import com.example.unimelborientation.util.SharedPreferencesUtils;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class BillFragment extends Fragment {

    private BillViewModel myViewModel;
    RecyclerBillAdapter recyclerAdapter;
    private final List<Bill> billsList = new ArrayList<>();
    private List<Map<String, String>> courseWindowData, sortWindowData, trendWindowData;
    private BillFragmentBinding binding;
    private PopupWindow popMenu;
    private ListView listView, popListView;
    private SimpleAdapter courseMenuAdapter, sortMenuAdapter, trendMenuAdapter;
    private int menuIndex = 0;
    private String currentCourse = "Master of Information Technology", currentSort, currentTrend;
    private String uid = "1";
    private Map<String, String> trendMap= new HashMap<>();



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = BillFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initBillsData();
        initPopWindowData();
        initFuncBarView();
        initPopWindowView();
        return view;
    }

    private void initPopWindowView() {
        View contentView = View.inflate(this.getContext(), R.layout.func_bar_pop_win_list, null);
        popMenu = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popMenu.setOutsideTouchable(true);
        popMenu.setBackgroundDrawable(new BitmapDrawable());
        popMenu.setFocusable(true);
        popMenu.setAnimationStyle(R.style.popwin_anim_style);
        popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                binding.supplierListCourseTv.setTextColor(Color.parseColor("#5a5959"));
                binding.supplierListSortTv.setTextColor(Color.parseColor("#5a5959"));
                binding.supplierListTrendTv.setTextColor(Color.parseColor("#5a5959"));
            }
        });


        popListView = (ListView) contentView.findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom)
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        popMenu.dismiss();
                    }
                });
        courseMenuAdapter = new SimpleAdapter(getContext(), courseWindowData,
                R.layout.item_listview_pop_win, new String[]{"name"},
                new int[]{R.id.listview_popwind_tv});
        sortMenuAdapter = new SimpleAdapter(getContext(), sortWindowData,
                R.layout.item_listview_pop_win, new String[]{"name"},
                new int[]{R.id.listview_popwind_tv});
        trendMenuAdapter = new SimpleAdapter(getContext(), trendWindowData,
                R.layout.item_listview_pop_win, new String[]{"name"},
                new int[]{R.id.listview_popwind_tv});

//        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
//                                    long arg3) {
//                popMenu.dismiss();
//                if (menuIndex == 0) {
//                    if (currentCourse != null && currentCourse.equals(courseWindowData.get(pos).get("name"))) {
//                        return;
//                    }
//                    currentCourse = courseWindowData.get(pos).get("name");
//                    binding.progress.setVisibility(View.VISIBLE);
//                    HttpClient.get("subject/getListByCourse/" + currentCourse, null, new JsonHttpResponseHandler() {
//
//                        @Override
//                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                            ArrayList<Bill> list = new ArrayList<>();
//                            try {
//                                System.out.println(response);
//                                JSONArray data = (JSONArray) response.get("data");
//
//                                for (int index = 0; index < data.length(); index++) {
//                                    list.add(new Gson().fromJson(String.valueOf((JSONObject) data.get(index)), Subject.class));
//                                }
//                                Toast.makeText(getContext(), currentCourse, Toast.LENGTH_SHORT).show();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }finally {
//                                recyclerAdapter.syncCurrentSubjects(list);
//                                binding.progress.setVisibility(View.GONE);
//                            }
//                        }
//                    });
//                } else if (menuIndex == 1) {
//                    currentSort = sortWindowData.get(pos).get("name");
//                    Toast.makeText(getContext(), currentSort, Toast.LENGTH_SHORT).show();
//                    recyclerAdapter.sort(currentSort);
//                } else {
//
//                    currentTrend = trendWindowData.get(pos).get("name");
//                    Toast.makeText(getContext(), currentTrend, Toast.LENGTH_SHORT).show();
//                    Context context = getContext();
//                    Intent intent = new Intent(context, subjectDetail.class); //jump to @'s activity
//                    intent.putExtra("subjectCode", currentTrend);
//                    intent.putExtra("sid", trendMap.get(currentTrend));
//                    //todo name
//                    context.startActivity(intent);
//                }
//            }
//        });

    }

    private void initPopWindowData() {
        courseWindowData = new ArrayList<Map<String, String>>();
        //Todo get real date
        String[] menuStr1 = new String[]{"Master of Information Technology",
                "Master of Information System",
                "Master of Data Science",
                "Master of Medicine",
                "Master of Finance",
        };
        Map<String, String> map1;
        for (String s : menuStr1) {
            map1 = new HashMap<String, String>();
            map1.put("name", s);
            courseWindowData.add(map1);
        }

        sortWindowData = new ArrayList<Map<String, String>>();
        String[] menuStr2 = new String[]{
                "Subject Name Alphabet Ascending",
                "Subject Name Alphabet Descending",
                "Practice Score Ascending",
                "Practice Score Descending",
                "Theory Score Ascending",
                "Theory Score Descending",
                "Difficulty Score Ascending",
                "Difficulty Score Descending",
        };
        Map<String, String> map2;
        for (String s : menuStr2) {
            map2 = new HashMap<String, String>();
            map2.put("name", s);
            sortWindowData.add(map2);
        }

        trendWindowData = new ArrayList<Map<String, String>>();

        String[] menuStr3 = new String[]{"COMP90015",
                "COMP90020",
                "COMP90055",
        };
        Map<String, String> map3;
        for (String s : menuStr3) {
            map3 = new HashMap<String, String>();
            map3.put("name", s);
            trendWindowData.add(map3);
        }
    }

    private void initFuncBarView() {
//        binding.supplierListLv;
//        binding.progress.setVisibility(View.GONE);
        binding.supplierListCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.supplierListCourseTv.setTextColor(Color.parseColor("#39ac69"));
                popListView.setAdapter(courseMenuAdapter);
                popMenu.showAsDropDown(binding.supplierListCourse, 0, 2);
                menuIndex = 0;
            }
        });

        binding.supplierListSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.supplierListSortTv.setTextColor(Color.parseColor("#39ac69"));
                popListView.setAdapter(sortMenuAdapter);
                popMenu.showAsDropDown(binding.supplierListSort, 0, 2);
                menuIndex = 1;
            }
        });

        binding.supplierListTrend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Extracting current trends...",Toast.LENGTH_SHORT).show();
                HttpClient.get("subject/getLastSubjects", null, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            JSONArray data = (JSONArray) response.get("data");
                            trendWindowData.clear();
                            Map<String, String> map;
                            for (int index = 0; index < data.length(); index++) {
                                map = new HashMap<String, String>();
                                Subject subject = new Gson().fromJson(String.valueOf((JSONObject) data.get(index)), Subject.class);
                                map.put("name", subject.getSubjcode());
                                trendWindowData.add(map);
                                trendMap.put(subject.getSubjcode(), String.valueOf(subject.getSid()));
                            }
                            binding.supplierListTrendTv.setTextColor(Color.parseColor("#39ac69"));
                            trendMenuAdapter = new SimpleAdapter(getContext(), trendWindowData,
                                    R.layout.item_listview_pop_win, new String[]{"name"},
                                    new int[]{R.id.listview_popwind_tv});
                            popListView.setAdapter(trendMenuAdapter);
                            popMenu.showAsDropDown(binding.supplierListTrend, 0, 2);
                            menuIndex = 2;
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

    //faked data for test
    private void initBillsData() {
        RequestParams params = new RequestParams();
        SharedPreferencesUtils local_setting = new SharedPreferencesUtils(getContext(), "setting");
        params.put("uid",local_setting.getInt("uid"));
        HttpClient.get("bill/searchAllBill",params,new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                showToast(responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.get("code").equals("0")) {
                        JSONArray bill = response.getJSONArray("data");
                        for(int i =0;i<bill.length();i++){
                            JSONObject item = bill.getJSONObject(i);
                            billsList.add(new Bill(item.getInt("bid"),"",item.getString("uname"),item.getInt("expense"),item.getString("time")));
                        }
//                        save to local cache if it is neseccery
//                        local_setting.putValues(new SharedPreferencesUtils.ContentValue("uid", response.getJSONObject("data").get("uid")));
                        initRecyclerView();
                        binding.progress.setVisibility(View.GONE);

                    } else {
                        System.out.println(response);
                        showToast(response.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

//        TODO: enable this code to load data dynamically
//
//        HttpClient.get("subject/getListByUid/" + uid, null, new JsonHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//
//                try {
//                    JSONArray data = (JSONArray) response.get("data");
//                    for (int index = 0; index < data.length(); index++) {
//                        billsList.add(new Gson().fromJson(String.valueOf((JSONObject) data.get(index)), Bill.class));
//                    }
//                    initRecyclerView();
//                    binding.progress.setVisibility(View.GONE);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                Log.d("getList", "onFailure: "+ responseString);
//            }
//        });
    }
    //Todo get real data from our database???


    private void initRecyclerView() {

        recyclerAdapter = new RecyclerBillAdapter(billsList);
        binding.recyclerView.setAdapter(recyclerAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        if (getActivity() != null) {
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
            binding.recyclerView.addItemDecoration(dividerItemDecoration);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myViewModel = new ViewModelProvider(this).get(BillViewModel.class);
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
        if (getActivity() != null) {
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