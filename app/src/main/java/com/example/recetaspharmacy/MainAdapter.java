package com.example.recetaspharmacy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
private ArrayList<String>  mDataset;

public MainAdapter(ArrayList<String> mDataset){
    this.mDataset=mDataset;

}
    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
holder.mTitle.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTitle;

public ViewHolder(View itemView){
    super(itemView);
    mTitle=(TextView) itemView.findViewById(R.id.title);
}













    }
}
