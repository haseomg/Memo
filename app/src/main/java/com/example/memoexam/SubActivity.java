package com.example.memoexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    Button goMain;

    TextView subTextView;

    String TAG = "----------Sub Activity----------";
    public static Context ctx;

    Adapter adapter;
    ArrayList<Item> memoList;
    LinearLayoutManager linearLayoutManager;
    androidx.recyclerview.widget.RecyclerView recyclerView;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Log.i(TAG, "onCreate()");


        ctx = SubActivity.this;

        recyclerView = findViewById(R.id.subRecyclerView);

        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false);

        adapter = new Adapter(getApplicationContext());


        adapter.setItemClickListener(new Adapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e(TAG, "[int position] : " + position);
            }
        });

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager
                (this, RecyclerView.VERTICAL, false));

        memoList = new ArrayList<>();

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Sub RecyclerView Click]");
            }
        });


        Intent intent = getIntent();
        String check = "check";
        if (intent.getStringExtra("check") == check) {
            Log.i(TAG, "[getIntent Check == check]");
        } else {
            Log.i(TAG, "[getIntent Check != check] : " + intent.getStringExtra("check"));


            if (intent.getSerializableExtra("memoList") != null) {
                memoList = (ArrayList<Item>) getIntent().getSerializableExtra("memoList");
                Log.i(TAG, "[Sub MemoList.toString Check] : " + memoList.toString());
                Log.i(TAG, "[Sub MemoList.size Check] : " + memoList.size());
                Log.i(TAG, "[Sub MemoList Check] : " + memoList);


                adapter.notifyDataSetChanged();
            } else {

            }
//            Item item = new Item (memo);
//            memoList.add(item);

        }

        adapter.setMemoList(memoList, getApplicationContext());

        adapter.notifyItemRangeInserted(position, memoList.size());


        goMain = findViewById(R.id.goMainButton);
        goMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, MainActivity.class);

                String check = "check";
                intent.putExtra("check", check);

                if (memoList != null) {
                    intent.putExtra("memoList", memoList);
                }

                startActivity(intent);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

}