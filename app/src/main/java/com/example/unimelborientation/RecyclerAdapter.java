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

import com.example.unimelborientation.type.RowSubject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {
    List<RowSubject> curSubjectsList;
    List<RowSubject> subjectsListAll;

    public RecyclerAdapter(List<RowSubject> curSubjectsList) {
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

            List<RowSubject> filteredList = new ArrayList<>();
            System.out.println(subjectsListAll);
            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(subjectsListAll);
            } else {
                for (RowSubject subject : subjectsListAll) {
                    if (subject.getSubjectName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(subject);
                    }
                    if (subject.getSubjectCode().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(subject);
                    }
                }
                if (filteredList.size() == 0){
                    //todo api search 
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            System.out.println(filteredList);
            return filterResults;
        }

        //runs on a UI thread
        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            curSubjectsList.clear();
            List<RowSubject> filterList= (List<RowSubject>) filterResults.values;
            curSubjectsList.addAll(filterList);
            notifyDataSetChanged();
        }
    };

    public void syncCurrentSubjects(ArrayList<RowSubject> subjectsList){
        curSubjectsList = subjectsList;
        subjectsListAll = curSubjectsList;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void sort(String method){
        if (method.equals("Practice Score Ascending")){
            Collections.sort(curSubjectsList, new Comparator<RowSubject>() {
                @Override
                public int compare(RowSubject o1, RowSubject o2) {
                    float res = o1.getPracticeDegree() - o2.getPracticeDegree();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? 1: -1 ;
                    }
                }
            });
        }
        if (method.equals("Practice Score Descending")){
            Collections.sort(curSubjectsList, new Comparator<RowSubject>() {
                @Override
                public int compare(RowSubject o1, RowSubject o2) {
                    float res = o1.getPracticeDegree() - o2.getPracticeDegree();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? -1: 1 ;
                    }
                }
            });
        }
        if (method.equals("Theory Score Ascending")){
            Collections.sort(curSubjectsList, new Comparator<RowSubject>() {
                @Override
                public int compare(RowSubject o1, RowSubject o2) {
                    float res = o1.getTheoryDegree() - o2.getTheoryDegree();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? 1: -1 ;
                    }
                }
            });
        }
        if (method.equals("Theory Score Descending")){
            Collections.sort(curSubjectsList, new Comparator<RowSubject>() {
                @Override
                public int compare(RowSubject o1, RowSubject o2) {
                    float res = o1.getTheoryDegree() - o2.getTheoryDegree();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? -1: 1 ;
                    }
                }
            });
        }
        if (method.equals("Difficulty Score Ascending")){
            Collections.sort(curSubjectsList, new Comparator<RowSubject>() {
                @Override
                public int compare(RowSubject o1, RowSubject o2) {
                    float res = o1.getDifficultyDegree() - o2.getDifficultyDegree();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? 1: -1 ;
                    }
                }
            });
        }
        if (method.equals("Difficulty Score Descending")){
            Collections.sort(curSubjectsList, new Comparator<RowSubject>() {
                @Override
                public int compare(RowSubject o1, RowSubject o2) {
                    float res = o1.getDifficultyDegree() - o2.getDifficultyDegree();
                    if (res == 0){
                        return 0;
                    }else{
                        return res>0? -1: 1 ;
                    }
                }
            });
        }
        if (method.equals("Subject Name Alphabet Ascending")){
            Collections.sort(curSubjectsList, new Comparator<RowSubject>() {
                @Override
                public int compare(RowSubject o1, RowSubject o2) {
                    return o1.getSubjectName().compareTo(o2.getSubjectName());
                }
            });
        }
        if (method.equals("Subject Name Alphabet Descending")){
            Collections.sort(curSubjectsList, new Comparator<RowSubject>() {
                @Override
                public int compare(RowSubject o1, RowSubject o2) {
                    return -(o1.getSubjectName().compareTo(o2.getSubjectName()));
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
            int sid = curSubjectsList.get(getAdapterPosition()).getSid();
            Intent intent = new Intent(context, subjectDetail.class); //Todo jump to @'s activity
            intent.putExtra("sid", sid);
            context.startActivity(intent);
        }
    }
}
