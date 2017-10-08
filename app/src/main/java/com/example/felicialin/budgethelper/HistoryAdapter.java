package com.example.felicialin.budgethelper;

import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;
import com.reimaginebanking.api.nessieandroidsdk.models.Purchase;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    private ArrayList<Purchase> pDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView view) {
            super(view);

            mTextView = view;
        }
    }

    public HistoryAdapter(ArrayList<Purchase> purchaseList) {
        pDataSet = purchaseList;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //This needs to be fixed!
        TextView v = (TextView) android.view.LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_home_page, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //this too!!!
        //holder.mTextView.setText(pDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
