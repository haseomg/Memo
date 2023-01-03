package com.example.memoexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Context ctx;

    Button plus;
    Button goSub;

    TextView mainLogo;

    Adapter adapter;
    ArrayList<Item> memoList;
    LinearLayoutManager linearLayoutManager;
    androidx.recyclerview.widget.RecyclerView recyclerView;

    int position;

    String memoWrite;
    String TAG = "----------Main Activity----------";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate()");

        mainLogo = findViewById(R.id.mainText);



        ctx = MainActivity.this;

        recyclerView = findViewById(R.id.mainRecyclerView);

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


        Intent intent  = getIntent();
        String check = "check";
        if (intent.getStringExtra("check") == check) {
            Log.i(TAG, "[getIntent Check == check]");
//            mainLogo.setText(intent.getStringExtra("check"));
        } else {
            Log.i(TAG, "[getIntent Check != check] : " + intent.getStringExtra("check"));

            if (intent.getSerializableExtra("memoList") != null) {
                memoList = (ArrayList<Item>) getIntent().getSerializableExtra("memoList");
                Log.i(TAG, "[Main MemoList.toString Check] : " + memoList.toString());
                Log.i(TAG, "[Main MemoList.size Check] : " + memoList.size());
                Log.i(TAG, "[Main MemoList Check] : " + memoList);
            } else {

            }
        }


        adapter.setMemoList(memoList, getApplicationContext());

        adapter.notifyItemRangeInserted(position, memoList.size());

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "[main RecyclerView Click]");
            }
        });

        plus = findViewById(R.id.plusButton);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 창 띄워주고 저장 시 리사이클러뷰에 반영

                final EditText editText = new EditText(ctx);
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("메모를 적어주세요");
                builder.setView(editText);
                builder.setPositiveButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i(TAG, "[Dialog Cancel Button Click");
                            }
                        });
                builder.setNegativeButton("SAVE",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 저장 시 어레이리스트에 내용 추가하고 리사이클러뷰에 반영
                                Log.i(TAG, "[Dialog Save Button Click");

                                memoWrite = editText.getText().toString();
                                Log.i(TAG, "[memoWrite.getText()] : " + memoWrite);

                                Item item = new Item(memoWrite);
                                memoList.add(item);

                                adapter.notifyDataSetChanged();
                            }
                        });
                builder.show();
            }
        });

        goSub = findViewById(R.id.goSubButton);
        goSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);

                if (memoList.size() > 0) {
//                    intent.putExtra("memo", memoWrite);
                    intent.putExtra("memoList", memoList);
                } else {
                    String check = "check";
                    intent.putExtra("check", check);
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