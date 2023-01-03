package com.example.memoexam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Serializable {

    ArrayList<Item> memoList;
    Context ctx;

    int position;
    String TAG = "-------Adapter-------";

    ItemClickListener itemClickListener;

    LinearLayoutManager linearLayoutManager;

    androidx.recyclerview.widget.RecyclerView recyclerView;

    public Adapter(Context ctx) {
        this.ctx = ctx;
    }

    public Adapter() {

    }

    public Adapter(ArrayList<Item> memoList) {
        this.memoList = memoList;
    }

    public void setMemoList(ArrayList<Item> memoList, Context ctx) {
        this.memoList = memoList;
        this.ctx = ctx;
    }

    public void setArrayList(ArrayList<Item> memoList) {
        this.memoList = memoList;
    }

    public void setPositionData(ArrayList<Item> memoList) {
        this.memoList = memoList;
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public void setItemClickListener(ItemClickListener listener) {
        itemClickListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView memoWrite;

        Button delete;

        LinearLayout linearLayout;

        ItemClickListener itemClickListener;

        Context ctx;

        public ViewHolder(View view) {
            super(view);

            delete = view.findViewById(R.id.deleteButton);
            memoWrite = view.findViewById(R.id.memoWrite);
            linearLayout = view.findViewById(R.id.linearLayout);

        }

        public void setItem(Item item) {
            memoWrite.setText(item.getMemoList());
        }


        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)
                ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler, parent, false);
        Adapter.ViewHolder viewHolder = new Adapter.ViewHolder(view);

        return  viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, @SuppressLint("RecyclerView") int position) {

        Log.i(TAG, "[onBindViewHolder Method]");

        linearLayoutManager = new LinearLayoutManager(((MainActivity) MainActivity.ctx).ctx);

        final Item item = memoList.get(position);
        viewholder.setItem(item);

        if (item.getMemoList().equals("")) {
            viewholder.memoWrite.setVisibility(View.GONE);
        }

        viewholder.memoWrite.setText(item.getMemoList());

        viewholder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "[deleteButton onClick]");
                int position = viewholder.getAdapterPosition();
                Log.i(TAG, "[in deleteOnClick int position Check] : " + position);
                memoList.remove(position);
                notifyItemRemoved(position);
                Log.i(TAG, "[in deleteOnClick memoListCheck : " + memoList);

                String checkOk = item.getMemoList();
                Log.i(TAG, "[in deleteOnClick checkOk Check] : " + checkOk );

                // Min 것은 삭제하면서 기록리스트에 추가해야함 근데 날짜 지나면 자동으로 넘어가게 하는 것일 수도
            }
        });

//        viewholder.
    }

    @Override
    public int getItemCount() {
        return (null != memoList ? memoList.size() : 0);
    }


}
