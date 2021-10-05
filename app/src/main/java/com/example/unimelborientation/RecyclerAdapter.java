package com.example.unimelborientation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private static final String TAG = "RecyclerAdapter";
    int count = 0;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: " + count++);

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView rowImageView;
        TextView rowLargeTextView, rowSmallTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowImageView = itemView.findViewById(R.id.rowImageView);
            rowLargeTextView = itemView.findViewById(R.id.rowLargeTextView);
            rowSmallTextView = itemView.findViewById(R.id.rowSmallTextView);

        }
    }
}
