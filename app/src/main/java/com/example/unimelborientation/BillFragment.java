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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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
    private final List<Bill> landlordBillsList = new ArrayList<>();
    private List<Map<String, String>> courseWindowData, sortWindowData, trendWindowData;
    private BillFragmentBinding binding;
    private PopupWindow popMenu;
    private CheckBox showAll;
    private TextView supplier_list_sort_tv;
    private ListView listView, popListView;
    private SimpleAdapter sortMenuAdapter;
    private int menuIndex = 0;
    private String uid = "1";
    private Map<String, String> trendMap= new HashMap<>();
    private String currentSort;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = BillFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
//        View view = inflater.inflate(R.layout.bill_fragment,container,false);
        initBillsData();
        initPopWindowData();
        initFuncBarView();
        initPopWindowView();
        binding.showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.showAll.isChecked()){
                    if(landlordBillsList.size()==0){ requestAllResult(); }
                    else{ initRecyclerView();}
                }else{ initRecyclerView(); }
            }
        });
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
                binding.showAll.setTextColor(Color.parseColor("#5a5959"));
                binding.supplierListSortTv.setTextColor(Color.parseColor("#5a5959"));
            }
        });

        SharedPreferencesUtils local_setting = new SharedPreferencesUtils(getContext(), "setting");
        Boolean isLandlord = local_setting.getInt("landlord")==1;
        if(!isLandlord){
            binding.showAll.setVisibility(View.GONE);
        }

        popListView = (ListView) contentView.findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom)
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        popMenu.dismiss();
                    }
                });
        sortMenuAdapter = new SimpleAdapter(getContext(), sortWindowData,
                R.layout.item_listview_pop_win, new String[]{"name"},
                new int[]{R.id.listview_popwind_tv});

        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                popMenu.dismiss();
                currentSort = sortWindowData.get(pos).get("name");
                Toast.makeText(getContext(), currentSort, Toast.LENGTH_SHORT).show();
                recyclerAdapter.sort(currentSort);
            }
        });
    }

    private void initPopWindowData() {
        sortWindowData = new ArrayList<Map<String, String>>();
        String[] menuStr2 = new String[]{
                "Expenses Ascending",
                "Expenses Descending",
                "Date Ascending",
                "Date Descending",
        };
        Map<String, String> map2;
        for (String s : menuStr2) {
            map2 = new HashMap<String, String>();
            map2.put("name", s);
            sortWindowData.add(map2);
        }
    }

    private void initFuncBarView() {
        binding.supplierListSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.supplierListSortTv.setTextColor(Color.parseColor("#39ac69"));
                popListView.setAdapter(sortMenuAdapter);
                popMenu.showAsDropDown(binding.supplierListSort, 0, 2);
                menuIndex = 1;
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

    private void requestAllResult(){
        RequestParams params = new RequestParams();
        SharedPreferencesUtils local_setting = new SharedPreferencesUtils(getContext(), "setting");
        int uid = local_setting.getInt("uid");
        params.put("uid",uid);
        HttpClient.get("bill/searchLandlordAllBill",params,new JsonHttpResponseHandler() {
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
                            landlordBillsList.add(new Bill(item.getInt("bid"),"",item.getString("uname"),item.getInt("expense"),item.getString("time")));
                        }
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
    }

    //faked data for test
    private void initBillsData() {
        RequestParams params = new RequestParams();
        SharedPreferencesUtils local_setting = new SharedPreferencesUtils(getContext(), "setting");
        int uid = local_setting.getInt("uid");
        if(uid==0){
//            billsList.add(new Bill(1,"bill","kim",80,"2020/10/26",305));
            initRecyclerView();
            binding.progress.setVisibility(View.GONE);
            return;
        }
        params.put("uid",uid);
        HttpClient.get("bill/searchTenantAllBill",params,new JsonHttpResponseHandler() {
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
    }

    private void initRecyclerView() {

        recyclerAdapter = new RecyclerBillAdapter(binding.showAll.isChecked()?landlordBillsList:billsList);
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

}