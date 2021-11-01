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

import com.example.unimelborientation.type.Subject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {
    List<Subject> curSubjectsList;
    List<Subject> subjectsListAll;

    public RecyclerAdapter(List<Subject> curSubjectsList) {
        this.curSubjectsList = curSubjectsList;
        System.out.println(curSubjectsList);
        this.subjectsListAll = new ArrayList<>(curSubjectsList);
        System.out.println(subjectsListAll);
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
        holder.rowSmallTextView.setText(curSubjectsList.get(position).getRowSmallText());
        holder.rowLargeTextView.setText(curSubjectsList.get(position).getRowLargeText());
        //Todo set subject score???
        //Todo set subject title???
    }

    @Override
    public int getItemCount() {
        return curSubjectsList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    final Filter filter = new Filter() {

        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Subject> filteredList = new ArrayList<>();
            System.out.println(subjectsListAll);
            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(subjectsListAll);
            } else {
                for (Subject subject : subjectsListAll) {
                    if (subject.getSubjname().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(subject);
                    }
                    if (subject.getSubjcode().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(subject);
                    }
                }
                if (filteredList.size() == 0){
                    //todo api search
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //runs on a UI thread
        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            curSubjectsList.clear();
            List<Subject> filterList= (List<Subject>) filterResults.values;
            curSubjectsList.addAll(filterList);
            notifyDataSetChanged();
        }
    };

    public void syncCurrentSubjects(ArrayList<Subject> subjectsList){
        curSubjectsList = subjectsList;
        subjectsListAll = curSubjectsList;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void sort(String method){
        if (method.equals("Practice Score Ascending")){
            Collections.sort(curSubjectsList, new Comparator<Subject>() {
                @Override
                public int compare(Subject o1, Subject o2) {
                    float res = o1.getPractiscore() - o2.getPractiscore();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? 1: -1 ;
                    }
                }
            });
        }
        if (method.equals("Practice Score Descending")){
            Collections.sort(curSubjectsList, new Comparator<Subject>() {
                @Override
                public int compare(Subject o1, Subject o2) {
                    float res = o1.getPractiscore() - o2.getPractiscore();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? -1: 1 ;
                    }
                }
            });
        }
        if (method.equals("Theory Score Ascending")){
            Collections.sort(curSubjectsList, new Comparator<Subject>() {
                @Override
                public int compare(Subject o1, Subject o2) {
                    float res = o1.getTheoryscore() - o2.getTheoryscore();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? 1: -1 ;
                    }
                }
            });
        }
        if (method.equals("Theory Score Descending")){
            Collections.sort(curSubjectsList, new Comparator<Subject>() {
                @Override
                public int compare(Subject o1, Subject o2) {
                    float res = o1.getTheoryscore() - o2.getTheoryscore();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? -1: 1 ;
                    }
                }
            });
        }
        if (method.equals("Difficulty Score Ascending")){
            Collections.sort(curSubjectsList, new Comparator<Subject>() {
                @Override
                public int compare(Subject o1, Subject o2) {
                    float res = o1.getDiffiscore() - o2.getDiffiscore();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? 1: -1 ;
                    }
                }
            });
        }
        if (method.equals("Difficulty Score Descending")){
            Collections.sort(curSubjectsList, new Comparator<Subject>() {
                @Override
                public int compare(Subject o1, Subject o2) {
                    float res = o1.getDiffiscore() - o2.getDiffiscore();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? -1: 1 ;
                    }
                }
            });
        }
        if (method.equals("Subject Name Alphabet Ascending")){
            Collections.sort(curSubjectsList, new Comparator<Subject>() {
                @Override
                public int compare(Subject o1, Subject o2) {
                    return o1.getSubjname().compareTo(o2.getSubjname());
                }
            });
        }
        if (method.equals("Subject Name Alphabet Descending")){
            Collections.sort(curSubjectsList, new Comparator<Subject>() {
                @Override
                public int compare(Subject o1, Subject o2) {
                    return -(o1.getSubjname().compareTo(o2.getSubjname()));
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

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),
                    "Loading: "+ curSubjectsList.get(getAdapterPosition()).getRowLargeText(),
                    Toast.LENGTH_SHORT).show();
            Context context = view.getContext();
            String subjectCode = curSubjectsList.get(getAdapterPosition()).getSubjcode();
            Intent intent = new Intent(context, subjectDetail.class); //Todo jump to @'s activity
            intent.putExtra("subjectCode", subjectCode);
            context.startActivity(intent);
        }
    }
}
