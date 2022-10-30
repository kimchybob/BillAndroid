package com.example.unimelborientation;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unimelborientation.type.Bill;
import com.example.unimelborientation.type.Subject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecyclerBillAdapter extends RecyclerView.Adapter<RecyclerBillAdapter.ViewHolder> implements Filterable {
    List<Bill> curBillsList;
    List<Bill> billsListAll;

    public RecyclerBillAdapter(List<Bill> curBillsList) {
        this.curBillsList = curBillsList;
        System.out.println(curBillsList);
        this.billsListAll = new ArrayList<>(curBillsList);
        System.out.println(billsListAll);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rowSmallTextView.setText(curBillsList.get(position).getRowSmallText());
        holder.rowLargeTextView.setText(curBillsList.get(position).getRowLargeText());
        //Todo set subject score???
        //Todo set subject title???
    }

    @Override
    public int getItemCount() {
        return curBillsList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    final Filter filter = new Filter() {

        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Bill> filteredList = new ArrayList<>();
            System.out.println(curBillsList);
            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(curBillsList);
            } else {
//                for (Bill bill : curBillsList) {
//                    if (bill.getSubjname().toLowerCase().contains(charSequence.toString().toLowerCase())) {
//                        filteredList.add(bill);
//                    }
//                    if (subject.getSubjcode().toLowerCase().contains(charSequence.toString().toLowerCase())) {
//                        filteredList.add(subject);
//                    }
//                }
//                if (filteredList.size() == 0){
//                    //todo api search
//                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //runs on a UI thread
        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            curBillsList.clear();
            List<Bill> filterList= (List<Bill>) filterResults.values;
            curBillsList.addAll(filterList);
            notifyDataSetChanged();
        }
    };

    public void syncCurrentSubjects(ArrayList<Bill> billsList){
        curBillsList = billsList;
        billsListAll = curBillsList;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void sort(String method){
        if (method.equals("Expenses Ascending")){
            Collections.sort(curBillsList, new Comparator<Bill>() {
                @Override
                public int compare(Bill o1, Bill o2) {
                    float res = o1.getExpense() - o2.getExpense();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? 1: -1 ;
                    }
                }
            });
        }
        if (method.equals("Expenses Descending")){
            Collections.sort(curBillsList, new Comparator<Bill>() {
                @Override
                public int compare(Bill o1, Bill o2) {
                    float res = o1.getExpense() - o2.getExpense();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? -1: 1 ;
                    }
                }
            });
        }
        if (method.equals("Date Ascending")){
            Collections.sort(curBillsList, new Comparator<Bill>() {
                @Override
                public int compare(Bill o1, Bill o2) {
                    try {
                        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(o1.getDate());
                        Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(o2.getDate());
                        return date1.compareTo(date2)>0?1:-1;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return -1;
                }
            });
        }
        if (method.equals("Date Descending")){
            Collections.sort(curBillsList, new Comparator<Bill>() {
                @Override
                public int compare(Bill o1, Bill o2) {
                    try {
                        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(o1.getDate());
                        Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(o2.getDate());
                        return date1.compareTo(date2)>0?-1:1;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return 1;
                }
            });
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView rowImageView;
        TextView rowLargeTextView, rowSmallTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowImageView = itemView.findViewById(R.id.rowImageView);
            rowLargeTextView = itemView.findViewById(R.id.rowLargeTextView);
            rowSmallTextView = itemView.findViewById(R.id.rowSmallTextView);

            itemView.setOnClickListener(this);

        }

//        TODO: enable onClick if needed to display detail page
        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(),
//                    "Loading: "+ curBillsList.get(getAdapterPosition()).getRowLargeText(),
//                    Toast.LENGTH_SHORT).show();
//            Context context = view.getContext();
//            String subjectCode = curBillsList.get(getAdapterPosition()).getSubjcode();
//            Integer sid_int = curBillsList.get(getAdapterPosition()).getSid();
//            String sid = String.valueOf(sid_int);
//            Intent intent = new Intent(context, subjectDetail.class); //Todo jump to @'s activity
//            intent.putExtra("subjectCode", subjectCode);
//            intent.putExtra("sid", sid);
//            context.startActivity(intent);
        }
    }
}
