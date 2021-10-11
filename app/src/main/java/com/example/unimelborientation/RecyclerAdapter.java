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
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {
    List<RowSubject> subjectsList;
    List<RowSubject> subjectsListAll;

    public RecyclerAdapter(List<RowSubject> subjectsList) {
        this.subjectsList = subjectsList;
        System.out.println(subjectsList);
        this.subjectsListAll = new ArrayList<>(subjectsList);
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
        holder.rowSmallTextView.setText(subjectsList.get(position).getRowSmallText());
        holder.rowLargeTextView.setText(subjectsList.get(position).getRowLargeText());
        //Todo set subject score???
        //Todo set subject title???
    }

    @Override
    public int getItemCount() {
        return subjectsList.size();
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
            subjectsList.clear();
            List<RowSubject> filterList= (List<RowSubject>) filterResults.values;
            subjectsList.addAll(filterList);
            notifyDataSetChanged();
        }
    };

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
                    "Loading: "+ subjectsList.get(getAdapterPosition()).getRowLargeText(),
                    Toast.LENGTH_SHORT).show();
            Context context = view.getContext();
            Intent intent = new Intent(context, subjectDetail.class); //Todo jump to @'s activity
            context.startActivity(intent);
        }
    }
}
